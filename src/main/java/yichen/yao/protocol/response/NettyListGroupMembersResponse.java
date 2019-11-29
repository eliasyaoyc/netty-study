package yichen.yao.protocol.response;

import lombok.Data;
import yichen.yao.protocol.NettyRequest;
import yichen.yao.session.Session;

import java.util.List;

import static yichen.yao.protocol.type.Type.LIST_GROUP_MEMBERS_RESPONSE;

/**
 * @Author: lancer.yao
 * @time: 2019/11/29:下午5:09
 */
@Data
public class NettyListGroupMembersResponse extends NettyRequest {
    private String groupId;
    private List<Session> sessionList;
    @Override
    public Byte getType() {
        return LIST_GROUP_MEMBERS_RESPONSE;
    }
}
