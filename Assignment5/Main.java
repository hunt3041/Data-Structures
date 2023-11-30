import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String filePath = System.getProperty("user.dir") + "/" + args[0]; 
        AdjacencyMapGraph<StudentInfo, Integer> graph = new AdjacencyMapGraph<>(false);
        graph = readFileToGraph(filePath);
        System.out.println(graph.numVertices());
        System.out.println(graph.numEdges());
        // System.out.println(graph.toString());
        printMenu();
        Scanner scan = new Scanner(System.in);
        while (true) {
            int userIn = scan.nextInt();
            
            // option 1 Remove friendship
            if(userIn == 1){
                System.out.println("Enter the id of the first student");
                String id1 = scan.next();
                System.out.println("Enter the id of the second student");
                String id2 = scan.next();
                String firstName1 = null;
                String firstName2 = null;
                Vertex<StudentInfo> vertex1 = null;
                Vertex<StudentInfo> vertex2 = null;

                Iterable<Vertex<StudentInfo>> vertexIterable = graph.vertices();
                for(Vertex<StudentInfo> vertex : vertexIterable){
                    System.out.println(vertex);
                    if(vertex.getElement().getID().equals(id1)){
                        System.out.println("Student 1 found.");
                        vertex1 = vertex;
                        System.out.println(vertex1);
                        firstName1 = vertex.getElement().getFirstName();
                    }
                    if(vertex.getElement().getID().equals(id2)){
                        System.out.println("Student 2 found.");
                        vertex2 = vertex;
                        System.out.println(vertex2);
                        firstName2 = vertex.getElement().getFirstName();
                    }
                }

                try{
                    vertex1.getElement().decrementFriendCount();
                    vertex2.getElement().decrementFriendCount();
                    graph.removeEdge(graph.getEdge(vertex1,vertex2));
                    System.out.println("The edge between the students " + firstName1 + " and " + firstName2 + " has been successfully removed.");
                    System.out.println("Total number of students in the graph: " + graph.numVertices());
                    System.out.println("Total number of edges in the graph: " + graph.numEdges());
                }
                catch(IllegalArgumentException e){
                    System.out.println("Sorry.. There is no edge between the vertices " + firstName1 + " and " + firstName2);
                }


            }

            // option 2 delete account
            if(userIn == 2){
                System.out.println("Enter the first name of the account holder.");
                String firstName = scan.next();
                System.out.println(firstName);
                System.out.println("Enter the last name of the account holder.");
                String middleName = scan.next();
                String lastName = scan.next();
                lastName = middleName + " " + lastName; 
                System.out.println(lastName);
                boolean studentRemoved = false; 
                Iterable<Vertex<StudentInfo>> vertexIterable = graph.vertices();
                for(Vertex<StudentInfo> vertex : vertexIterable){
                    if(vertex.getElement().getFirstName().equalsIgnoreCase(firstName) && vertex.getElement().getLastName().equalsIgnoreCase(lastName)){
                        graph.removeVertex(vertex);  
                        studentRemoved = true;
                        System.out.println("The student " + firstName + " has been successfully removed.");
                        System.out.println("Total number of vertices in the graph: " + graph.numVertices());
                        System.out.println("Total number of edges in the graph: " + graph.numEdges());
                    }
                    
                }
                if(!studentRemoved){
                    System.out.println("Sorry...");
                    System.out.println(firstName + " not found!");
                }
            }
            
            // optoion 3 count friends
            if(userIn == 3){
                Iterable<Vertex<StudentInfo>> vertexIterable = graph.vertices();
                System.out.println("Enter the ID of the of the student.");
                String id = scan.next();
                Vertex<StudentInfo> student = null;
                int degree = 0;
                ArrayList<String> friendsList = new ArrayList<>();
                for(Vertex<StudentInfo> vertex : vertexIterable){
                    if(vertex.getElement().getID().equals(id)){
                        student = vertex;
                        degree = graph.outDegree(student);
                    }
                }
                Iterable<Edge<Integer>> edgesIterable = graph.outgoingEdges(student);
                for(Edge<Integer> edge : edgesIterable){
                    friendsList.add(graph.opposite(student, edge).getElement().getFirstName());
                }
                System.out.println("Student has " + degree + " friends.");
                System.out.println("Friends of " + student.getElement().getFirstName() + " are:");
                for(String friend : friendsList){
                    System.out.println(friend);
                }
                
            }
        }
    
    }

    
      
     public static AdjacencyMapGraph<StudentInfo, Integer> readFileToGraph(String filePath){
        AdjacencyMapGraph<StudentInfo, Integer> graph = new AdjacencyMapGraph<>(false);
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            
            while ((line = br.readLine()) != null) {
                String[] values = line.split("\t");
                if (values[0].equalsIgnoreCase("id")){}

                else{
                    // Split the line using semicolons as the separator
                    ArrayList<String> friends = new ArrayList<>();
                    for(int i = 7; i < values.length; i++){
                        friends.add(values[i]);
                    }
                    
                    StudentInfo studentInfo = new StudentInfo(values[0], values[1], values[2], values[3], values[4], values[5], Integer.parseInt(values[6]), friends);
                    graph.insertVertex(studentInfo);


                }
                
                
            }
            Iterable<Vertex<StudentInfo>> vertexIterable1 = graph.vertices();
            Iterable<Vertex<StudentInfo>> vertexIterable2 = graph.vertices();

            for (Vertex<StudentInfo> vertex1 : vertexIterable1) {
                // Access information about each vertex, for example:
                StudentInfo studentInfo = vertex1.getElement();
                for(String id : studentInfo.getFriends()){
                  for(Vertex<StudentInfo> vertex2 : vertexIterable2){
                    if(vertex2.getElement().getID().equalsIgnoreCase(id)){
                        try{
                            graph.insertEdge(vertex1, vertex2, 0);
                        }
                        
                        catch(IllegalArgumentException e){

                        }
                    }
                  }  
                }
            }
            System.out.println("Input file is read successfully");
        } 
        
        catch (IOException e) {
           System.out.println("File not found.");
        }
    return graph;
    }

    public static void printMenu(){
        System.out.println("1. Remove friendship");
        System.out.println("2. Delete Account");
        System.out.println("3. Count friends");
        System.out.println("4. Friends Circle");
        System.out.println("5. Closeness centrality");
        System.out.println("6. Exit");
    }
}
