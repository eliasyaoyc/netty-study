package yichen.yao.client.console;

import io.netty.channel.Channel;
import yichen.yao.protocol.request.NettyMessageRequest;

import java.util.Scanner;

/**
 * @Author: lancer.yao
 * @time: 2019/11/29:下午4:47
 */
public class SendToUserConsoleCommand implements ConsoleCommand{

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("发送消息给某个某个用户：");

        String toUserId = scanner.next();
        String message = scanner.next();
        channel.writeAndFlush(new NettyMessageRequest(toUserId, message));
    }
}
