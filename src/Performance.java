import java.io.File;
import java.util.Scanner;

public class Performance {
    static Scanner reader;
    static String[] store;
    static File file;
    public static void main(String[] args) {
        runProgram();
    }
    public static void runProgram() {
        file = new File("perform.db");
        try{
        reader = new Scanner(file);
        
        while (reader.hasNext()) {
           store = reader.nextLine().split(",");
        }
        // s
        System.out.println("+--------------------------------------------------------------------------+");
        System.out.println("|                         Performance Summary                              |");
        System.out.println("+--------------------------------------------------------------------------+");
        System.out.printf("|   Name          |              %s                                        \n",store[0]);
        System.out.printf("|   Rank          |              %s                                        \n",store[1]);
        System.out.printf("|   Points        |              %s                                          \n",store[2]);
        System.out.printf("|   Quantity_left |              %s                                       \n",store[3]);
        System.out.println("+--------------------------------------------------------------------------+\n");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
