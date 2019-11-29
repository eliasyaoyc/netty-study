package yichen.yao.protocol.response;

import lombok.Data;
import yichen.yao.protocol.NettyRequest;

import static yichen.yao.protocol.type.Type.JOIN_GROUP_RESPONSE;

/**
 * @Author: lancer.yao
 * @time: 2019/11/29:下午5:06
 */
@Data
public class NettyJoinGroupResponse extends NettyRequest {
    private boolean success;
    private String groupId;
    private String reason;
    @Override
    public Byte getType() {
        return JOIN_GROUP_RESPONSE;
    }
}
