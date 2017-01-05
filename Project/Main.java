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
        GetArenas GetA = new GetArenas();
        GetTournaments GetT = new GetTournaments();
        AddArena AddA = new AddArena();

        System.out.println("What would you like to do? Add arena, show tournaments or show a tournament-specific arena?");
        Scanner keyboard = new Scanner(System.in);
        String op = keyboard.nextLine();

        switch (op.toUpperCase()) {
            case "ADD ARENA":
                AddA.addArena();
                break;
            case "SHOW TOURNAMENTS":
                GetT.getTournaments();
                break;
            case "SHOW TOURNAMENT ARENA":
                GetA.getArenas();
                break;
            default:
                System.out.println("Invalid OP!");
        }
    }
}