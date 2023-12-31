/*
 * Author Name: Hunter Green
 * Email: hungree@okstate.edu
 * Date: 10/6/23
 * Program Description: Data Structures Assignment2. Implements a course-student information management program.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)throws Exception{
      Scanner scan = new Scanner(System.in);
      DoublyLinkedList<Course> courseList = new DoublyLinkedList<>();
      printMenu();
      boolean option1 = false; 
      while(true){
        String userInput = scan.nextLine();

        // Read the data OPTION 1
        if(userInput.equals("1")){
          option1 = true;
          System.out.print("Enter the filepath of input file: ");
          String filePath = scan.nextLine();
          courseList = readData(filePath);
          if(courseList != null){
            courseList = deleteDuplicateCourses(courseList);
            courseList = sortCourses(courseList);
            System.out.println("Input file read successfully");
            summaryOfRecord(courseList);
          }
          
        }

        // Delete a course OPTION 2
        else if(userInput.equals("2") && option1){
          System.out.print("Enter the course number to delete: ");
          String userInput2 = scan.nextLine();
          // System.out.println(userInput);
          courseList = deleteCourse(courseList, userInput2);
          summaryOfRecord(courseList); 
          
        }

        // Insert a new course OPTION 3
        else if(userInput.equals("3") && option1){
          System.out.print("Enter the new course number to add: ");
          String newCourseNum = scan.nextLine();
          System.out.print("Enter the new course name for " + newCourseNum + ": ");
          String newCourseName = scan.nextLine();
          Course newCourse =  new Course(newCourseNum, newCourseName);
          courseList.addFirst(newCourse);
          courseList = sortCourses(courseList); 
          summaryOfRecord(courseList);
          
        }

        // Delete a student OPTION 4
        else if(userInput.equals("4") && option1){
          System.out.print("Enter the student ID number to delete: ");
          String studentID = scan.nextLine();
          System.out.print("Enter the course number from which the student is to be dropped from: ");
          String courseNum = scan.nextLine().replaceAll("\\s", "");;

          Iterator<Course> itr1 = courseList.iterator();
          while(itr1.hasNext()){
            Course course = itr1.next();
            if(course.getCourseNumber().replaceAll("\\s", "").equalsIgnoreCase(courseNum)){
              course.dropStudent(studentID);
              // System.out.println("Student removed");
            }
          }
          summaryOfRecord(courseList);
          
        }

        // Insert a new student OPTION 5
        else if(userInput.equals("5") && option1){
          System.out.print("Enter the course number the student wants to enroll to: ");
          String courseNum = scan.nextLine();
          System.out.print("Enter the student's name: ");
          String studentName = scan.nextLine();
          System.out.print("Enter the student's ID: ");
          String studentID = scan.nextLine();
          System.out.print("Enter the student's emergency contact address: ");
          String studentAddress = scan.nextLine();
          Student newStudent = new Student(studentName, studentID, null, studentAddress);
          Iterator<Course> itr = courseList.iterator();
          boolean success = false;
          while(itr.hasNext()){
            Course course = itr.next();
            if(course.getCourseNumber().replaceAll("\\s", "").equalsIgnoreCase(courseNum)){
              course.addStudent(newStudent);
              success = true;

            }
          }
          if(!success){
            System.out.println("Course not found");
          }
          summaryOfRecord(courseList);
          
        }
        
        // Transfer a student OPTION 6
        else if(userInput.equals("6")){
          System.out.print("Enter the student's name: ");
          String studentName = scan.nextLine();
          System.out.print("Enter the course number the student wants to drop from: ");
          String courseToDrop = scan.nextLine().replaceAll("\\s", "");
          System.out.print("Enter the course number the student wants to enroll to: ");
          String courseToEnroll = scan.nextLine().replaceAll("\\s", "");
          Student student = new Student();
          boolean dropCourseFound = false;
          boolean studentFound = false;
          boolean enrollCourseFound = false;

          Iterator<Course> itr = courseList.iterator();
          while(itr.hasNext()){
            Course course = itr.next();
            if(course.getCourseNumber().replaceAll("\\s", "").equalsIgnoreCase(courseToDrop)){
              student = course.dropStudent(studentName);
              dropCourseFound = true;
              if(student != null){
                studentFound = true;
              }
            }
          }
            if(studentFound){
              Iterator<Course> itr2 = courseList.iterator();
              while(itr2.hasNext()){
                Course course2 = itr2.next();
                if(course2.getCourseNumber().replaceAll("\\s", "").equalsIgnoreCase(courseToEnroll)){
                  course2.addStudent(student);
                }
              }
            
            } 
          summaryOfRecord(courseList);
          
        }

        // Display the course list OPTION 7
        else if(userInput.equals("7") && option1){
          System.out.println("The list of courses registered are as follows: ");
          Iterator<Course> itr = courseList.iterator();
          while(itr.hasNext()){
            Course course = itr.next();
            System.out.println("Course Number: " + course.getCourseNumber());
            System.out.println("Course Name: " + course.getCourseName());
            System.out.println("Number of students enrolled: " + course.getStudentCount() + "\n");
          }
          
        }

        // Display a student list OPTION 8 
        else if(userInput.equals("8") && option1){
          System.out.print("Enter the course number: ");
          String courseNum = scan.nextLine().replaceAll("\\s","");
          Iterator<Course> itr = courseList.iterator();
          boolean courseFound = false;
          while(itr.hasNext()){
            Course course = itr.next();
            if(course.getCourseNumber().replaceAll("\\s", "").equalsIgnoreCase(courseNum)){
              System.out.println("The list of students enrolled in the course " + courseNum.toUpperCase() + " are as follows:\n\n");
              course.printStudentList();
              courseFound = true;
            }
          }
          if(!courseFound){
            System.out.println("Course not found");
          }
        }

        else if(userInput.equals("9")){
          break;
        }
        else{
          System.out.println("Enter another option, must use option 1 first or exit\n\n");
          printMenu();
        }
      }
    
    }

    /*
     * Deletes a course from the course list
     * 
     * @param courseList: the doubly linked list that the course is being deleted from
     * @param delCourse: the course that is to be deleted from the list
     * @return courseList: the new list  
     */
    public static DoublyLinkedList<Course> deleteCourse(DoublyLinkedList<Course> courseList, String delCourse){
      Iterator<Course> itr = courseList.iterator();
      String courseNum;
      delCourse = delCourse.replaceAll("\\s","" );
      // deletes the specified course
      boolean courseRemoved = false;
      while(itr.hasNext()){
        courseNum = itr.next().getCourseNumber();
        courseNum = courseNum.replaceAll("\\s", "");
        if(courseNum.equalsIgnoreCase(delCourse)){
          itr.remove(); 
          courseRemoved = true;
          // System.out.println("Course deleted");
        }
      } 
      if(!courseRemoved){
        System.out.println("Course not found");
      }
      
      
       return courseList; 
          
    }
      

      
    /*
     * Prints of summary of the record as described in the assignment
     * 
     * @param courseList: the course list that the summary is extracted from
     */
    public static void summaryOfRecord(DoublyLinkedList<Course> courseList){
      System.out.println("Summary of the record: ");
      System.out.println("Number of courses registered: " + courseList.size());
      Iterator<Course> itr = courseList.iterator();
      int numStudents = 0;
      while(itr.hasNext()){
        numStudents = numStudents + itr.next().getStudentCount();
      }
      System.out.println("Number of total students: " + numStudents);
    }
    
    /*
     * Reads the data from the input file and stores it in the linked-list structure defined in the handout
     * 
     * @param filePath: the file path of the .txt file that is to be read
     * @return courseList: list of courses now in the structure defined in the handout
     */
    public static DoublyLinkedList<Course> readData(String filePath)throws Exception{
      try{
        String[][] fileToArray = fileToArray(filePath); 
        DoublyLinkedList<Course> courseList = new DoublyLinkedList<>(); 
        courseList = createLists(fileToArray, courseList);
        return courseList;
      }
      catch(FileNotFoundException e){
        System.out.println("Could not find file, select option 1 again and try a different path");}
        return null;
    }

    /*
     * Prints the option menu
     */
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

    /*
     * Deletes the duplicate courses that are created initially becuase of the way the .txt file must be read
     * 
     * @param courseList: the course list that the duplicates are deleted from 
     * @return courseList: the new courselist with no duplicates
     */
    public static DoublyLinkedList<Course> deleteDuplicateCourses(DoublyLinkedList<Course> courseList){
      Iterator<Course> itr1 = courseList.iterator();
      
      int counter;
      while(itr1.hasNext()){
        Iterator<Course> itr2 = courseList.iterator();
        SinglyLinkedList<Student> studentList= new SinglyLinkedList<>();
        Course course1 = new Course();
        counter = 0;
        course1 = itr1.next();
        while(itr2.hasNext()){
          Course course2 = new Course();
          course2 = itr2.next();
          if(course2.getCourseNumber().equals(course1.getCourseNumber())){
            if(counter > 0){
              studentList.addFirst(course2.getFirstStudent());
              itr2.remove();
            }
            
            counter++;
            
          }
        }
        studentList.addLast(course1.getFirstStudent());
        course1.addStudentList(studentList, studentList.size());
      }
      return courseList;
    }


    /*
     * Sorts the courses and students by alphabetical order using insertion sort
     * 
     * @param courseList: the course list to sort the courese and students
     * @return sortedList: the sorted list
     */
    public static DoublyLinkedList<Course> sortCourses(DoublyLinkedList<Course> courseList){
      DoublyLinkedList<Course> sortedList = new DoublyLinkedList<>();
      Iterator<Course> itr = courseList.iterator();
      Course[] courseListAsArray = new Course[courseList.size()];

      // Stores the courses into an array for sorting 
      int i = 0;
      while(itr.hasNext()){
          Course courseObj = itr.next();
          courseObj.sortStudents();
          courseListAsArray[i] = courseObj;
          i++;
      }
      int length = courseList.size();
      Course temp;
      for(int j = 0; j < length; j++){
          for(int k = j + 1; k < length; k++){
              if(courseListAsArray[k].getCourseNumber().compareTo(courseListAsArray[j].getCourseNumber()) < 0){
                  temp = courseListAsArray[j];
                  courseListAsArray[j] = courseListAsArray[k];
                  courseListAsArray[k] = temp;
              }
          }
      } 

      for(int j = 0; j < length; j++){
          sortedList.addLast(courseListAsArray[j]);
      }
      
      return sortedList;
      
  }

    
    
    /*
     * Creates the linked-list structure defined in the handout from the 2-D array that is produced in 
     * another function
     * 
     * @param array: 2-D array containing all the data in the .txt file
     * @param list: the list that we are adding new elements to
     * @return list: the fully populated list 
     */
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



  /*
   * Creates a 2-D array with the information in the .txt file
   * 
   * @param filePath: the file path to the inputFile.txt file
   * @return textFileAsArray: the 2-D array containing the file contents
   */
  public static String[][] fileToArray(String filePath)throws Exception{
    File file = new File(filePath);
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
