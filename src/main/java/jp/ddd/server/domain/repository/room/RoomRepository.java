package jp.ddd.server.domain.repository.room;

import java.util.Optional;

import jp.ddd.server.domain.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Integer>, RoomRepositoryCtm {

  Optional<Room> findByIdAndDeleted(Integer id, byte deleted);

}
