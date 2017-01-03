package jp.ddd.server.web.json;

import java.io.Serializable;

import lombok.Data;

/**
 * @author noguchi_kohei
 */
@Data
public abstract class BaseJson implements Serializable {
  private static final long serialVersionUID = 1L;
}
