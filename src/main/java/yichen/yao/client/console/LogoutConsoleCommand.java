package yichen.yao.client.console;

import io.netty.channel.Channel;
import yichen.yao.protocol.request.NettyLogoutRequest;

import java.util.Scanner;

/**
 * @Author: lancer.yao
 * @time: 2019/11/29:下午4:47
 */
public class LogoutConsoleCommand implements  ConsoleCommand{
    @Override
    public void exec(Scanner scanner, Channel channel) {
        NettyLogoutRequest nettyLogoutRequest = new NettyLogoutRequest();
        channel.writeAndFlush(nettyLogoutRequest);
    }
}
