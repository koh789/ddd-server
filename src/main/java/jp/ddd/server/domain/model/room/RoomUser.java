package jp.ddd.server.domain.model.room;

import jp.ddd.server.domain.model.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * The persistent class for the room database table.
 */
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "room_user")
@Entity
@NamedQueries({ //
})
public class RoomUser extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private byte deleted;

    @Embedded
    private JoinDt joinDt;

    private String name;

    @Column(name = "room_id")
    private Integer room_id;

    @Column(name = "user_id")
    private Integer userId;
}