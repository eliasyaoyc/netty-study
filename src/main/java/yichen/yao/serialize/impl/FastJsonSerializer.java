package yichen.yao.serialize.impl;


import com.alibaba.fastjson.JSON;
import yichen.yao.serialize.Serializer;

/**
 * @Author: lancer.yao
 * @time: 2019/11/29:上午9:47
 */
public class FastJsonSerializer implements Serializer {

    @Override
    public byte getSerializerAlgorithm() {
        return Serializer.fastJSON;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes,clazz);
    }
}
