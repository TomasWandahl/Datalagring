/**
 * Created by Tomas Wändahl on 2016-12-22.
 * All rights reserved.
 * Yeah.
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while (true) {
            run();
        }
    }

    public static void run() {
        System.out.println("Read or write?");
        Scanner keyboard = new Scanner(System.in);
        String op = keyboard.nextLine();

        Insert ins = new Insert();
        Select sel = new Select();

        switch (op.toUpperCase()) {
            case "READ":
                sel.retrieve();
                break;
            case "WRITE":
                ins.input();
                break;
            default:
                System.out.println("Invalid OP!");
        }
    }
}