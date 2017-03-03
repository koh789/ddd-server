package jp.ddd.server.external.mysql.entity;

import jp.ddd.server.adapter.gateway.external.rdb.ExtUserRepository;
import jp.ddd.server.domain.model.user.User;
import jp.ddd.server.external.mysql.entity.base.BaseEntity;
import jp.ddd.server.other.utils.Hashes;
import jp.ddd.server.other.utils.enums.Status;
import lombok.*;
import org.eclipse.collections.api.list.ImmutableList;

import javax.persistence.*;
import java.util.Optional;

/**
 * The persistent class for the user database table.
 */
@EqualsAndHashCode(callSuper = false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@NamedQueries({//
  @NamedQuery(name = "User.getByLid", query = "SELECT u FROM User u WHERE u.loginId=:lid"),
  @NamedQuery(name = "User.getByLidAndPassAndStatus", query = "SELECT u FROM User u WHERE u.loginId=:lid AND u.pass=:pass AND u.status=:status"),
  @NamedQuery(name = "User.findByIdsAndStatus", query = "SELECT u FROM User u WHERE u.id IN (:ids) AND u.status=:status") })
public class ExtUser extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;

    @Column(name = "login_id")
    private String loginId;

    private String name;

    private String pass;
    @Enumerated(EnumType.ORDINAL)
    private Status status;

    private String tel;

    public static ExtUser create(User user) {

        return ExtUser.builder().email(user.getUserInfo().getEmail()).status(user.getStatus())
          .loginId(user.getLoginId().getId()).name(user.getUserInfo().getName()).pass(user.getHashPass().getPass())
          .tel(user.getUserInfo().getTel()).build();
    }

    public static Optional<ExtUser> getOpt(ExtUserRepository rep, String loginId, String pass) {
        val hashedPass = Hashes.toSHA256(pass);
        return rep.getOpt(loginId, hashedPass);
    }

    public static ImmutableList<ExtUser> find(ImmutableList<Integer> userIds, ExtUserRepository rep) {
        return rep.find(userIds);
    }
}