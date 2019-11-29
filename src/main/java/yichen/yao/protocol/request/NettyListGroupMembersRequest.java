package yichen.yao.protocol.request;

import lombok.Data;
import yichen.yao.protocol.NettyRequest;

import static yichen.yao.protocol.type.Type.LIST_GROUP_MEMBERS_REQUEST;

/**
 * @Author: lancer.yao
 * @time: 2019/11/29:下午5:09
 */
@Data
public class NettyListGroupMembersRequest extends NettyRequest {
    private String groupId;
    @Override
    public Byte getType() {
        return LIST_GROUP_MEMBERS_REQUEST;
    }
}
