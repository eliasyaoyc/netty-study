package yichen.yao.protocol.request;

import yichen.yao.protocol.NettyRequest;

import static yichen.yao.protocol.type.Type.LOGOUT_REQUEST;

/**
 * @Author: lancer.yao
 * @time: 2019/11/29:下午4:29
 */
public class NettyLogoutRequest extends NettyRequest {
    @Override
    public Byte getType() {
        return LOGOUT_REQUEST;
    }
}
