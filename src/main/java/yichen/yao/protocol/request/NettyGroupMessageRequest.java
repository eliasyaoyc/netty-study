package yichen.yao.protocol.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import yichen.yao.protocol.NettyRequest;

import static yichen.yao.protocol.type.Type.GROUP_MESSAGE_REQUEST;

/**
 * @Author: lancer.yao
 * @time: 2019/11/29:下午7:00
 */
@Data
@NoArgsConstructor
public class NettyGroupMessageRequest extends NettyRequest {
    private String toGroupId;
    private String message;

    public NettyGroupMessageRequest(String toGroupId, String message) {
        this.toGroupId = toGroupId;
        this.message = message;
    }

    @Override
    public Byte getType() {
        return GROUP_MESSAGE_REQUEST;
    }
}
