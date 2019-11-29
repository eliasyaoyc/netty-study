package yichen.yao.protocol.type;

/**
 * @Author: lancer.yao
 * @time: 2019/11/29:上午9:36
 */
public interface Type {
    Byte LOGIN_REQUEST = 1;

    Byte LOGIN_RESPONSE = 2;

    Byte MESSAGE_REQUEST = 3;

    Byte MESSAGE_RESPONSE = 4;

    Byte LOGOUT_REQUEST = 5;

    Byte LOGOUT_RESPONSE = 6;

    Byte CREATE_GROUP_REQUEST = 7;

    Byte CREATE_GROUP_RESPONSE = 8;
}
