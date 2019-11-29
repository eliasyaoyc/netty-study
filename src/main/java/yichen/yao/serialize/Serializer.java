package yichen.yao.serialize;

import yichen.yao.serialize.impl.FastJsonSerializer;

/**
 * @Author: lancer.yao
 * @time: 2019/11/29:上午9:46
 */
public interface Serializer {

    byte fastJSON = 1;
    byte protoBuf = 2;


    Serializer DEFAULT = new FastJsonSerializer();

    /**
     * 序列化算法
     * @return
     */
    byte getSerializerAlgorithm();

    /**
     * java 对象转换成二进制
     */
    byte[] serialize(Object object);

    /**
     * 二进制转换成 java 对象
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
