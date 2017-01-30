package jp.ddd.server.domain.model.user;

import jp.ddd.server.domain.model.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
})
public class User extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private byte deleted;

    private String email;

    @Column(name = "login_id")
    private String loginId;

    private String name;

    private String pass;

    private String tel;

}