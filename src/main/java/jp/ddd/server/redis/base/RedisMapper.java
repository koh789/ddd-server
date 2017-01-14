package jp.ddd.server.redis.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * redis基底クラスです。
 *
 * @param <T>
 * @author noguchi_kohei
 */
public abstract class RedisMapper<T extends Serializable> {
  private Logger log = LoggerFactory.getLogger(this.getClass());
  @Autowired
  protected RedisTemplate<Key, T> template;

  /**
   * デフォルトexpire(seconds)
   */
  public final long expire = 24 * 60 * 60;

  /**
   * キー生成
   * entity毎にユニーク性を保持するためにentityClass名を
   * 同一entity内で複数キーが存在する場合はnameSpaceを使用してください。
   * ex) PostCheer.class, postId:10かつactionUserId:23の場合
   * key = PostCheer_postId<->actionUserId_10<->23
   * そうでない場合nameSpaceは使用しなくても構いません。
   *
   * @param entityClass set対象のdto
   * @param nameSpace   上記参照、必要ない場合getMethod名など各entityで適宜指定してください。
   * @param id          　キー
   * @return
   */
  protected Key generateStrKey(Class<T> entityClass, String nameSpace, Serializable identifier) {
    return new Key(entityClass.getSimpleName() + "_" + nameSpace + "_" + identifier.toString());
  }

  /**
   * expireをデフォルトを使用する場合コチラを使用してください。
   */
  protected void saveValue(Key key, T obj) {
    template.opsForValue().set(key, obj, expire, TimeUnit.SECONDS);
  }

  protected T getValue(Key key) {
    return template.opsForValue().get(key);
  }
}
