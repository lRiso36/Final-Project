package Final;
public class Tree {
    enum Species {
        BIRCH,
        FIR,
        MAPLE
    }
    Species treeSpecies;
    private int yearPlanted;
    private double treeHeight;
    private double growthRate;

    int[] numbers = new int[]{0, 1, 2};

    public Tree(){
        Species treeSpecies = null;
        int yearPlanted = 0;
        double treeHeight = 0.0;
        double growthRate = 0.0;
    } //

    public Tree(Species treeSpecies, int yearPlanted, double treeHeight, double growthRate){
        this.treeSpecies = treeSpecies;
        this.yearPlanted = yearPlanted;
        this.treeHeight = treeHeight;
        this.growthRate = growthRate;
    } // instantiating class

    public Tree randomTree(Tree tree){;
        tree.yearPlanted = (int) (2000 + (Math.random() * (25)));
        tree.growthRate = 10 + ((Math.random() * (10)));
        tree.treeHeight = 10 + ((Math.random() * (10)));
        tree.treeSpecies = randomTree(numbers);

        return tree;
    } // end of randomTree

    private Species randomTree(int numbers[]) {
        int temp = (int) (Math.random() * 3);
        if(temp == 0) return Species.FIR;
        else if (temp == 1) return Species.MAPLE;
        return Species.BIRCH;
    } // end of randomTree species

    public void setTreeHeight(double treeHeight) {
        this.treeHeight = treeHeight;
    }
    public Species getTreeSpecies(){
        return treeSpecies;
    }
    public int getYearPlanted() {
        return yearPlanted;
    } // end of get
    public double getTreeHeight() {
        return Double.parseDouble(String.format("%.2f", treeHeight));
    } // end of get
    public double getGrowthRate() {
        return Double.parseDouble(String.format("%.2f", growthRate));
    } // end of get

    @Override
    public String toString() {
        return treeSpecies + "   " + yearPlanted + "  " + Double.parseDouble(String.format("%.1f", treeHeight)) + "'  ";
    } // end of toString
    public String fir_string(){
        return treeSpecies + "     " + yearPlanted + "  " + Double.parseDouble(String.format("%.1f", treeHeight)) + "'  ";
    } // end of firString
    public String GR(){
        return  Double.parseDouble(String.format("%.1f", growthRate)) + "%";
    }
    public String smallerGR(){
        return  " " + Double.parseDouble(String.format("%.1f", growthRate)) + "%";
    }
}
