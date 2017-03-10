package jp.ddd.server.adapter.gateway.rds.custom;

import jp.ddd.server.adapter.gateway.rds.entity.MessageExt;
import jp.ddd.server.other.data.common.Page;
import org.eclipse.collections.api.list.ImmutableList;

/**
 * Created by noguchi_kohei 
 */
public interface MessageRdsCtm {

    ImmutableList<MessageExt> findByRoomId(Integer roomId, Page page);

    /**
     * 指定メッセージルーム内で対象ユーザの未読リストを取得します。
     * @param roomId
     * @param userId
     * @return
     */
    ImmutableList<MessageExt> findUnread(Integer roomId, Integer userId);
}
