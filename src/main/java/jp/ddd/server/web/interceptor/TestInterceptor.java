package jp.ddd.server.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class TestInterceptor extends HandlerInterceptorAdapter {
  private final Logger log = LoggerFactory.getLogger(this.getClass());

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
    throws Exception {
    log.info("test");

    return super.preHandle(request, response, handler);
  }

  /**
   * This implementation is empty.
   */
  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                         ModelAndView modelAndView) throws Exception {
  }

  /**
   * This implementation is empty.
   */
  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
    throws Exception {
  }

  /**
   * This implementation is empty.
   */
  @Override
  public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
    throws Exception {
  }

}
