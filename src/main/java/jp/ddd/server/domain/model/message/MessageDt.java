package jp.ddd.server.domain.model.message;

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
public class MessageDt implements Serializable {
    private static final long serialVersionUID = 1L;

    @Getter
    @Temporal(TemporalType.TIMESTAMP)
    private Date messageDt;
}
