
import java.io.*;
import java.util.*;


public class Anka {
	public static String participantName = null;
	public static String password = null;
	public static String DOB = null;
	public static String productName = null;
	public static String productDescription = null;
	public static int Quantity = 0;
	public static int rate = 0;
	public static int Menu = 0;


	public static void main(String[] args) {
		menu();

	}
	public static void menu() {
		System.out.println("Please choose option \n1:  To register participant \n2:  To register product details \n3:  To exit");
		Scanner scan = new Scanner(System.in);
		Menu = scan.nextInt();
		scan.nextLine();

		switch(Menu) {
		case 1: {
			if (participantName == null) {
				System.out.println("Enter name, password, product and date of birth");
				register();
			}
			else {
				System.out.println("User exists please update your product details by choosing option 2.");
				menu();
			}
			break;
		}
		case 2: {
			if (Quantity == 0) {
				registerProduct();

			}
			else {
				registerQty();
			}

			break;
		}
		case 3: {
			exit();
		}
		}

		scan.close();
	}
	public static void register() {
		Scanner scan = new Scanner(System.in);

		System.out.println("Enter your full name");
		participantName = scan.nextLine().toUpperCase();
		System.out.println("Enter your password");
		password = scan.nextLine();
		System.out.println("Enter product Name");
		productName = scan.nextLine();
		System.out.println("Enter your date of birth in the format day-month-year");
		DOB = scan.nextLine();

		try {
			File file = new File("participantDetails.txt");
			file.createNewFile();
			FileWriter Write = new FileWriter("participantDetails.txt");
			Write.write("Participant Name: " + participantName + "\n" + "Password: " + password + "Product Name: " + productName + "\n" + "DOB: " + DOB);
			System.out.println("Successfully registered now you can choose products to start the contest by choosing option 2\n");
			menu();

			Write.close();
		}
		catch (IOException e) {
			System.out.println("Ooops! an error occured");
		}

		scan.close();
	}
	public static void registerProduct() {
		Scanner scan = new Scanner(System.in);

		if (participantName!=null) {

			System.out.println("Enter product description");
			productDescription = scan.nextLine();
			System.out.println("Enter the quantity of procuts you would like to register");
			Quantity = scan.nextInt();
			scan.nextLine();
			System.out.println("Enter the rating of your product");
			rate = scan.nextInt();
		}
		else {
			System.out.println("Please first register into the system to update product details, choose option 1\n");
			menu();
		}

		try {
			File file = new File("productDetails.txt");
			file.createNewFile();
			FileWriter Write = new FileWriter("productDetails.txt");
			Write.write("Product Name: " + productName + "\n" + "Deacription: " + productDescription + "\n" + "Quantity availabe: " + Quantity + "\n" + "Product rating: " + rate);
			System.out.println("Successfully registered products \nYou can now press option 3 to exit the program or option 2 to add more products\n");

			menu();

			Write.close();
		}
		catch (IOException e) {
			System.out.println("Ooops! an error occured");
		}

		scan.close();
	}
	public static void registerQty() {

		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the quantity of procuts you would like to add to your products available\n");
		Quantity = scan.nextInt() + Quantity;
		scan.nextLine();

		try {
			File file = new File("productDetails.txt");
			file.createNewFile();
			FileWriter Write = new FileWriter("productDetails.txt");
			Write.write("Product Name: " + productName + "\n" + "Deacription: " + productDescription + "\n" + "Quantity availabe: " + Quantity + "\n" + "Product rating: " + rate);
			System.out.println("Successfully added products to your current stock \nYou can now press option 3 to exit the program or option 2 to add more products\n");
			menu();

			Write.close();
		}
		catch (IOException e) {
			System.out.println("Ooops! an error occured");
		}

		scan.close();
	}

	public static void exit() {
		Scanner scan = new Scanner(System.in);
		int exited = 0;
		System.out.println("Do you really want to exit the system \n1:  No\n2:  Yes\n");
		exited = scan.nextInt();
		switch (exited) {
		case 1: {
			menu();
			break;
		}
		case 2: {
			Menu = 0;
			System.out.println("Successfully exited out of the system");
			break;
		}
		}

		scan.close();
	}
}
