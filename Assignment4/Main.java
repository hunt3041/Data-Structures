import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args){
        String filePath = System.getProperty("user.dir") + "/" + args[0]; 
        HeapAdaptablePriorityQueue<String, Record> priQueue = new HeapAdaptablePriorityQueue<>();

        readTxtFileToQueue(filePath, priQueue);

     
    
        

    }


    




    public static HeapAdaptablePriorityQueue<String, Record> readTxtFileToQueue(String filePath, 
                                                            HeapAdaptablePriorityQueue<String, Record> list){
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                if (count == 0){}

                else{
                    // Split the line using semicolons as the separator
                    String[] values = line.split(";");
                    
                    Record patientRecord = new Record(values[0], values[1], values[2], values[3], values[4], values[5], values[6], values[7], values[8], values[9], values[10], values[11], values[12]);

                    list.insert(patientRecord.getUnosStatus(), patientRecord);

                    System.out.println(list.min());
                    System.out.println(list.min().getValue().getFirstName());
                }
                count += 1;
                
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    return list;
    }

}