package jp.ddd.server.adapter.gateway.dynamodb.table.id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by noguchi_kohei 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomUserId implements Serializable {
    private static final long serialVersionUID = 5232389369383066987L;
    private Integer roomId;

    private Integer userId;
}
