package yichen.yao.attribute;

import io.netty.util.AttributeKey;
import yichen.yao.session.Session;

/**
 * @author yao
 * @date 2019/11/28 下午9:08
 */
public interface Attributes {

    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");

    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
