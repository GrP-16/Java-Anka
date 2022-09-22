import java.io.File;
import java.io.FileWriter;
import java.sql.ResultSet;
// import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class CronJob extends TimerTask {
    static File file;
    static String result[];
    static String res[] = {"anka","root",""}; 
    static Statement st = DB.connect(res);
    static Scanner fileReader;
    public static void main(String[] args) {
        CronJob con = new CronJob();
        Timer t = new Timer();
        t.scheduleAtFixedRate(con, 0, 12000);
    }


public static void fetchPerformance(Statement statement) {

    // this file contains the saved name of the user registered paticipant.
    File f = new File("local.db");
    
    String q = "";
    try{
        if(f.exists() == true){
     Scanner inp =   new Scanner(f);
     String res = inp.next();

    String query = "SELECT * FROM `participantdetails` WHERE name ='"+res+"';";
    String qry = "SELECT * FROM `productdetails` WHERE productowner='"+res+"';";
        
    // query data from `participantdetails` table.
            ResultSet result = DB.getData(statement,query);
        //   Looping through database records
            while(result.next()){
                // Full text
                q += result.getString("name")+","+result.getString("points")+","+result.getString("quantity")+",";
        }
        
         // query from productDetails
            ResultSet pdt = DB.getData(statement,qry);
            while(pdt.next()){
                q += pdt.getString("rank");
            }
        }
    } catch(Exception ex){
        System.out.println("SQL error "+ex.getMessage());
    }
    try{
        File file = new File("perform.db");
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(q);
            //   System.out.println(q);
        }
    } catch(Exception ex){
        System.out.print("Error :"+ex.getMessage());
    }
}

public static void pLogic(String a[], String table,Statement st){
        // System.out.println(table+","+a.length);
        if (a.length == 6) {
            //id	name	product	DateOfBirth	points	created_at	updated_at	return_customer	position
            System.out.println("Participant details");
                String sql = "INSERT INTO `"+ table +"` (`id`,`name`,`password`,`product`,`DateOfBirth`,`points`,`created_at`,`updated_at`,`return_customer`,`quantity`) VALUES (0,'"+a[0]+"','"+a[1]+"','"+ a[2] +"','"+a[3]+"',0,'"+a[4]+",'"+a[5]+"',0,0);";
        //    helps us to perform db insertions for participantdetails
            DB.makeQuery(st, sql);
                // System.out.println(sql) ;
        } else { 
            System.out.println("Product details\n");
            // 
      // id	product	quantity	price	description	rate	productowner	created_at	updated_at	quantity_left	

            String sql = "INSERT INTO `"+ table +"` (`id`, `product`, `quantity`, `price` , `description`, `rate`, `productowner`, `created_at`, `updated_at`,`quantity_left`) VALUES (0, '"+a[1]+"', '"+a[2]+"', '"+ a[3] +"', '"+a[4]+"','"+a[5]+"','"+a[6]+"','"+a[7]+"','"+a[8]+"','"+a[9]+"');";
        //    helps us to perform db insertions for product details
            DB.makeQuery(st, sql);
            // System.out.println(sql);
        }

    }

    public static void setTimeout(Runnable runnable, int delay){
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            }
            catch (Exception e){
                System.err.println(e);
            }
        }).start();
    }

    @Override
    public void run() {
        try {
            
            String files[] = {"pDetails.csv", "productDetails.csv"};
            String table[] = {"participantdetails", "productdetails"};

            for (int i = 0; i < files.length; i++) {
                
                file = new File(files[i]);

                if(file.exists() == true){
                     fileReader = new Scanner(file);
                    // reading file contents
                    while (fileReader.hasNext()) {
                        // fileReader.
                      result = fileReader.nextLine().split(",");
                    }  

                    fileReader.close(); 

                    String conectionParams[] = {"anka","root",""};
                  
                    Statement st = DB.connect(conectionParams);
                    pLogic(result,table[i],st);
                    
                    fetchPerformance(st);

                    System.out.println("\nUploaded  "+files[i]);
                    // to clear the screen
                
                     System.out.println("\nClearing in 8 seconds....");
                   
                     setTimeout(()-> System.out.print("\033[H\033[2J")  ,8000);
                   
                    file.delete();

                } else {
                    
                    // pLogic(result,table[i],st);
                    
                    fetchPerformance(st);

                    // System.out.printf("\nFile => %s doesn't exist",files[i]);
                    // // System.out.println("\n\n\nClearing  exception  8 seconds....");
                    //  setTimeout(()-> System.out.print("\033[H\033[2J")  ,8000);
                }
            }
        } catch (Exception e) {
            // exit();
            System.out.println("\nCronJob Error: "+e.getMessage());
            System.out.println("\nClearing  exception  8 seconds....");
            setTimeout(()-> System.out.print("\033[H\033[2J")  ,8000);
        }
    }
    
}
