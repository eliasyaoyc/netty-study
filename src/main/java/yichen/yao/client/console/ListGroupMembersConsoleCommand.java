package yichen.yao.client.console;

import io.netty.channel.Channel;
import yichen.yao.protocol.request.NettyListGroupMembersRequest;

import java.util.Scanner;

public class ListGroupMembersConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        NettyListGroupMembersRequest listGroupMembersRequest = new NettyListGroupMembersRequest();

        System.out.print("输入 groupId，获取群成员列表：");
        String groupId = scanner.next();

        listGroupMembersRequest.setGroupId(groupId);
        channel.writeAndFlush(listGroupMembersRequest);
    }
}
