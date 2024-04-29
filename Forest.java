package Final;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;

public class Forest {
        private static String name;
        static ArrayList<Tree> trees;
        private static final int DOUBLE_DIGIT = 10;
        private static final Scanner keyboard = new Scanner(System.in);

        public Forest(String name) {
            this.name = name;
            this.trees = new ArrayList<>();
        } // end of Forest

        public void addTree(Tree tree) {
            trees.add(tree);
        } // end of addTree

    public static Forest fileOpener(String forestName){
        Forest forest = new Forest(forestName);
        int yearPlanted;
        double heightInFeet;
        double growthRate;
        Tree.Species species;
        String fileName = forestName + ".csv";
        try {
            FileInputStream fileByteStream = new FileInputStream(fileName);
            Scanner fileScanner = new Scanner(fileByteStream);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] currentTrees = line.split(",");

                species = Tree.Species.valueOf(currentTrees[0].toUpperCase());
                yearPlanted = Integer.parseInt(currentTrees[1]);
                heightInFeet = Integer.parseInt(currentTrees[2]);
                growthRate = Double.parseDouble(currentTrees[3]);

                Tree newtree = new Tree(species, yearPlanted, heightInFeet, growthRate); // adds tree to arrayList
                trees.add(newtree); //adds new tree
            } // end of while statement

            fileScanner.close(); //close scanner
            fileByteStream.close(); //close document

        } catch (
                IOException e) {
            System.out.println("There is no file with that name");
        } // catch statement
        System.out.println("Initializing from " + Forest.getName());
        return forest;
    }
    public static Forest dbOpener(String forestName) {
        int yearPlanted;
        double heightInFeet;
        double growthRate;
        Tree.Species species;
        String fileName = forestName + ".db";
        Forest forest = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            String[] currentTrees = line.split(" ");

            species = Tree.Species.valueOf(currentTrees[0].toUpperCase());
            yearPlanted = Integer.parseInt(currentTrees[1]);
            heightInFeet = Double.parseDouble(currentTrees[2]);
            growthRate = Double.parseDouble(currentTrees[3]);
            forest = new Forest(forestName);
            Tree newtree = new Tree(species, yearPlanted, heightInFeet, growthRate); // adds tree to arrayList
            trees.add(newtree); //adds new tree
        } catch (IOException e) {
            System.out.println("Sorry, I cannot open that file at this time.");
            e.printStackTrace();
        }
        return forest;
    } // end of method
    public static void printTree(int index){

        Tree tree = Forest.trees.get(index);

        if(tree.getTreeSpecies() == Tree.Species.FIR && tree.getGrowthRate() < DOUBLE_DIGIT) {
            System.out.println(tree.fir_string() + tree.smallerGR());
        } // if it's fir and single digit growth rate
        else if(tree.getTreeSpecies() == Tree.Species.FIR && tree.getGrowthRate() > DOUBLE_DIGIT)
        {
            System.out.println(tree.fir_string() + tree.GR());
        } // if it's fir and double-digit growth rate
        else if(tree.getTreeSpecies() != Tree.Species.FIR && tree.getGrowthRate() < DOUBLE_DIGIT){
            System.out.println(tree + tree.smallerGR());
        } // if it's not fir and single digit growth rate
        else{
            System.out.println(tree + tree.GR());
        } // if it's not fir and double-digit growth rate

    } // print one tree method

    public static void printForest(Forest forest) {
        for (int i = 0; i < Forest.trees.size(); i++) {
            printTree(i);
        }//end of for

        double average = getAverageHeight(forest);
        System.out.println("There are " + Forest.trees.size() + " trees, with an average height of " +
                Double.parseDouble(String.format("%.1f", average)) + "'");
    } // end of printForest method

    public static double getAverageHeight(Forest forest){
        int total = 0;
        double average;

        for (Tree tree : Forest.trees) {
            total += (int) tree.getTreeHeight();
        }//end of for
        if(total == 0)
        {
            return 0;
        }
        average = (double) total / Forest.trees.size();
        return average;
    } // end of get height average method

    public static void removeTree(){
            int index;
            System.out.print("Enter the tree index that you want to cut: ");
            index = keyboard.nextInt();
            while (!(index >= 0 && index < trees.size())){
                System.out.println("Invalid tree index, please enter a number from  0 to " + (trees.size() - 1));
                index = keyboard.nextInt();
            }
            trees.remove(index);
        } // end of method
    public static void addTree(int index){
        Tree newTree = new Tree();
        newTree = newTree.randomTree(newTree);
        trees.add(index, newTree);
    } // end of add tree method
    public static void simGrowth(){
             trees.forEach(tree -> tree.setTreeHeight(tree.getTreeHeight() + tree.getTreeHeight() * (tree.getGrowthRate() / 100)));
            } // end of growth method

    public static void reapTrees(){
        System.out.print("Height to reap from:");
        double userInput = keyboard.nextDouble();

        while (userInput < 0){
            System.out.print("Invalid input. Please enter a valid number above 0: ");
            userInput = keyboard.nextDouble();
        }

        for (int i = 0; i < trees.size() - 1; i++) {
            Tree tree = trees.get(i);

            if(tree.getTreeHeight() >= userInput){
                System.out.print("Reaping the tall tree  ");
                printTree(i); //prints
                trees.remove(i);
                System.out.print("Replaced with new tree ");
                addTree(i); // adds new tree
                printTree(i); // prints new tree
            } // end of if
        }//end of for
    } // end of reap tree method

    public static void dbFileSaver(String fileName){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName + ".db"));
             for(int i = 0; i < Forest.trees.size() - 1; i++)
             {
                 Tree tree = Forest.trees.get(i);
                writer.write(String.valueOf(tree.getTreeSpecies()));
                writer.write(" ");
                writer.write(String.valueOf(tree.getYearPlanted()));
                writer.write(" ");
                writer.write(String.valueOf(tree.getTreeHeight()));
                writer.write(" ");
                writer.write(String.valueOf(tree.getGrowthRate()));
                writer.write("\n");
            } // end of for
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    } // end of db savers

public void loadForest(String filename) {

}

//        // Getters and setters
        public static String getName() { return name; }
//        public ArrayList<Tree> getTrees() { return trees; }
//    }
}
