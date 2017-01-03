package jp.ddd.server.web.json;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorJson implements Serializable {

  private static final long serialVersionUID = 1L;

  private int code;

  private String msg;

}
