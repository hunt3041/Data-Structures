public class Record {
    // Variables
    private String firstName;
    private String lastName;
    private String address; 
    private String city;
    private String county;
    private String state;
    private String zip;
    private String phone1;
    private String phone2;
    private String email;
    private String dateListed;
    private String unosStatus;
    private String dateOfBirth; 

    //default constructor
    public Record(){}

    // Constructor for if values are known
    public Record(String firstName, String lastName, String address, String city, String county, String state, String zip, String phone1, String phone2, String email, String dateListed, String unosStatus, String dateOfBirth){
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.county = county;
        this.state = state;
        this.zip = zip;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.email = email;
        this.dateListed = dateListed;
        this.unosStatus = unosStatus;
        this.dateOfBirth = dateOfBirth;
    }

    
    // Get the patient's first name
    public String getFirstName(){return this.firstName;}

    // Get the patient's last name
    public String getLastName(){return this.lastName;}

    // Get the patient's address
    public String getAddress(){return this.address;}

    // Get the patient's city
    public String getCity(){return this.city;}

    // Get the patient's county
    public String getCounty(){return this.county;}

    // Get the patient's state
    public String getState(){return this.state;}

    // Get the patient's zip
    public String getZip(){return this.zip;}

    // Get the patient's phone1
    public String getPhone1(){return this.phone1;}

    // Get the patient's phone2
    public String getPhone2(){return this.phone2;}

    // Get the patient's county
    public String getEmail(){return this.email;}

    // Get the patient's date listed
    public String getDateListed(){return this.dateListed;}

    // Get the patient's UNOS Status
    public String getUnosStatus(){return this.unosStatus;}

    // Get the patient's DOB
    public String getDateOfBirth(){return this.dateOfBirth;}

    // Set the UNOS Status
    public void setUnosStatus(String newUnosStatus){
        this.unosStatus = newUnosStatus;
    }
}
