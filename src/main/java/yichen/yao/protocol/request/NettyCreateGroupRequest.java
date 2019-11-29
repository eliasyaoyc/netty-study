package yichen.yao.protocol.request;

import lombok.Data;
import yichen.yao.protocol.NettyRequest;

import java.util.List;

import static yichen.yao.protocol.type.Type.CREATE_GROUP_REQUEST;

/**
 * @Author: lancer.yao
 * @time: 2019/11/29:下午4:30
 */
@Data
public class NettyCreateGroupRequest extends NettyRequest {
    private List<String> userIdList;

    @Override
    public Byte getType() {
        return CREATE_GROUP_REQUEST;
    }
}
