package myPackage;
import java.util.ArrayList;


/*
 * Class for storing all of the student information. 
 */



public class StudentInfo {
    private String id; 
    private String firstName;
    private String lastName;
    private String college;
    private String department;
    private String email;
    private int friendCount;
    private ArrayList<String> friends = new ArrayList<>();

    // Default constructor
    public StudentInfo(){};

    // Contructor with parameters
    public StudentInfo(String id, String firstName, String lastName, String college, String department, String email, int friendCount, ArrayList<String> friends){
        this.id = id;
        this. firstName = firstName;
        this.lastName = lastName;
        this.college = college;
        this.department = department;
        this.email = email;
        this.friendCount = friendCount;
        this.friends = friends;
    }

    // getter for id
    public String getID(){return this.id;}
    
    // getter for first name
    public String getFirstName(){return this.firstName;}

    // getter for last name
    public String getLastName(){return this.lastName;}

    // getter for college
    public String getCollege(){return this.college;}

    // getter for department
    public String getDepartment(){return this.department;}

    // getter for email
    public String getEmail(){return this.email;}

    // getter for friend count
    public int getFriendCount(){return this.friendCount;}

    // getter for friends list
    public ArrayList<String> getFriends(){return this.friends;}

    // increment friend count
    public int incrementFriendCount(){
        this.friendCount += 1;
        return this.friendCount;
    }

    // decrement friend count
    public int decrementFriendCount(){
        if(this.friendCount > 0){
            this.friendCount -= 1;
            return this.friendCount;
        }
        else{
            return 0;
        }
            
    }
}
