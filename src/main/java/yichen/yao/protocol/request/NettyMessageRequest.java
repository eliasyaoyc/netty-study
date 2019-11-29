package yichen.yao.protocol.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import yichen.yao.protocol.NettyRequest;
import yichen.yao.protocol.type.Type;

/**
 * @Author: lancer.yao
 * @time: 2019/11/29 上午9:16
 */
@Data
@NoArgsConstructor
public class NettyMessageRequest extends NettyRequest {

    private String toUserId;
    private String message;

    public NettyMessageRequest(String toUserId, String message) {
        this.toUserId = toUserId;
        this.message = message;
    }

    @Override
    public Byte getType() {
        return Type.MESSAGE_REQUEST;
    }
}
