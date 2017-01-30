package jp.ddd.server.domain.model.room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by noguchi_kohei 
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class JoinDt implements Serializable {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "join_dt")
    private Date joinDt;
}
