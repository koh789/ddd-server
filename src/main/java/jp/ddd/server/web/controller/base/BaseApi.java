package jp.ddd.server.web.controller.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ddd.server.exception.IllegalArgumentException;
import jp.ddd.server.web.json.ErrorJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jp.ddd.server.exception.AccessPermissonException;
import jp.ddd.server.exception.AuthException;
import jp.ddd.server.exception.CoreException;
import jp.ddd.server.exception.InternalException;
import jp.ddd.server.exception.NotFoundException;
import jp.ddd.server.utils.Msg;

/**
 * jsonAPI用エラーハンドラー基底クラス。
 *
 * @author noguchi_kohei
 */
public abstract class BaseApi {
  protected final Logger log = LoggerFactory.getLogger(getClass());

  @ExceptionHandler(BindException.class)
  public ResponseEntity<ErrorJson> handleException(BindException e, HttpServletRequest request,
                                                   HttpServletResponse response) {
    logError(e);

    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    String msg = (e.getFieldError() == null) ? Msg.INVALID : e.getFieldError().getDefaultMessage();
    return new ResponseEntity<ErrorJson>(new ErrorJson(httpStatus.value(), msg), httpStatus);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorJson> handleException(MethodArgumentNotValidException e, HttpServletRequest request,
                                                   HttpServletResponse response) {
    logError(e);

    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    String msg = e.getBindingResult().getFieldError().getDefaultMessage();
    return new ResponseEntity<ErrorJson>(new ErrorJson(httpStatus.value(), msg), httpStatus);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorJson> handleException(Exception e, HttpServletRequest request,
                                                   HttpServletResponse response) {
    HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    logError(e);

    String msgForUser = null;
    if (e instanceof CoreException) {
      msgForUser = ((CoreException) e).getMessageForUser();
      if (e instanceof IllegalArgumentException) {

        httpStatus = HttpStatus.BAD_REQUEST;
      } else if (e instanceof AuthException) {
        httpStatus = HttpStatus.UNAUTHORIZED;
      } else if (e instanceof AccessPermissonException) {
        httpStatus = HttpStatus.FORBIDDEN;
      } else if (e instanceof NotFoundException) {
        httpStatus = HttpStatus.NOT_FOUND;
      } else if (e instanceof InternalException) {
        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
      }
    }
    return new ResponseEntity<ErrorJson>(new ErrorJson(httpStatus.value(), msgForUser), httpStatus);
  }

  private void logError(Exception e) {
    String causeMsg = e.getCause() == null ? "" : e.getCause().getMessage();
    log.error("[ className ]: " + e.getClass().getName() + " \n [ msg ]: " + e.getMessage() + "  " + causeMsg
      + " \n[ e ]: " + e + " \n [ cause ]: " + e.getCause());
  }

}
