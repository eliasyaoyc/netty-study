package yichen.yao.netty.nio;

import javax.xml.transform.Source;
import java.nio.IntBuffer;
import java.security.SecureRandom;

/**
 * @Author: lancer.yao
 * @time: 2020/1/7:上午11:09
 */
public class NioTest1 {
    public static void main(String[] args) {
        IntBuffer buffer = IntBuffer.allocate(10);
        System.out.println("capacity: " + buffer.capacity());
        for(int i = 0; i < buffer.capacity(); ++i){
            int randomNumber = new SecureRandom().nextInt(20);
            buffer.put(randomNumber);
        }
        System.out.println("before flip limit: " + buffer.limit());
        buffer.flip();
        System.out.println("after flip limit: " + buffer.limit());
        System.out.println("enter while loop");
        while (buffer.hasRemaining()){
            System.out.println("position: " + buffer.position());
            System.out.println("limit: " + buffer.limit());
            System.out.println("capacity: " + buffer.capacity());
            System.out.println(buffer.get());
        }
    }
}
