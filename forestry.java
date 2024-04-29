package Final;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class forestry {
    private static final Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to the Forestry Simulation");
        System.out.println("_____________________________________");
        int count = 0; //for args index command line

        if (args.length < 1) {
            System.out.println("There is no forest in the command line.");
            System.exit(1);
        } //checking if command line is filled

        String fileName = args[0]; // setting fileName to first forest in command line
        Forest forest = Forest.fileOpener(fileName);

        char userChoice;

        System.out.print("\n(P)rint, (A)dd, (C)ut, (G)row, (R)eap, (S)ave, (L)oad, (N)ext, e(X)it:  ");
        System.out.println();
        userChoice = keyboard.next().charAt(0);

        while (!(userChoice == 'X' || userChoice == 'x')) {
            switch (userChoice) {
                case 'P':
                case 'p':
                    System.out.println("Forest name: " + Forest.getName());
                    Forest.printForest(forest);
                    break;
                case 'A':
                case 'a':
                    Forest.addTree(Forest.trees.size());
                    break;
                case 'C':
                case 'c':
                    Forest.removeTree();
                    break;
                case 'G':
                case 'g':
                    Forest.simGrowth();
                    break;
                case 'R':
                case 'r':
                    Forest.reapTrees();
                    break;
                case 'S':
                case 's':
                    Forest.dbFileSaver(fileName);
                    break;
                case 'L':
                case 'l':
                        System.out.print("Enter the forest name: ");
                        fileName = keyboard.next();
                        forest = Forest.dbOpener(fileName);
                    break;
                case 'N':
                case 'n':
                    System.out.println("Moving to the next forest...");
                    if(args.length - 1 > count){
                        count++;
                        fileName = args[count];
                        forest = Forest.fileOpener(fileName);
                    }
                    else{
                        System.out.println("There are no other forests in the command line.");
                    }
                    break;
                case 'x':
                case 'X':
                    break;
                default:
                    System.out.println("ERROR: Invalid option, try again");
                    break;
            } // end of switch
            System.out.println();
            System.out.print("\n(P)rint, (A)dd, (C)ut, (G)row, (R)eap, (S)ave, (L)oad, (N)ext, e(X)it:  ");

            userChoice = keyboard.next().charAt(0);
        }//end of while
        System.out.println("Exiting the Forestry Simulation");
    } // end of main
} //end of code

