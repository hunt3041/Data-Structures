import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Array;
import java.util.ArrayList;


// possibly switch to reading it as a string. Think about best way to split it
// Should I add it to stack immediately?
// Is the array pointless?
// Do I need two stacks?

public class Main {
    public static void main(String[] args)throws Exception{
        
        String filePath = System.getProperty("user.dir") + "/" + args[0]; 
        ArrayList<String> tagArray = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        char[] textAsCharArray = fileToArray(filePath);
        String[] textAsStringArray = fileToStringArray(filePath);

        tagArray = storeTags(textAsCharArray);
        String tags[] = tagArray.toArray(new String[tagArray.size()]);
        
        int errorLine1;
        int errorLine2 = 0;
        
        errorLine1 = rule1Check(textAsStringArray);
        stack = rule2Check(tags);


        for(int i = 0; i < textAsStringArray.length; i ++){
          if(stack.getTop() != null){
            if (textAsStringArray[i].contains(stack.getTop())){
              errorLine2 = i;
              break;
            }
          }
          
        }

        if(errorLine1 == 0 && stack.isEmpty()){
          System.out.println("Congratulations...");
          System.out.println("The given HTML file meets all the tag rules..");
        }
        
        else{
          System.out.println("Oops... There is a problem..");
          if(errorLine1 != 0){
            System.out.println("One of the tags on line " + errorLine1 + " is missing a < or >");
          if(errorLine2 != 0){
            System.out.println("The " + stack.getTop() + " tag at line # " + errorLine2 + "does not meet the tag rules..");

          }
          }
          
        }
         
        
    }

    


    public static int rule1Check(String[] text)throws Exception{
      Stack<Character> stack = new Stack<>();
      boolean open = false; 
      boolean fail = false;
      int lineCount = 1;
      for(String line : text){
        if(line != null){
          char[] lineAsChars = new char[line.length()];
          for(char ch : lineAsChars){
            if(ch == '<'){
              if(!open){
                stack.push(ch);
                open = true;
              }
              fail = true;
              
            }
            else if(ch == '>' ){
              if(open){
                stack.pop();
                open = false;
              }
              fail = true;
            }
          }
        if(fail || !stack.isEmpty()){
          return lineCount;
        }
        lineCount += 1;
        }
      }
      
      return 0;
      
    }

    public static Stack<String> rule2Check(String[] tagArray){
      Stack<String> stack = new Stack<>();
      for(String element : tagArray){
        char elementChar[] = element.toCharArray();
        if(!element.contains("/") && elementChar[1] != '!' && !element.equals("<br>") && !element.equals("<hr>")){
          stack.push(element);
          System.out.println("Pushing: " + element + " endtag: " + getEndTag(element));
        }
        else if(element.equals(getEndTag(stack.getTop()))){
          String delElement = stack.pop();
          System.out.println("Popping: " + delElement);
        }
      }
      if(stack.isEmpty()){
        // System.out.println("Rule 2 passed");
        return stack;
      }
      // System.out.println("Rule 2 failed");
      return stack;

    }

    
    public static String getEndTag(String tag){
      if(tag == null){
        return null;
      }
      String commonTags[] = {"html", "head", "title", "body", "center"};
      String endTag;
      char tagChar[] = tag.toCharArray();
      
      
      for(String element : commonTags){
        if(tag.contains(element)){
          endTag = "</" + element + '>';
          return endTag;
        }
      }
      
      if(tagChar.length == 3){
        endTag = "</" + tagChar[1] + '>';
        return endTag;
      }

      if(tagChar[1] == 'h'){
        endTag = "</" + tagChar[1] + tagChar[2] + '>';
        return endTag;
      }

      if(tagChar[1] == 'a'){
        endTag = "</" + 'a' + '>';
        return endTag;
      }
      
      return null;


    }
    
    
    // Store all tags
    public static ArrayList<String> storeTags(char[] textAsArray){
      int length = textAsArray.length;
      String tag;
      int begin = 0;
      int end = 0;
      ArrayList<String> tagArray = new ArrayList<>();

      for(int i = 0; i < length; i++){
        if(textAsArray[i] == '<'){
          begin = i;
        }

        else if(textAsArray[i] == '>'){
          end = i;
          tag = getTag(textAsArray, begin, end);
          tagArray.add(tag);
        } 
      }

      return tagArray;
    }
      
    


    // Gets a specific tag by the indes of '<' and '>''
    public static String getTag(char[] textAsArray, int begin, int end){
      StringBuilder tag = new StringBuilder();
      for(int start = begin;  start <= end; start++){
        if(textAsArray[start] != '\r'){
          tag.append(textAsArray[start]);
        }
          
      }
      return tag.toString();

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
      // System.out.print(textFileAsArray[k]);
      
      k++;
    }
    br.close();
    
    return textFileAsArray;

  }

    public static String[] fileToStringArray(String filePath)throws Exception{
    File file = new File(filePath);
    FileReader fr = new FileReader(file);
    BufferedReader br = new BufferedReader(fr);
    int k = 0;
    int approxLength = (int)(file.length() + 10);
    String[] textFileAsArray = new String[approxLength];

    while(br.ready()){
      textFileAsArray[k] = br.readLine();
      // System.out.print(textFileAsArray[k]);
      
      k++;
    }
    br.close();
    
    return textFileAsArray;

  }
}