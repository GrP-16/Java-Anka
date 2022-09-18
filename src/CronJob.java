import java.io.File;
import java.sql.Statement;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class CronJob extends TimerTask {
    static File file;
    static String result[];
    static Scanner fileReader;
    public static void main(String[] args) {
        CronJob con = new CronJob();
        Timer t = new Timer();
        t.scheduleAtFixedRate(con, 2, 10000);
    }


    public static void pLogic(String a[], String table,Statement st){
        // System.out.println(table+","+a.length);
        if (a.length == 6) {
            //id	product	quantity	price	description	rate	productowner	created_at	updated_at	quantity_left

            System.out.println("Participant details");
                String sql = "INSERT INTO `"+ table +"` (`id`, `name`, `DateOfBirth`, `created_at`, `updated_at`, `return_customer`) VALUES ('"+a[0]+"', '"+a[1]+"', '"+a[2]+"', '"+ a[3] +"', '"+a[4]+"', '"+a[5]+"');";
        //    helps us to perform db insertions for participantdetails
                DB.makeQuery(st, sql);
                // System.out.println(sql) ;
        } else {
            System.out.println("Product details\n");
            String sql = "INSERT INTO `"+ table +"` (`id`, `product`, `quantity`, `price`, `description`, `rate`, `productowner`, `created_at`, `updated_at`, `quantity_left`) VALUES ('"+a[0]+"', '"+a[1]+"', '"+a[2]+"', '"+ a[3] +"', '"+a[4]+"','"+a[5]+"','"+a[6]+"','"+a[7]+"','"+a[8]+"','"+a[9]+"');";
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

                    System.out.println("\nUploaded  "+files[i]);
                    // to clear the screen
                
                     System.out.println("\nClearing in 8 seconds....");
                   
                     setTimeout(()-> System.out.print("\033[H\033[2J")  ,8000);
                   
                    file.delete();

                } else {
                    System.out.printf("\nFile => %s doesn't exist",files[i]);
                    // System.out.println("\n\n\nClearing  exception  8 seconds....");
                     setTimeout(()-> System.out.print("\033[H\033[2J")  ,8000);
                }
            }
        } catch (Exception e) {
            System.out.println("\nCronJob Error: "+e.getMessage());
            System.out.println("\nClearing  exception  8 seconds....");
            setTimeout(()-> System.out.print("\033[H\033[2J")  ,8000);
        }
    }
    
}
