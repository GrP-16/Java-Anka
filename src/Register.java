import java.io.File;
import java.io.FileWriter;
import java.sql.Timestamp;
import java.time.Instant;

public class Register{
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
        register(args);
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
}