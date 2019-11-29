package yichen.yao.client.console;

import io.netty.channel.Channel;
import yichen.yao.protocol.request.NettyLoginRequest;

import java.util.Scanner;

/**
 * @Author: lancer.yao
 * @time: 2019/11/29:下午4:43
 */
public class LoginConsoleCommand implements ConsoleCommand{
    @Override
    public void exec(Scanner scanner, Channel channel) {
        NettyLoginRequest nettyLoginRequest = new NettyLoginRequest();

        System.out.print("输入用户名登录: ");
        nettyLoginRequest.setUsername(scanner.nextLine());
        nettyLoginRequest.setPassword("pwd");

        // 发送登录数据包
        channel.writeAndFlush(nettyLoginRequest);
        waitForLoginResponse();
    }

    private static void waitForLoginResponse() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
    }
}
