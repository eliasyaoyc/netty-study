package yichen.yao.client.console;

import io.netty.channel.Channel;
import yichen.yao.protocol.request.NettyCreateGroupRequest;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: lancer.yao
 * @time: 2019/11/29:下午4:47
 */
public class CreateGroupConsoleCommand implements  ConsoleCommand{
    @Override
    public void exec(Scanner scanner, Channel channel) {
        NettyCreateGroupRequest createGroupRequest = new NettyCreateGroupRequest();
        System.out.println("【拉人群聊】输入 userId 列表，userId 之间英文逗号隔开：");
        String userIds = scanner.next();
        createGroupRequest.setUserIdList(Arrays.asList(userIds.split(",")));
        channel.writeAndFlush(createGroupRequest);
    }
}
