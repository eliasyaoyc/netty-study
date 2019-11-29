package yichen.yao.client.console;

import io.netty.channel.Channel;
import yichen.yao.protocol.request.NettyGroupMessageRequest;
import yichen.yao.protocol.request.NettyMessageRequest;

import java.util.Scanner;

/**
 * @Author: lancer.yao
 * @time: 2019/11/29:下午6:59
 */
public class SendToGroupConsoleCommand implements ConsoleCommand{
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("发送消息给某个某个群组：");

        String toGroupId = scanner.next();
        String message = scanner.next();
        channel.writeAndFlush(new NettyGroupMessageRequest(toGroupId, message));
    }
}
