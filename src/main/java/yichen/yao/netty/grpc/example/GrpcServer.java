package yichen.yao.netty.grpc.example;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

/**
 * @Author: lancer.yao
 * @time: 2020/1/7:上午9:53
 */
public class GrpcServer {
    private Server server;
    private void start() throws IOException {
        this.server = ServerBuilder.forPort(8899).addService(new StudentServiceImpl()).build().start();
        System.out.println("服务器启动");
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println("关闭jvm");
            GrpcServer.this.stop();
        }));
        System.out.println("执行到这里");
    }
    private void stop(){
        if(null != server){
            this.server.shutdown();
        }
    }
    private void awaitTermination() throws InterruptedException {
        if(null != this.server){
            this.server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        GrpcServer server = new GrpcServer();
        server.start();
        server.awaitTermination();
    }
}
