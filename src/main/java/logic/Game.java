package logic;

import java.util.Scanner;

public class Game {
    Scanner scan = new Scanner(System.in);
    private final char[] box;
    byte countMoves;
    byte rand;
    byte i;

    byte winner = 0;

    public Game(){
        box = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'};
        countMoves = 0;
    }

    public void start(){
        System.out.println("Enter box number to select. Enjoy!\n");
        printBox();
        cleanBox();
        while (winner==0){
            doMove();
            checkWinner();
            if(winner!=0)break;
            aiMove();
            checkWinner();
            printBox();
        }
        System.out.println(switch (winner){
            case 1 -> "You won the game!\nCreated by Shreyas Saha. Thanks for playing!";
            case 2 -> "You lost the game!\nCreated by Shreyas Saha. Thanks for playing!";
            case 3 -> "It's a draw!\nCreated by Shreyas Saha. Thanks for playing!";
            default -> throw new IllegalStateException("Unexpected value: " + winner);
        });
    }

    private void printBox(){
        System.out.println("\n " + box[0] + " | " + box[1] + " | " + box[2] + " ");
        System.out.println("-----------");
        System.out.println(" " + box[3] + " | " + box[4] + " | " + box[5] + " ");
        System.out.println("-----------");
        System.out.println(" " + box[6] + " | " + box[7] + " | " + box[8] + " \n");
    }
    private boolean boxAvailable(){
        return countMoves<8;
    }
    private void cleanBox(){
        for(i = 0; i < 9; i++)
            box[i] = ' ';
    }
    private void checkWinner(){
        winner = (byte) (boxAvailable()?0:3);

        if(((box[0] == 'X') && (box[1] == 'X') && (box[2] == 'X')) ||
                ((box[3] == 'X') && (box[4] == 'X') && (box[5] == 'X')) ||
                ((box[6] == 'X') && (box[7] == 'X') && (box[8] == 'X')) ||
                ((box[0] == 'X') && (box[3] == 'X') && (box[6] == 'X')) ||
                ((box[1] == 'X') && (box[4] == 'X') && (box[7] == 'X')) ||
                ((box[2] == 'X') && (box[5] == 'X') && (box[8] == 'X')) ||
                ((box[0] == 'X') && (box[4] == 'X') && (box[8] == 'X')) ||
                ((box[2] == 'X') && (box[4] == 'X') && (box[6] == 'X')))
            winner = 1;

        if((box[0]=='O' && box[1]=='O' && box[2]=='O') ||
                (box[3]=='O' && box[4]=='O' && box[5]=='O') ||
                (box[6]=='O' && box[7]=='O' && box[8]=='O') ||
                (box[0]=='O' && box[3]=='O' && box[6]=='O') ||
                (box[1]=='O' && box[4]=='O' && box[7]=='O') ||
                (box[2]=='O' && box[5]=='O' && box[8]=='O') ||
                (box[0]=='O' && box[4]=='O' && box[8]=='O') ||
                (box[2]=='O' && box[4]=='O' && box[6]=='O'))
            winner = 2;
    }

    private void doMove(){
        while (true) {
            byte input = 0;
            if ((input = scan.nextByte()) > 0 && input < 10) {
                if (box[input - 1] == 'X' || box[input - 1] == 'O')
                    System.out.println("That one is already in use. Enter another.");
                else {
                    box[input - 1] = 'X';
                    break;
                }
            } else {
                System.out.println("Invalid input. Enter again.");
            }
        }
    }
    private void aiMove() {
        rand = (byte) (Math.random() * (9 - 1 + 1) + 1);
        if (box[rand - 1] != 'X' && box[rand - 1] != 'O') {
            box[rand - 1] = 'O';
        }else aiMove();
    }
}