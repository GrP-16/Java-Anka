
import java.io.*;
import java.util.*;
import java.sql.Timestamp;
import java.time.Instant;

public class App {
	public static String participantName = null;
	public static String password = null;
	public static String DOB = null;
	public static String productName = null;
	public static String productDescription = null;
	static String return_customer = null;
	public static int Quantity = 0;
	static Timestamp instant= Timestamp.from(Instant.now());  
	public static int rate = 0;
	static int price;
	static int quantity,quantity_left;
	static String product,productowner;
	static String owner;

	public static int Menu = 0;


	public static void main(String[] args) {
		menu(args);

	}
    public static void msg(){
        System.out.println("+-------------------------------------------------------------------------------------+");
        System.out.println("|                               Welcome to Anka CLI                                   |");
        System.out.println("+-------------------------------------------------------------------------------------+");
            System.out.println("| For help type: -h                                                                 |");
        System.out.println("+-------------------------------------------------------------------------------------+");
        System.out.println("|          Command          |  Options                                                ");
        System.out.println("+-------------------------------------------------------------------------------------|");
        System.out.println("|   Register                |   name  DOB(yyy-mm-dd)					                 ");
        // System.out.println("+");
        System.out.println("|   post-product            |   product	quantity	price	rate description productowner created_at updated_at	quantity_left");
        // System.out.println("+						   															 ");
        System.out.println("|   Performance             |                                                         ");
        System.out.println("+-------------------------------------------------------------------------------------+");
    }
	public static void menu(String a[]) {
        if (a.length == 0) {
               // checking if no agrument passed
            msg();
        } else {
            if (a[0].equals("Register")) {
                String temp[] = { a[1], a[2] };
               register(temp);
            } else if(a[0].equals("PostProduct")){
			//id	product	quantity	price	description	rate	productowner	created_at	updated_at	quantity_left	

                String temp[] = { a[1], a[2], a[3], a[4], a[5] ,a[6], a[7]};
                registerProduct(temp);
            } else {
                msg();
            }
        }

	}
	public static void register(String params[]) {

		participantName = params[0].toUpperCase();
		// password = params[1];
		// return_customer = params[2];
		DOB = params[1];

		try {
			File file = new File("pDetails.csv");
			file.createNewFile();
			FileWriter Write = new FileWriter("pDetails.csv");
		
			Write.write("0,"+ participantName + "," + DOB + ","+ instant +","+ instant+","+return_customer);
			// Write.write("\"0\" ,\""+ participantName + "\",\"" + DOB + "\",\","+ instant +", "+ instant +",");
			System.out.println("Successfully registered\n");
			Write.close();
		}
		catch (Exception e) {
			System.out.println("Ooops! an error occured");
		}
	}

	public static void registerProduct(String params[]) {
	//id	product	quantity	price	description	rate	productowner	created_at	updated_at	quantity_left	
		if (params.length != 0) {
            productName = params[0];
			Quantity =  Integer.parseInt(params[1]);
			price = Integer.parseInt(params[2]);
			rate = Integer.parseInt(params[3]);
			productDescription = params[4];
			owner = params[5];
			quantity_left = Integer.parseInt(params[6]);
		// for (String string : params) {
		// 	System.out.println(string);
		// }

		try {
			File file = new File("productDetails.csv");
			file.createNewFile();
			FileWriter Write = new FileWriter("productDetails.csv");
			//product	quantity	price	rate description productowner	created_at	updated_at	quantity_left	
			
			String ts = "0," + productName + "," + Quantity + ","+ price +"," + productDescription + "," + rate+ "," + owner+ ","+ instant +","+ instant + "," + quantity_left;
			Write.write(ts);
			System.out.println("Successfully registered products");
			Write.close();
		}
		catch (Exception e) {
			System.out.println("Ooops! "+e.getMessage());
		}
	} else {
		msg();
	}
	}
}