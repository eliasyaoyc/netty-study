package yichen.yao.client.console;

import io.netty.channel.Channel;
import yichen.yao.protocol.request.NettyJoinGroupRequest;

import java.util.Scanner;

public class JoinGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        NettyJoinGroupRequest nettyJoinGroupRequest = new NettyJoinGroupRequest();

        System.out.print("输入 groupId，加入群聊：");
        String groupId = scanner.next();

        nettyJoinGroupRequest.setGroupId(groupId);
        channel.writeAndFlush(nettyJoinGroupRequest);
    }
}
