package jp.ddd.server.domain.model.user;

<<<<<<< HEAD:src/main/java/jp/ddd/server/domain/model/user/User.java
import jp.ddd.server.domain.model.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
=======
import jp.ddd.server.domain.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
>>>>>>> 6941ca9919d255ef643c1bc0b48a2a836e5e0ed7:src/main/java/jp/ddd/server/domain/entity/User.java
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * The persistent class for the user database table.
 */
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@NamedQueries({// 
  @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),//
  @NamedQuery(name = "User.findByIdList",//
    query = "SELECT u FROM User u WHERE u.id in (:idList) AND u.deleted = :deleted")//
})
public class User extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private byte deleted;

    private String email;

    @Column(name = "login_id")
    private String loginId;

    private String name;

    private String pass;

    private String tel;

}