public class Student {
    private String studentName; 
    private String studentID;
    private String email;
    private String address;

    


    public Student(String studentName, String studentID, String email, String address){
        this.studentName = studentName;
        this.studentID = studentID;
        this.email = email;
        this.address = address;
    }

    
    // Get the student's name
    public String getStudentName(){return this.studentName;}

    // Get the student's ID
    public String getStudentID(){return this.studentID;}

    // Get the student's email
    public String getEmail(){return this.email;}

    // Get the student's address
    public String getAddress(){return this.address;}

    

}
