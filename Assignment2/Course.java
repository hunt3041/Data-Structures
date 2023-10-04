import java.util.Iterator;

public class Course {
    private String courseName; 
    private String courseNumber;
    private SinglyLinkedList<Student> studentList = new SinglyLinkedList<>();
    
    private int studentCount = 1;

    // Default constructor
    public Course(){
          
    }

    public Course(String courseNumber, String courseName, Student student){
        this.courseName = courseName;
        this.courseNumber = courseNumber;
        this.studentList.addFirst(student);
        
        
    }

    // Ruturns the first student in the course's student list
    public Student getFirstStudent(){return this.studentList.first();}
    
    // Get the course name
    public String getCourseName(){return this.courseName;}

    // Set a new course number
    public void setCourseNumber(String courseNumber){this.courseNumber = courseNumber;}
    
    // Get the course number
    public String getCourseNumber(){return this.courseNumber;}

    // Get the number of students in the course
    public int getStudentCount(){return this.studentCount;}

    // Increment the number of students in the course by 1
    public void updateStudentCount(int studentCount){this.studentCount = studentCount;}

    // Adds student to the list of students
    public void addStudent(Student student){
        this.studentList.addFirst(student);
        this.studentCount = this.studentCount + 1;
    }

    
    public void addStudentList(SinglyLinkedList<Student> studentList){
        this.studentList = studentList;
    }
    // Sorts the student list
    public void sortStudents(){
        Iterator<Student> itr = this.studentList.iterator();
        /*
         * Implement sorting algorithm here
         */
    
    
    }
    // Prints the student list
    public void printStudentList(){
        Iterator<Student> itr = this.studentList.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next().getStudentName());
        }
    }



   
    
}
