package yichen.yao.util;

import io.netty.channel.Channel;
import io.netty.util.Attribute;
import yichen.yao.attribute.Attributes;
/**
 * @author yao
 * @date 2019/11/28 下午9:20
 */
public class LoginUtil {
    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel) {
        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);

        return loginAttr.get() != null;
    }
}
