package com.zte.myhomework;

import java.util.Scanner;

/**
 * Created by wang_ting on 2018/4/9.
 * @author wang_ting
 */
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean exitFlag = false;

        while (true) {
            System.out.println("-------------------------------");
            System.out.println("请选择排序类型：");
            System.out.println("1:学号排序");
            System.out.println("2:年龄排序");
            System.out.println("3:成绩排序");
            System.out.println("0:退出系统");
            int orderType = Integer.parseInt(scanner.nextLine());
            if (orderType == 0) {
                exitFlag = true;
            }
            System.out.println("-------------------------------");
            if (exitFlag) {
                break;
            }
            System.out.println("请选择排序方式：");
            System.out.println("1:正序");
            System.out.println("2:倒序");
            System.out.println("0:退出系统");
            String temp = scanner.nextLine();
            boolean reverse = false;
            if (temp.equals("0")) {
                exitFlag = true;
            } else if (temp.equals("1")) {
                reverse = false;
            } else {
                reverse = true;
            }
            System.out.println("-------------------------------");
            if (exitFlag) {
                break;
            }
            StudentSort.orderStudent(orderType, reverse);
        }
    }
}
