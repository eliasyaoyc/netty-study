package yichen.yao.protocol.response;

import lombok.Data;
import yichen.yao.protocol.NettyRequest;
import yichen.yao.protocol.type.Type;

/**
 * @Author: lancer.yao
 * @time: 2019/11/29 上午9:16
 */
@Data
public class NettyMessageResponse extends NettyRequest {

    private String fromUserId;

    private String fromUserName;

    private String message;

    @Override
    public Byte getType() {
        return Type.MESSAGE_RESPONSE;
    }
}
