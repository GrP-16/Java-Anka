import java.io.*;
import java.util.*;
import java.sql.Timestamp;
import java.time.Instant;

public class PostProduct{
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
    
    public static void main(String[] args) {
        registerProduct(args);
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
          //  msg();
        }
        }
    }

    











