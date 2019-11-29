package yichen.yao.protocol.request;

import lombok.Data;
import yichen.yao.protocol.NettyRequest;
import yichen.yao.protocol.type.Type;

/**
 * @Author: lancer.yao
 * @time: 2019/11/29 上午9:15
 */
@Data
public class NettyLoginRequest extends NettyRequest {

    private String username;

    private String password;

    public NettyLoginRequest() {
    }

    public NettyLoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public Byte getType() {
        return Type.LOGIN_REQUEST;
    }
}
