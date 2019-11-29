package yichen.yao.protocol.response;

import lombok.Data;
import yichen.yao.protocol.NettyRequest;

import static yichen.yao.protocol.type.Type.LOGOUT_RESPONSE;

/**
 * @Author: lancer.yao
 * @time: 2019/11/29:下午4:29
 */
@Data
public class NettyLogoutResponse extends NettyRequest {
    private boolean success;

    private String reason;
    @Override
    public Byte getType() {
        return LOGOUT_RESPONSE;
    }
}
