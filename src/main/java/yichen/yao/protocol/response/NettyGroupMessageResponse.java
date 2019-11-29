package yichen.yao.protocol.response;

import lombok.Data;
import yichen.yao.protocol.NettyRequest;
import yichen.yao.session.Session;

import static yichen.yao.protocol.type.Type.GROUP_MESSAGE_RESPONSE;

/**
 * @Author: lancer.yao
 * @time: 2019/11/29:下午7:01
 */
@Data
public class NettyGroupMessageResponse extends NettyRequest {
    private String fromGroupId;
    private Session fromUser;
    private String message;

    @Override
    public Byte getType() {
        return GROUP_MESSAGE_RESPONSE;
    }
}
