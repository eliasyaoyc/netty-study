package yichen.yao.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import yichen.yao.protocol.request.NettyCreateGroupRequest;
import yichen.yao.protocol.response.NettyCreateGroupResponse;
import yichen.yao.util.SessionUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author: lancer.yao
 * @time: 2019/11/29:下午4:35
 */
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<NettyCreateGroupRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, NettyCreateGroupRequest msg) throws Exception {
        List<String> userIdList = msg.getUserIdList();

        List<String> userNameList = new ArrayList<>();
        // 1. 创建一个 channel 分组
        ChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());
        // 2. 筛选出待加入群聊的用户的 channel 和 userName
        for (String userId : userIdList) {
            Channel channel = SessionUtil.getChannel(userId);
            if(channel != null){
                channelGroup.add(channel);
                userNameList.add(SessionUtil.getSession(channel).getUserName());
            }
        }
        // 3. 创建群聊创建结果的响应
        NettyCreateGroupResponse createGroupResponse = new NettyCreateGroupResponse();
        createGroupResponse.setSuccess(true);
        createGroupResponse.setGroupId(UUID.randomUUID().toString().split("-")[0]);
        createGroupResponse.setUserNameList(userNameList);

        // 4. 给每个客户端发送拉群通知
        channelGroup.writeAndFlush(createGroupResponse);

        System.out.print("群创建成功，id 为[" + createGroupResponse.getGroupId() + "], ");
        System.out.println("群里面有：" + createGroupResponse.getUserNameList());

    }
}
