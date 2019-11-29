package yichen.yao.protocol.codec;

import io.netty.buffer.ByteBuf;
import yichen.yao.protocol.NettyRequest;
import yichen.yao.protocol.request.NettyLoginRequest;
import yichen.yao.protocol.request.NettyMessageRequest;
import yichen.yao.protocol.response.NettyLoginResponse;
import yichen.yao.protocol.response.NettyMessageResponse;
import yichen.yao.serialize.Serializer;
import yichen.yao.serialize.impl.FastJsonSerializer;
import yichen.yao.serialize.impl.ProtoBufSerializer;

import java.util.HashMap;
import java.util.Map;

import static yichen.yao.protocol.type.Type.*;

/**
 * @Author: lancer.yao
 * @time: 2019/11/29:上午9:42
 * 编解码
 */
public class NettyRequestCodec {

    public static final NettyRequestCodec INSTANCE = new NettyRequestCodec();

    private final Map<Byte, Class<? extends NettyRequest>> requestTypeMap;
    private final Map<Byte, Serializer> serializerMap;

    private NettyRequestCodec(){
        requestTypeMap = new HashMap<>();
        requestTypeMap.put(LOGIN_REQUEST, NettyLoginRequest.class);
        requestTypeMap.put(LOGIN_RESPONSE, NettyLoginResponse.class);
        requestTypeMap.put(MESSAGE_REQUEST, NettyMessageRequest.class);
        requestTypeMap.put(MESSAGE_RESPONSE, NettyMessageResponse.class);

        serializerMap = new HashMap<>();
        Serializer fast = new FastJsonSerializer();
        serializerMap.put(fast.getSerializerAlgorithm(), fast);
        Serializer protoBuf = new ProtoBufSerializer();
        serializerMap.put(protoBuf.getSerializerAlgorithm(), protoBuf);
    }

    //编码
    public ByteBuf encode(ByteBuf byteBuf,NettyRequest nettyRequest){
        byte[] serialize = Serializer.DEFAULT.serialize(nettyRequest);
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(nettyRequest.getType());
        byteBuf.writeInt(serialize.length);
        byteBuf.writeBytes(serialize);
        return byteBuf;
    }

    //解码
    public NettyRequest decode(ByteBuf byteBuf){
        byte serializerAlgorithm = byteBuf.readByte();
        byte type = byteBuf.readByte();
        int length = byteBuf.readInt();
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Class<? extends NettyRequest> requestType = getRequestType(type);
        Serializer serializer = getSerializer(serializerAlgorithm);

        if (requestType != null && serializer != null) {
            return serializer.deserialize(requestType, bytes);
        }
        return null;
    }

    private Serializer getSerializer(byte serializeAlgorithm) {

        return serializerMap.get(serializeAlgorithm);
    }

    private Class<? extends NettyRequest> getRequestType(byte type) {

        return requestTypeMap.get(type);
    }
}
