package jp.ddd.server.domain.model.message;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by noguchi_kohei 
 */
@EqualsAndHashCode
@Data
@Embeddable
public class MessageDt implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "message_dt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date messageDt;

    @Column(name = "last_edit_dt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastEditDt;
}
