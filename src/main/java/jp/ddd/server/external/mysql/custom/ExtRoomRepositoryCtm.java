package jp.ddd.server.external.mysql.custom;

import jp.ddd.server.external.mysql.entity.ExtRoom;
import org.eclipse.collections.api.list.ImmutableList;

import java.util.Optional;

/**
 * Created by noguchi_kohei 
 */
public interface ExtRoomRepositoryCtm {

    ImmutableList<ExtRoom> findByDtDesc(Integer userId);

    Optional<ExtRoom> getOpt(Integer id);

}
