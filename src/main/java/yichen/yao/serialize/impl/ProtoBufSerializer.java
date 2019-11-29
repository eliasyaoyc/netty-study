package yichen.yao.serialize.impl;


import yichen.yao.serialize.Serializer;

/**
 * @Author: lancer.yao
 * @time: 2019/11/29:上午9:47
 */
public class ProtoBufSerializer implements Serializer {

    @Override
    public byte getSerializerAlgorithm() {
        return Serializer.protoBuf;
    }

    @Override
    public byte[] serialize(Object object) {
        return new byte[0];
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return null;
    }
}
