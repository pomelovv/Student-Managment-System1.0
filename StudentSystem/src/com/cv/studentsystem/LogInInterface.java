package com.cv.studentsystem;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class LogInInterface {

    //登录系统
    /*要求：1，键盘录入账号密码及验证码
            2，键盘录入用户名，用户名如果未注册，直接结束，并提示：用户名未注册，请先注册
            3，判断验证码是否正确，如不正确，重新输入
            4，判断用户名和密码是否匹配，有3次机会，机会用完后选择忘记密码或者跳出系统
    */


    public static void login(ArrayList<User> userList) {
        Scanner sc = new Scanner(System.in);
        int index = -1;
        boolean flag = false;
        System.out.println("===已进入账号登录系统===");
        label1:
        while (true) {
            System.out.println("请输入用户名：");
            String userName = sc.next();
            for (int i = 0; i < userList.size(); i++) {
                if (userName.equals(userList.get(i).getUserName())) {
                    index = i;
                    flag = true;
                    break;
                } else if (userList.get(i).getUserName().substring(0, 1).equals("+")) {
                    System.out.println("账号处于锁定状态，无权登录");
                    System.out.println("键入【1】进行验证解锁，键入其他以返回主界面");
                    if (sc.next().equals("1")) {
                        ForgetInterface.forget(userList);
                        return;
                    }
                    else
                        return;
                }
            }
            //判断用户名是否存在，如果不存在可以选择重新输入、注册账号、返回主界面
            if (!flag) {
                System.out.println("未查询到用户名");
                System.out.println("键入【1】以重新输入，键入【2】以进入注册系统，键入其他以返回主界面");
                String str = sc.next();
                //键入1以重新输入
                if (str.equals("1"))
                    continue label1;
                    //键入2以进入注册系统，键入其他以返回主界面
                else if (str.equals("2")) {
                    RegisterInterfance.register(userList);
                    return;
                } else
                    return;
            } else
                break label1;
        }
        //用户名存在，输入验证码（限制条件较多，放在GetCaptcha方法中）
        //验证码规则：由4位大写或者小写字母和1位数字组成，同一个字母可重复，数字可出现在任意位置
        while (true) {
            String captcha = GetCaptcha();
            System.out.println("验证码为：" + captcha);
            System.out.println("请键入验证码以进行机器人验证：");
            String str = sc.next();
            if (str.equals(captcha)){
                System.out.println("恭喜您通过机器人验证");
                break;
            }else {
                System.out.println("验证码输入错误，已重新生成：");
            }
        }
        //判断用户名和密码是否匹配，有3次机会，机会用完后选择忘记密码或者跳出系统

        for (int i = 2; i >= 0; i--) {
            System.out.println("请输入密码：");
            String passwordInput = sc.next();
            if (passwordInput.equals(userList.get(index).getPassword())){
                System.out.println("恭喜您，密码正确");
                System.out.println("=====已成功登入系统=====");
                Management.management();
                return;
            }else {
                System.out.println("密码错误。您还有【" + i + "】次输入机会");
            }
        }
        String userName = userList.get(index).getUserName();
        userList.get(index).setUserName("+" + userName);
        System.out.println("抱歉，输入次数已用完，您的账号已被锁定，键入【1】进行验证解锁，键入其他以返回主界面");
        if (sc.next().equals("1")) {
            ForgetInterface.forget(userList);
            return;
        }
        else
            return;
    }

    //验证码获取
    public static String GetCaptcha() {
        Random r = new Random();
        char[] captchaArry = new char[5];
        //构建一个包含所有大小写字母的数组
        char[] arr = new char[52];
        for (int i = 0; i < arr.length; i++) {
            if (i < 26)
                arr[i] = (char) ('A' + i);
            else
                arr[i] = (char) ('a' + i - 26);
        }
        //将验证码数组全部赋予英文大小写
        for (int i = 0; i < captchaArry.length; i++) {
            captchaArry[i] = arr[r.nextInt(0, 52)];
        }
        //将验证码数组的任一位更换为数字
        captchaArry[r.nextInt(0, 5)] = (char)('0' + r.nextInt(0, 10));
        //将验证码数组转换为字符串返回
        return new String(captchaArry);
    }

}

