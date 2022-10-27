package com.cv.studentsystem;

import java.util.ArrayList;
import java.util.Scanner;

public class RegisterInterfance {

    //注册系统
    /*要求：1.用户名唯一，用户名可以为字母或数字，但不能是纯数字，用户名长度在3-15位之间
           2.密码需要输入两次，两次结果一样时方可
           3.身份证号码需要验证：长度为18位，不能以0开头，最后一位可以是x，不能和已有身份证号重复
           4.手机号需要验证：长度为11位的数字，不能以0开头
    */
    public static void register(ArrayList<User> userList) {
        Scanner sc = new Scanner(System.in);
        User us1 = new User();
        System.out.println("===已进入账号注册系统===");
        //用户名输入（用户名限制条件较多，用方法userNameCheck来判断输入是否合法）
        label1:
        while (true) {
            System.out.println("请输入用户名：（提示：用户名可以为数字与字母的组合，但不能为纯数字，用户名长度为3-15位。）");
            String userNam = sc.next();
            //用户名不合法时，选择重新输入或结束输入;用户名合法时,将用户名存入。
            if (!userNameCheck(userNam, userList)) {
                System.out.println("键入【1】以重新输入，键入其他以返回主界面");
                if (sc.next().equals("1"))
                    continue label1;
                else
                    return;
            } else {
                System.out.println("用户名校验中>>>>");
                System.out.println("用户名录入成功");
                us1.setUserName(userNam);
                break;
            }
        }
        //密码输入
        label2:
        while (true) {
            System.out.println("请输入密码");
            String password1 = sc.next();
            System.out.println("请再次输入密码");
            String password2 = sc.next();
            if (password2.equals(password1)) {
                System.out.println("密码校验中>>>>");
                System.out.println("密码录入成功");
                us1.setPassword(password1);
                break;
            } else {
                System.out.println("两次密码不一致，键入【1】以重新输入，键入其他以返回主界面");
                if (sc.next().equals("1"))
                    continue label2;
                else
                    return;
            }
        }
        //身份证号码验证（身份证号码限制条件较多，用方法userIdNumCheck来判断输入是否合法）
        label3:
        while (true) {
            System.out.println("请输入身份证号码");
            String idNum = sc.next();
            if (!userIdNumCheck(idNum, userList)) {
                System.out.println("键入【1】以重新输入，键入其他以返回主界面");
                if (sc.next().equals("1"))
                    continue label3;
                else
                    return;
            } else {
                System.out.println("身份证号码校验中>>>>");
                System.out.println("身份证号码录入成功");
                us1.setIdNum(idNum);
                break;
            }
        }

        //手机号码验证（手机号码限制条件较多，用方法userPhoneNumCheck来判断输入是否合法）
        label4:
        while (true) {
            System.out.println("请输入手机号码");
            String phoneNum = sc.next();
            if (!userPhoneNumCheck(phoneNum, userList)) {
                System.out.println("键入【1】以重新输入，键入其他以返回主界面");
                if (sc.next().equals("1"))
                    continue label4;
                else
                    return;
            } else {
                System.out.println("手机号码校验中>>>>");
                System.out.println("手机号码录入成功");
                us1.setPhoneNum(phoneNum);
                break;
            }
        }
        System.out.println("========注册成功========");
        System.out.println("正在返回主界面>>>>");
        userList.add(us1);
        return;
    }


    //用户名合法性判断
    public static boolean userNameCheck(String userNam, ArrayList<User> userList) {
        Scanner sc = new Scanner(System.in);
        boolean flag = false;
        // 用户名长度判断
        int len = userNam.length();
        if (len < 3 || len > 15) {
            System.out.println("用户名长度不合法");
            return false;
        }
        //用户名重复判断
        for (int i = 0; i < userList.size(); i++) {
            if (userNam.equals(userList.get(i).getUserName())) {
                System.out.println("用户名重复");
                return false;
            }
        }
        //用户名非法字符判断
        for (int i = 0; i < userNam.length(); i++) {
            String userNamCapital = userNam.toUpperCase();
            if (userNamCapital.charAt(i) > 'A' && userNamCapital.charAt(i) < 'Z') {
                flag = true;
            } else if (userNamCapital.charAt(i) < '0' || (userNamCapital.charAt(i) > '9' && userNamCapital.charAt(i) < 'A') || userNamCapital.charAt(i) > 'Z') {
                System.out.println("非法：用户名存在非法字符");
                return false;
            }
        }
        if (flag)
            return true;
        else {
            System.out.println("非法：用户名为纯数字");
            return false;
        }
    }

    //身份证号码合法性判断
    public static boolean userIdNumCheck(String idNum, ArrayList<User> userList) {
        boolean flag = false;
        // 身份证号码长度判断
        if (idNum.length() != 18) {
            System.out.println("身份证号码长度不合法");
            return false;
        }
        //身份证号码重复判断
        for (int i = 0; i < userList.size(); i++) {
            if (idNum.equals(userList.get(i).getIdNum())) {
                System.out.println("身份证号码重复");
                return false;
            }
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

    //手机号码验证
    public static boolean userPhoneNumCheck(String phoneNum, ArrayList<User> userList) {
        //手机号码长度判断（不写在循环里，防止资源浪费）
        if (phoneNum.length() != 11) {
            System.out.println("手机号码长度不合法");
            return false;
        }
        //首位非0判断（不写在循环里，防止资源浪费）
        if (phoneNum.charAt(0) == 0) {
            System.out.println("非法：手机号码首位不得为0");
        }
        //数字判断
        for (int i = 0; i < phoneNum.length(); i++) {
            if (phoneNum.charAt(i) < '0' || phoneNum.charAt(i) > '9') {
                System.out.println("非法：手机号码必须为数字");
                return false;
            }
        }
        return true;
    }


}
