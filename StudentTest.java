package com.cv.test;

import java.util.Scanner;

public class StudentTest {
    public static void main(String[] args) {
        Student[] arr = new Student[1];
        Student st1 = new Student(2021221092, "陈维", 23);
        arr[0] = st1;
        label:
        while (true) {
            System.out.println("##########菜单#########");
            System.out.println("键入【1】以添加学生信息\n键入【2】以更改学生信息\n键入【3】以删除学生信息\n键入【4】以查询学生信息\n键入其他数字以结束系统");
            System.out.println("#######################");
            System.out.print("请输入：");
            Scanner sc = new Scanner(System.in);
            int choose = sc.nextInt();
            switch (choose) {
                case 1:
                    arr = add(arr);
                    break;
                case 2:
                    arr = change(arr);
                    break;
                case 3:
                    arr = delete(arr);
                    break;
                case 4:
                    show(arr);
                    break;
                default: {
                    System.out.println("系统已结束");
                    break label;
                }

            }
        }

    }

    // 添加信息
    public static Student[] add(Student[] arr) {
        //新建多一位数组brr，并将值赋予brr
        Student[] brr = new Student[arr.length + 1];
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < arr.length; i++) {
            brr[i] = arr[i];
        }
        System.out.print("输入要添加的学号：");
        int stNum_add = sc.nextInt();
        if (judge(stNum_add, arr) == -1) {
            System.out.println("验证中…………");
            System.out.println("可以添加信息！");
            System.out.print("输入此学生姓名：");
            String name_add = sc.next();
            System.out.print("输入此学生年龄：");
            int age_add = sc.nextInt();
            Student st = new Student(stNum_add, name_add, age_add);
            brr[brr.length - 1] = st;
            System.out.println("已成功添加学生信息！");
            return brr;
        } else {
            System.out.println("学生信息重复，不可添加");
        }
        return arr;
    }


    // 更改信息
    public static Student[] change(Student[] arr) {
        Scanner sc = new Scanner(System.in);
        System.out.println("输入学号");
        int stNum_change = sc.nextInt();
        int index = judge(stNum_change, arr);
        if (index == -1) {
            System.out.println("未检索到信息");
        } else {
            System.out.println("已检索到信息");
            arr[index].setStNum(stNum_change);
            System.out.println("输入更改后姓名");
            String name_change = sc.next();
            System.out.println("输入更改后年龄");
            int age_change = sc.nextInt();
            arr[index].setName(name_change);
            arr[index].setAge(age_change);
            System.out.println("已成功修改信息");
        }

        return arr;
    }

    //删除信息
    public static Student[] delete(Student[] arr) {
        Scanner sc = new Scanner(System.in);
        System.out.println("输入学号");
        int stNum_delete = sc.nextInt();
        //判断是否存在要删除的信息
        int index = judge(stNum_delete, arr);
        int j = 0;
        if (index == -1)
            System.out.println("未检索到信息");
        else {
            Student[] brr = new Student[arr.length - 1];
            for (int i = 0; i < arr.length; i++) {
                if (i != index) {
                    brr[j] = arr[i];
                    j++;
                }
            }
            System.out.println("已成功删除信息");
            return brr;
        }
        return arr;
    }


    //遍历信息
    public static void show(Student[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(String.format("%-16s", arr[i].getStNum()) + arr[i].getName() + "\t" + arr[i].getAge());
        }
    }

    //唯一性判断
    public static int judge(int stNum, Student[] arr) {
        int index = -1;
        for (int i = 0; i < arr.length; i++) {
            if (stNum == arr[i].getStNum()) {
                index = i;
                return index;
            }
        }
        return index;
    }

}

