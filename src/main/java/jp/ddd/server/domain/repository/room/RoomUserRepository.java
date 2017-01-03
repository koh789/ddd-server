package jp.ddd.server.domain.repository.room;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.ddd.server.domain.entity.RoomUser;

public interface RoomUserRepository extends JpaRepository<RoomUser, Integer>, RoomUserRepositoryCtm {

}
