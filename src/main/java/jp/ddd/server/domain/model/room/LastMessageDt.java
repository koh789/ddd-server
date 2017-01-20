package jp.ddd.server.domain.model.room;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by noguchi_kohei 
 */
@EqualsAndHashCode
@AllArgsConstructor
@Embeddable
public class LastMessageDt implements Serializable {

    @Getter
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastMessageDt;

}
