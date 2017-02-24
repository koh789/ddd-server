package jp.ddd.server.adapter.web.presenter;

/**
 * Created by noguchi_kohei 
 */
public interface Presenter<T, ResultJson> {

    ResultJson response(T t);
}
