package com.cv.studentsystem;

import java.util.ArrayList;
import java.util.Scanner;

public class ForgetInterface {

    /*1,键盘录入用户名，判断当前用户名是否存在，如不存在，直接结束方法，并提示：未注册或已锁定
      2，如果存在或锁定：键盘录入身份证号码和手机号码
      3，判断当前用户的身份证号码和手机号码是否一致，一致可以修改密码

     */
    public static void forget(ArrayList<User> userList) {
        Scanner sc = new Scanner(System.in);
        int index = -1;
        boolean flag = false;
        System.out.println("===已进入账号找回系统===");
        label1:
        while (true) {
            System.out.println("请输入用户名：");
            String userName = sc.next();
            for (int i = 0; i < userList.size(); i++) {
                if (userName.equals(userList.get(i).getUserName())) {
                    index = i;
                    flag = true;
                    System.out.println("已检索到用户名：" + userName);
                    break;
                } else if (userList.get(i).getUserName().substring(0, 1).equals("+")) {
                    index = i;
                    System.out.println("该账号处于锁定状态");
                    flag = true;
                    break;
                }
            }
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
        //如果存在或锁定：键盘录入身份证号码和手机号码
        label2:
        while (true) {
            System.out.println("键入身份证号码");
            String idNum = sc.next();
            if (userIdNumCheck(idNum, userList)) {
                if (idNum.equals(userList.get(index).getIdNum())) {
                    System.out.println("身份证号码验证通过");
                    break label2;
                } else {
                    System.out.println("身份证号码未验证通过，键入【1】以重新输入，键入其他以返回主界面");
                    if (sc.next().equals("1"))
                        continue label2;
                    else
                        return;
                }
            } else {
                System.out.println("身份证号码输入格式不合规范，请重新输入：");
                continue label2;
            }
        }
        //手机号码验证
        label3:
        while (true) {
            System.out.println("键入手机号码");
            String phoneNum = sc.next();
            if (RegisterInterfance.userPhoneNumCheck(phoneNum, userList)) {
                if (phoneNum.equals(userList.get(index).getPhoneNum())) {
                    System.out.println("手机号码验证通过");
                    break label3;
                } else {
                    System.out.println("手机号码未验证通过，键入【1】以重新输入，键入其他以返回主界面");
                    if (sc.next().equals("1"))
                        continue label3;
                    else
                        return;
                }
            } else {
                System.out.println("手机号码输入格式不合规范，请重新输入：");
                continue label3;
            }
        }
        //若账号锁定的话,需要解锁
        System.out.println("恭喜，验证全部通过");
        if (userList.get(index).getUserName().substring(0, 1).equals("+")) {
            String userNameModify = userList.get(index).getUserName().substring(1);
            userList.get(index).setUserName(userNameModify);
            System.out.println("======账户已解锁======");
            System.out.println("正在返回主界面>>>>");
            return;
        }
        System.out.println("按照提示修改密码：");
        System.out.println("======================");
        label4:
        while (true) {
            System.out.println("请输入密码");
            String password1 = sc.next();
            System.out.println("请再次输入密码");
            String password2 = sc.next();
            if (password2.equals(password1)) {
                System.out.println("密码校验中>>>>");
                System.out.println("======密码修改成功======");
                System.out.println("正在返回主界面>>>>");
                userList.get(index).setPassword(password1);
                return;
            } else {
                System.out.println("两次密码不一致，键入【1】以重新输入，键入其他以返回主界面");
                if (sc.next().equals("1"))
                    continue label4;
                else
                    return;
            }
        }

    }
    public static boolean userIdNumCheck(String idNum, ArrayList<User> userList) {
        boolean flag = false;
        // 身份证号码长度判断
        if (idNum.length() != 18) {
            System.out.println("身份证号码长度不合法");
            return false;
        }
        // 身份证号码非法字符判断
        //首位非0判断（不写在循环里，防止资源浪费）
        if (idNum.charAt(0) == '0') {
            System.out.println("非法：身份证号码首位不可为0");
            return false;
        }
        //前17位数字判断
        for (int i = 0; i < idNum.length() - 1; i++) {
            if (idNum.charAt(i) < '0' || idNum.charAt(i) > '9'){
                System.out.println("非法：身份证号码前17位必须为数字");
                return false;
            }
        }
        //末位判断（不写在循环里，防止资源浪费）
        if (idNum.charAt(17) == 'X' || idNum.charAt(17) == 'x' || (idNum.charAt(17) >= '0' && idNum.charAt(17) <= '9')) {
        } else{
            System.out.println("非法：身份证号码第18位必须为数字或英文字母X（不区分大小写）");
            return false;
        }
        return true;
    }

}
