/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.util.Scanner;

/**
 *
 * @author admin
 */
public class Validation {

    public static final Scanner sc = new Scanner(System.in);

    public String getInputString(String mes) {
        System.out.println(mes);
        return sc.nextLine();
    }

    
   public int getInt(String promt, int m, int n) {
        int a = -1;
        while (true) {
            System.out.print(promt + ": ");
            try {
                String s = sc.nextLine();
                a = Integer.parseInt(s);
                if (a >= m && a <= n) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Please enter a number between " + m + " and " + n);
            }
        }
        return a;
    }
   
   public static String checkInputString() {
        while (true) {
            String string = sc.nextLine().trim();
            if (string.isEmpty()) {
                System.err.println("Not allowed empty!");
                System.out.println("Enter again; ");
            } else {
                return string;
            }
        }
    }
}
