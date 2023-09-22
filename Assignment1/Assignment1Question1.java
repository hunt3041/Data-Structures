package Assignment1;
public class Assignment1Question1{

public static void main(String[] args) {

    String stringInput = "World";

    // Creating an array of stringInput length without using length() method
    int length = 0;
    for (char ch : stringInput.toCharArray()) {
      length++;
    }
    // System.out.print("Length of array: ");
    // System.out.println(stringInput.toCharArray().length);
    // StringBuffer objects are mutable, which allow you to modify the contents of a string.
    StringBuffer output = new StringBuffer("Hello, ");
    // Add each character from stringInput to the end of the output using append()
    for (int i = 0; i < length; i++) {
        output.append(stringInput.charAt(i));
    }
    System.out.println(output);
    
}
}