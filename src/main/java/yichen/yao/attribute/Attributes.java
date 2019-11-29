package yichen.yao.attribute;

import io.netty.util.AttributeKey;

/**
 * @author yao
 * @date 2019/11/28 下午9:08
 */
public interface Attributes {

    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
}
