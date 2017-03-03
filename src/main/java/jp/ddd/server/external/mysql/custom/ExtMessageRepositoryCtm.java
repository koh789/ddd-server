package jp.ddd.server.external.mysql.custom;

import jp.ddd.server.external.mysql.entity.ExtMessage;
import jp.ddd.server.other.data.common.Page;
import org.eclipse.collections.api.list.ImmutableList;

/**
 * Created by noguchi_kohei 
 */
public interface ExtMessageRepositoryCtm {

    ImmutableList<ExtMessage> findByRoomId(Integer roomId, Page page);

    /**
     * 指定メッセージルーム内で対象ユーザの未読リストを取得します。
     * @param roomId
     * @param userId
     * @return
     */
    ImmutableList<ExtMessage> findUnread(Integer roomId, Integer userId);
}
