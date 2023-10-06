import java.util.Iterator;

public class Course {
    private String courseName; 
    private String courseNumber;
    private SinglyLinkedList<Student> studentList = new SinglyLinkedList<>();
    
    private int studentCount;

    // Default constructor
    public Course(){
          
    }
    // Constructor for when no students are in course yet
    public Course(String courseNumber, String courseName){
        this.courseName = courseName;
        this.courseNumber = courseNumber;

    }

    // Constructor if all course information is known
    public Course(String courseNumber, String courseName, Student student){
        this.courseName = courseName;
        this.courseNumber = courseNumber;
        this.studentList.addFirst(student);
        this.studentCount = studentList.size();
        
    }


    // Returns the first student in the course's student list
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
        this.studentCount = this.studentList.size();
        sortStudents();
    }

    // adds an entire new list to the course
    public void addStudentList(SinglyLinkedList<Student> studentList, int size){
        this.studentCount = studentList.size();
        this.studentList = studentList;
    }

    // Sorts the student list by their first names as requested in the assignment
    //INSERTION/BUBBLE SORT
    // must be called after a student insertion
    public void sortStudents(){
        SinglyLinkedList<Student> sortedList = new SinglyLinkedList<>();
        Iterator<Student> itr = this.studentList.iterator();
        Student[] studentListAsArray = new Student[this.studentCount];

        // Stores the students into an array for sorting 
        int i = 0;
        while(itr.hasNext()){
            Student studentObj = itr.next();
            studentListAsArray[i] = studentObj;
            i++;
        }
        int length = studentList.size();
        Student temp;
        for(int j = 0; j < length; j++){
            for(int k = j + 1; k < length; k++){
                String temp1[] = studentListAsArray[j].getStudentName().split(" ");
                String temp2[] = studentListAsArray[k].getStudentName().split(" ");
                if(temp2[temp2.length - 1].compareTo(temp1[temp1.length - 1]) < 0){
                    temp = studentListAsArray[j];
                    studentListAsArray[j] = studentListAsArray[k];
                    studentListAsArray[k] = temp;
                }
            }
        } 

        for(int j = 0; j < length; j++){
            sortedList.addLast(studentListAsArray[j]);
        }
        
        this.studentList = sortedList;
        
    }
    
    
    // Prints the student list in format requested in assignment
    public void printStudentList(){
        Iterator<Student> itr = this.studentList.iterator();
        System.out.printf("%-15s %-25s %-25s %-40s%n", "Student's ID", "Student's Name", "Email", "Address");
        System.out.println();
        while(itr.hasNext()){
            Student student = itr.next();
            System.out.printf("%-15s %-25s %-25s %-40s%n", student.getStudentID(), student.getStudentName(), student.getEmail(), student.getAddress());

        }
    }

    // Removes a student from the course and returns the student object
    public Student dropStudent(String IDorName){
        Iterator<Student> itr = this.studentList.iterator();
        IDorName = IDorName.replaceAll("[^a-zA-Z0-9]", "");
        while(itr.hasNext()){
            Student student = itr.next();
            if(student.getStudentID().equalsIgnoreCase(IDorName)){
                itr.remove();
                this.studentCount = this.studentList.size();
                return student;
            }
            else if(student.getStudentName().replaceAll("[^a-zA-Z0-9]", "").equalsIgnoreCase(IDorName)){
                itr.remove();
                this.studentCount = this.studentList.size();
                return student;
            }
        }
        
        System.out.println("Student not found");
        return null;
        }
    }

   
    

