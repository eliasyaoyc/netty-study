package yichen.yao.protocol.response;

import lombok.Data;
import yichen.yao.protocol.NettyRequest;

import java.util.List;

import static yichen.yao.protocol.type.Type.CREATE_GROUP_RESPONSE;

/**
 * @Author: lancer.yao
 * @time: 2019/11/29:下午4:30
 */
@Data
public class NettyCreateGroupResponse extends NettyRequest {
    private boolean success;
    private String groupId;
    private List<String> userNameList;

    @Override
    public Byte getType() {
        return CREATE_GROUP_RESPONSE;
    }
}
