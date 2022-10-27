package com.cv.studentsystem;

import java.util.ArrayList;
import java.util.Scanner;

public class MainInterface {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<User> userList = new ArrayList<>();
        //添加初始学生信息（目前阶段暂不用完善，只完成登录、注册、忘记密码功能，后续考虑删、改功能）
        userList.add(new User("zhangsan", "zs123456", "15289293733", "610222199901010123"));
        userList.add(new User("lisi", "ls123456", "15289293734", "610222199901010124"));
        userList.add(new User("wangwu", "ww123456", "15289293735", "610222199901010125"));
        // 登录提示
        label:
        while (true) {
            System.out.println("==========菜单==========");
            System.out.println("   注册账户请键入【1】\n   登录账户请键入【2】\n   忘记密码请键入【3】\n 结束系统请键入其他数字");
            System.out.println("========================");
            System.out.print("请输入：");
            int click = sc.nextInt();
            switch (click) {
                case 1:
                    RegisterInterfance.register(userList);
                    break;
                case 2:
                    LogInInterface.login(userList);
                    break;
                case 3:
                    ForgetInterface.forget(userList);
                default:
                    break label;
            }
        }
    }

}
