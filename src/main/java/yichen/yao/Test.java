package yichen.yao;

import java.util.Scanner;

/**
 * @Author: lancer.yao
 * @time: 2019/11/29 上午9:04
 */
public class Test {
    public static void main(String[] args) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
                System.out.println("请输入账号！ 以回车结束");
                Scanner accountScanner = new Scanner(System.in);
                String account = accountScanner.nextLine();
                System.out.println(account);
                System.out.println("请输入密码！ 以回车结束");
                Scanner passwordScanner = new Scanner(System.in);
                String password = passwordScanner.nextLine();
                System.out.println(password);
//            }
//        }).start();
    }
}
