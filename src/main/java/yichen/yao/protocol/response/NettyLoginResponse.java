package yichen.yao.protocol.response;

import lombok.Data;
import yichen.yao.protocol.NettyRequest;
import yichen.yao.protocol.type.Type;

/**
 * @Author: lancer.yao
 * @time: 2019/11/29 上午9:15
 */
@Data
public class NettyLoginResponse extends NettyRequest {
    private boolean success;

    private String reason;

    @Override
    public Byte getType() {
        return Type.LOGIN_RESPONSE;
    }
}
