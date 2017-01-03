package jp.ddd.server.domain.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Test implements Serializable {
  private Long id;
  private String text;
  private String info;

}
