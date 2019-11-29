package yichen.yao.client.console;

import io.netty.channel.Channel;
import yichen.yao.protocol.request.NettyQuitGroupRequest;

import java.util.Scanner;

public class QuitGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        NettyQuitGroupRequest nettyQuitGroupRequest = new NettyQuitGroupRequest();

        System.out.print("输入 groupId，退出群聊：");
        String groupId = scanner.next();

        nettyQuitGroupRequest.setGroupId(groupId);
        channel.writeAndFlush(nettyQuitGroupRequest);
    }
}
