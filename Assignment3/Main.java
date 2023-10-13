import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;


// possibly switch to reading it as a string. Think about best way to split it
// Should I add it to stack immediately?
// Is the array pointless?
// Do I need two stacks?

public class Main {
    public static void main(String[] args)throws Exception{
        
        String filePath = System.getProperty("user.dir") + "/" + args[0]; 
        Stack<Character> stack = new Stack<>();
        
        
        char[] textAsArray = fileToArray(filePath);

        for(int i = 0; i < filePath.length(); i++){
          char current = textAsArray[i];
          if(current == '<'){
            stack.push(current);
          }
          else if()
        }

    }





    


    public static char[] fileToArray(String filePath)throws Exception{
    File file = new File(filePath);
    FileReader fr = new FileReader(file);
    BufferedReader br = new BufferedReader(fr);
    int k = 0;
    int approxLength = (int)(file.length() + 10);
    char[] textFileAsArray = new char[approxLength];

    while(br.ready()){
      textFileAsArray[k] = (char)br.read();
      System.out.print(textFileAsArray[k]);
      
      k++;
    }
    br.close();
    
    return textFileAsArray;

  }
}