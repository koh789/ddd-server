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

  @Column(name = "deleted")
  private byte deleted;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "create_time")
  private Date createTime;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "update_time")
  private Date updateTime;

  @PrePersist
  public void setPrePersist() {
    this.deleted = Deleted.PUBLIC.getCode();
    this.createTime = Dates.now();
    this.updateTime = Dates.now();
  }

  @PreUpdate
  public void setPreUpdate() {
    this.updateTime = Dates.now();
  }
}
