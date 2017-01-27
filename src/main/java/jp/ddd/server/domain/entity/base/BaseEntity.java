package jp.ddd.server.domain.entity.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import jp.ddd.server.utils.Dates;
import jp.ddd.server.utils.enums.Deleted;
import lombok.Data;

@Data
@MappedSuperclass
public class BaseEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "create_at")
  private Date createAt;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "update_at")
  private Date updateAt;

  @PrePersist
  public void setPrePersist() {
    this.createAt = Dates.now();
    this.updateAt = Dates.now();
  }

  @PreUpdate
  public void setPreUpdate() {
    this.updateAt = Dates.now();
  }
}
