package jp.ddd.server.aop;

import java.lang.reflect.Method;
import java.sql.Connection;

import javax.persistence.EntityManager;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.hibernate.internal.SessionImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jp.ddd.server.annotation.DataSource;
import jp.ddd.server.utils.enums.Schema;

@Aspect
@Component
@Order(1)
public class SchemaChangeAdvisor {

  private Logger log = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private EntityManager em;

  @Around("execution(* jp.personal.server.domain.repository..*(..))")
  public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

    MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

    String methodName = methodSignature.getName();
    Method method = methodSignature.getMethod();

    if (method.getDeclaringClass().isInterface()) {
      method = joinPoint.getTarget().getClass().getDeclaredMethod(methodName, method.getParameterTypes());
    }
    DataSource dataSource = (DataSource) method.getAnnotation(DataSource.class);

    SessionImpl session = em.unwrap(SessionImpl.class);
    Connection conn = session.connection();
    if (dataSource != null && dataSource.value() == Schema.SLAVE) {

      conn.setAutoCommit(false);
      conn.setReadOnly(true);
      log.debug("Schema ===> SLAVE");
    } else {
      conn.setAutoCommit(true);
      conn.setReadOnly(false);
      log.debug("Schema ===> MASTER");
    }

    Object returnValue = joinPoint.proceed();
    conn.close();

    return returnValue;
  }
}
