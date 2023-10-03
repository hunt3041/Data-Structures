import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)throws Exception{
      Scanner scan = new Scanner(System.in);
      DoublyLinkedList<Course> courseList = new DoublyLinkedList<>();
      printMenu();
      while(true){
        String userInput = scan.nextLine();
        if(userInput.equals("1")){
          courseList = readData();
        }
        else if(userInput.equals("2")){
          System.out.println("Enter the course number to delete: ");
          String userInput2 = scan.nextLine();
          // System.out.println(userInput);
          courseList = deleteCourse(courseList, userInput2);
          
        }
        else{
          System.out.println("Enter another option");
        }
      }
    
    }

   
    public static DoublyLinkedList<Course> deleteCourse(DoublyLinkedList<Course> courseList, String delCourse){
      Iterator<Course> itr = courseList.iterator();
      String courseNum;
      delCourse = delCourse.replaceAll("\\s","" );
      while(itr.hasNext()){
        courseNum = itr.next().getCourseNumber();
        courseNum = courseNum.replaceAll("\\s", "");
        if(courseNum.equalsIgnoreCase(delCourse)){
          itr.remove(); 
          System.out.println("Course deleted");
        }
      } 
      Iterator<Course> iter = courseList.iterator();
      System.out.println("Printing new course list");
      while(iter.hasNext()){
        courseNum = iter.next().getCourseNumber();
        System.out.println(courseNum);
      }
      
       return courseList; 
          
    }
      

      

      
    
    // First reads data into 2D array and then creates the doubly linked list for the courses 
    // and the singly linked list for the students
    // Does not sort
    // does not delete duplicate courses
    public static DoublyLinkedList<Course> readData()throws Exception{
      String fileName = "/Users/Hunter/Data Structures/Assignment2/inputFile.txt";
      String[][] fileToArray = fileToArray(fileName); 
      DoublyLinkedList<Course> courseList = new DoublyLinkedList<>(); 
      int totalCourses = 0;
      courseList = createLists(fileToArray, courseList);
      System.out.println("Input file read successfully");
      System.out.println("Summary of the recod: ");
      System.out.println("Number of courses registered: " + totalCourses);
      System.out.println("Number of total students: " + courseList.size());
      return courseList;
    }
    // Prints the user option menu
    public static void printMenu(){
      System.out.println("Enter one of the following options: ");
        System.out.println("1.  Read the input data");
        System.out.println("2.  Delete a course");
        System.out.println("3.  Insert a new course");
        System.out.println("4.  Delete a student");
        System.out.println("5.  Insert a new student");
        System.out.println("6.  Transfer a student from one course to another");
        System.out.println("7.  Display the course list");
        System.out.println("8.  Display the student list");
        System.out.println("9.  Exit");
    }
    
    // Sorts the courses using bubble sort
    public static void sortCourses(DoublyLinkedList<Course> courseList){
      

    }
    
    // Creates the linked list structure from the 2D array
    public static DoublyLinkedList<Course> createLists(String[][] array, DoublyLinkedList<Course> list){
      String courseNumber = null;
      String courseName = null;
      String studentName = null;
      String studentID = null;
      String email = null;
      String address = null;
      for(int k = 0; k < array.length; k++){
        for(int j = 0; j < array[0].length;j++){
          if((j == 0)){courseNumber = array[k][j];}

          else if(j == 1){courseName = array[k][j]; }

          else if(j == 2){studentName = array[k][j];}

          else if(j == 3){studentID = array[k][j];}

          else if(j == 4){email = array[k][j];}

          else if(j == 5){address = array[k][j];}
          
          else{continue;}
        }
        Student newStudent = new Student(studentName, studentID, email, address);
        Course course = new Course(courseNumber, courseName, newStudent);
        list.addLast(course);
      }
      return list;
    }



  // Stores the inputfile.txt into an array to be parsed and stored in the linked lists
  public static String[][] fileToArray(String fileName)throws Exception{
    File file = new File(fileName);
    FileReader fr = new FileReader(file);
    BufferedReader br = new BufferedReader(fr);
    int k = 0;
    br.readLine();
    String[][] textFileAsArray = new String[915][20];

    while(br.ready()){
      String[] lineAsArray = new String[20];
      
      lineAsArray = br.readLine().split("\t");
      
      for(int j = 0; j < lineAsArray.length; j++){
        textFileAsArray[k][j] = lineAsArray[j];
        
      } 
      k++;
    }
    br.close();
    return textFileAsArray;

  }
}
