package com.cv.test;

public class Student {
    private int stNum;
    private String name;
    private int age;

    public Student() {
    }

    public Student(int stNum, String name, int age) {
        this.stNum = stNum;
        this.name = name;
        this.age = age;
    }

    public int getStNum() {
        return stNum;
    }

    public void setStNum(int stNum) {
        this.stNum = stNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
