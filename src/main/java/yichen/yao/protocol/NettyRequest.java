package yichen.yao.protocol;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @Author: lancer.yao
 * @time: 2019/11/29 上午9:14
 */
@Data
public abstract class NettyRequest {

    @JSONField(serialize = false)
    public abstract Byte getType();
}
