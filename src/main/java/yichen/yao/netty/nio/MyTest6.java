package yichen.yao.netty.nio;

import java.nio.ByteBuffer;

/**
 * @Author: lancer.yao
 * @time: 2020/1/7:下午1:29
 */
public class MyTest6 {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        for (int i = 0; i < byteBuffer.capacity(); ++i) {
            byteBuffer.put((byte) i);
        }
        byteBuffer.position(2);
        byteBuffer.limit(6);

        ByteBuffer slice = byteBuffer.slice();
        for(int i = 0; i < slice.capacity(); ++i){
            byte b = slice.get();
            b *= 2;
            slice.put(i,b);
        }

        byteBuffer.position(0);
        byteBuffer.limit(byteBuffer.capacity());

        while (byteBuffer.hasRemaining()){
            System.out.println(byteBuffer.get());
        }
    }
}
