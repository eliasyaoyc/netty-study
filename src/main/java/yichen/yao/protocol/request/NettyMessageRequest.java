package yichen.yao.protocol.request;

import lombok.Data;
import yichen.yao.protocol.NettyRequest;
import yichen.yao.protocol.type.Type;

/**
 * @Author: lancer.yao
 * @time: 2019/11/29 上午9:16
 */
@Data
public class NettyMessageRequest extends NettyRequest {

    private String message;

    @Override
    public Byte getType() {
        return Type.MESSAGE_REQUEST;
    }
}
