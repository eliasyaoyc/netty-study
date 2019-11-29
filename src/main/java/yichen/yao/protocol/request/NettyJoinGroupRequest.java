package yichen.yao.protocol.request;

import lombok.Data;
import yichen.yao.protocol.NettyRequest;

import static yichen.yao.protocol.type.Type.JOIN_GROUP_REQUEST;

/**
 * @Author: lancer.yao
 * @time: 2019/11/29:下午5:06
 */
@Data
public class NettyJoinGroupRequest extends NettyRequest {
    private String groupId;
    @Override
    public Byte getType() {
        return JOIN_GROUP_REQUEST;
    }
}
