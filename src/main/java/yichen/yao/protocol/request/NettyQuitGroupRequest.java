package yichen.yao.protocol.request;

import lombok.Data;
import yichen.yao.protocol.NettyRequest;

import static yichen.yao.protocol.type.Type.QUIT_GROUP_REQUEST;

/**
 * @Author: lancer.yao
 * @time: 2019/11/29:下午5:10
 */
@Data
public class NettyQuitGroupRequest extends NettyRequest {
    private String groupId;
    @Override
    public Byte getType() {
        return QUIT_GROUP_REQUEST;
    }
}
