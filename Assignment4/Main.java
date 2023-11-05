import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        String filePath = System.getProperty("user.dir") + "/" + args[0]; 
        
        HeapAdaptablePriorityQueue<String, Double, Record> priQueue = new HeapAdaptablePriorityQueue<>();

        priQueue = readTxtFileToQueue(filePath, priQueue);
        // for(int i = 0; i < 300; i++){
        //     Record patient =  priQueue.removeMin().getValue();
        //     System.out.println("Name: " + patient.getFirstName() + "  Priority: " + patient.getUnosStatus() + "  Age: " + patient.getAge());
        // }

        printMenu();
        
        while (true) {
            Scanner scan = new Scanner(System.in);
            printMenu();
            int userIn = scan.nextInt();
            if(userIn == 1){
                System.out.println("Enter the name of the record to read");
                String fileName = scan.next();
                String path = System.getProperty("user.dir") + "/" + fileName; 
                priQueue = readTxtFileToQueue(path, priQueue);
                System.out.println("Input file is read successfully");
                
                
            }
            else if(userIn == 2){
                Record min = priQueue.min().getValue();
                System.out.println("The patient detail with the highest priority is as follows:");
                System.out.println("Patient's first name: " + min.getFirstName());
                System.out.println("Patient's last name:" + min.getLastName());
                System.out.println("Date of birth of the patient: " + min.getDateOfBirth());
                System.out.println("Address: " + min.getAddress());
                System.out.println("City: " + min.getCity());
                System.out.println("County: " + min.getCounty());
                System.out.println("State: " + min.getState());
                System.out.println("Zip code: " + min.getZip());
                System.out.println("Phone Number (1st Preference): " + min.getPhone1());
                System.out.println("Phone Number (1st Preference): " + min.getPhone2());
                System.out.println("Email address: " + min.getEmail());
                System.out.println("UNOS Status: " + min.getUnosStatus());
                System.out.println("Date listed on " + min.getUnosStatus() + ": " + min.getDateListed()); 
                
            }


        }
        
     
    
        

    }


    

    public static void printMenu(){
        System.out.println("1.  insert");
        System.out.println("2.  peek");
        System.out.println("3.  nextPatient");
        System.out.println("4.  removePatient");
        System.out.println("5.  size");
        System.out.println("6.  exit");
    }


    public static HeapAdaptablePriorityQueue<String, Double, Record> readTxtFileToQueue(String filePath, 
                                                            HeapAdaptablePriorityQueue<String, Double, Record> list){
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                if (values[0] == "first_name"){}

                else{
                    // Split the line using semicolons as the separator
                    
                    
                    Record patientRecord = new Record(values[0], values[1], values[2], values[3], values[4], values[5], values[6], values[7], values[8], values[9], values[10], values[11], values[12]);

                    list.insert(patientRecord.getUnosStatus(), patientRecord.getAge(), patientRecord);

                    // System.out.println(list.min());
                    // System.out.println(list.min().getValue().getFirstName());
                }
                
                
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    return list;
    }

}