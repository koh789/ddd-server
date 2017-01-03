package jp.ddd.server.domain.repository.base;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * redisにおけるkeyを表現します。
 *
 * @author noguchi_kohei
 */
@AllArgsConstructor
@Data
public class Key implements Serializable {

  private static final long serialVersionUID = 8287223606378011085L;
  private String key;

}
