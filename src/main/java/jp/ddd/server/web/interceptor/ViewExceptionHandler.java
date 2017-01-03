package jp.ddd.server.web.interceptor;

/**
 * view向けのexceptionをハンドリングします。
 * apiは別で
 *
 * @author noguchi_kohei
 */
//@Component
//public class ViewExceptionHandler implements HandlerExceptionResolver, Ordered {
//    private final Logger log = LoggerFactory.getLogger(this.getClass());
//
//    private final String ERROR_MESSEAGE = "errorMessage";
//
//    @Override
//    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
//            Exception e) {
//        log.error(e.getMessage(), e.getCause());
//        ModelAndView mv = null;
//        try {
//            if (e instanceof CoreException) {
//                if (e instanceof IllegalArgumentException) {
//                    mv = new ModelAndView("/errors/400.html");
//                } else if (e instanceof AuthenticationException) {
//                    mv = new ModelAndView("/errors/401.html");
//                } else if (e instanceof AccessPermissonException) {
//                    mv = new ModelAndView("/errors/403.html");
//                } else if (e instanceof NotFoundException) {
//                    mv = new ModelAndView("/errors/404.html");
//                } else if (e instanceof InternalException) {
//                    mv = new ModelAndView("/errors/500.html");
//                }
//                mv.addObject(ERROR_MESSEAGE, ((CoreException) e).getMessageForUser());
//                return mv;
//            } else {
//                return mv;
//            }
//
//        } catch (Exception exception) {
//            return mv;
//        }
//    }
//
//    @Override
//    public int getOrder() {
//        // TODO 自動生成されたメソッド・スタブ
//        return 0;
//    }
//}
