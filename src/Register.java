import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Scanner;

public class Register{
     static String participantName = null;
	 static String password;
	 static String DOB = null;
	 static String productName = null;
	 static String productDescription = null;
	 static String return_customer = null;
	 static int Quantity = 0;
	 static Timestamp instant= Timestamp.from(Instant.now());  
	 static int rate = 0;
	static int price;
	static Scanner reader;
	static int quantity,quantity_left;
	static String product,productowner;
	static String owner;
	static FileOutputStream out;
	static FileInputStream in;
	static FileWriter wr;
    static int rout = 0;
    public static void main(String[] args) {
        register(args);
    }


	public static void setState(){
		try{
			File file =	new File("state.db");
			if(file.exists() == false){
			 out = new FileOutputStream(file);
				out.write(1);
				out.close();
			}
		} catch(IOException ex){
			System.out.println("IO Error :"+ex.getMessage());
		}
	}

  public static int getState(){
	int x = 0;

	try{
		File file =	new File("state.db");
		// System.out.print(file.exists());
		if(file.exists() == true){
			 in = new FileInputStream(file);
			x = in.read();
			in.close();
		} else {
			x = 0;
		}
	} catch(IOException ex){
		System.out.println("IO Error :"+ex.getMessage());
	}
	return x;
}



    public static void register(String params[]) {
		if (params.length == 0) {
			App.msg();
		} else {
			participantName = params[0];
			password = params[1];
			product = params[2];
			DOB = params[3];
			File file = new File("pDetails.csv");

			try {
				if(file.exists() == false){
					// localStorage
				if(getState() != 1 ){
					setState();
				// creates and writes to the file
					FileWriter Write = new FileWriter(file);
					
					try (FileWriter wr = new FileWriter("local.db")) {
						wr.write(participantName);
					}
					// id	name password	product	DateOfBirth	points	created_at	updated_at	return_customer	position
					Write.write(participantName + "," + password + ","+ product +","+ DOB+","+ instant+","+instant);
					// Write.write("\"0\" ,\""+ participantName + "\",\"" + DOB + "\",\","+ instant +", "+ instant +",");
					System.out.println("Successfully registered\n");
					Write.close();
				} else {
					System.out.println("+----------------------------------------------+");
					System.out.println("|                  Caution !!!                 |");
					// System.out.println("+----------------------------------------------+");
					
					System.out.println("+----------------------------------------------+");
					System.out.println("|      Ooops!!, You already registered	       |");
					System.out.println("+----------------------------------------------+");

				}
			} else {
					System.out.println("+----------------------------------------------+");
					System.out.println("|                  Caution !!!                 |");
					// System.out.println("+----------------------------------------------+");
					
					System.out.println("+----------------------------------------------+");
					System.out.println("|      Ooops!!, You already registered	       |");
					System.out.println("+----------------------------------------------+");
			}


			} catch (Exception e) {
				System.out.println("Ooops! an error occured"+ e.getMessage());
			}
	}
}
}