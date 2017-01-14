package jp.ddd.server.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ddd.server.redis.SesUserRepository;
import jp.ddd.server.utils.EntityFuncs;
import jp.ddd.server.utils.Requests;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import jp.ddd.server.annotation.NotLoginRequired;
import jp.ddd.server.utils.Cookies;

/**
 * 認証インターセプター
 * 現状認証情報がない場合は全てログインページへ
 *
 * @author noguchi_kohei
 */
public class AuthentecateInterceptor extends HandlerInterceptorAdapter {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SesUserRepository sesUserRepository;

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        //visitor情報をストア

        NotLoginRequired notLoginRequired = ((HandlerMethod) handler).getMethodAnnotation(NotLoginRequired.class);
        if (notLoginRequired != null) {
            return true;
        }
        //ログイン情報がない場合ログイン画面へリダイレクト
        if (!sesUserRepository.getOpt(Cookies.createKey(req, res)).isPresent()) {
            res.sendRedirect("/auth/login");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e)
      throws Exception {
    }
}
