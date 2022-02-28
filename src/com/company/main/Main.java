package com.company.main;



import com.company.blackjack.GameVisualizer;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        Menu menu = new Menu();
        Scanner input = new Scanner(System.in);
       GameVisualizer.getHello();

        for (; ; ) {
            String command = input.nextLine();
            menu.doCommandOfMenu(command);
        }
    }
}
