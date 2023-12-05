import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import myPackage.*;

public class Main {

    public static void main(String[] args) {
        Scanner scan1 = new Scanner(System.in);
        System.out.println("Please enter the name file you wish to read.");
        String fileName = scan1.next();
        String filePath = System.getProperty("user.dir") + "/" + fileName;
        AdjacencyMapGraph<StudentInfo, Integer> graph = new AdjacencyMapGraph<>(false);
        graph = readFileToGraph(filePath);
        // System.out.println(graph.numVertices());
        // System.out.println(graph.numEdges());
        // System.out.println(graph.toString());
        printMenu();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            int userIn = scanner.nextInt();
            
            // option 1 Remove friendship
            if(userIn == 1){
                Scanner scan = new Scanner(System.in); 
                System.out.println("Enter the first name of the first student.");
                String firstName1 = scan.next();
                System.out.println("Enter the first name of the second student");
                String firstName2 = scan.next();
                Vertex<StudentInfo> vertex1 = null;
                Vertex<StudentInfo> vertex2 = null;

                Iterable<Vertex<StudentInfo>> vertexIterable = graph.vertices();
                for(Vertex<StudentInfo> vertex : vertexIterable){
                    // System.out.println(vertex);
                    if(vertex.getElement().getFirstName().equalsIgnoreCase(firstName1)){
                        // System.out.println("Student 1 found.");
                        vertex1 = vertex;
                        // System.out.println(vertex1);
                        firstName1 = vertex.getElement().getFirstName();
                    }
                    if(vertex.getElement().getFirstName().equalsIgnoreCase(firstName2)){
                        // System.out.println("Student 2 found.");
                        vertex2 = vertex;
                        // System.out.println(vertex2);
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
                catch(NullPointerException e){
                    System.out.println("Sorry..");
                    if(vertex1 == null){
                        System.out.println(firstName1 + " not found!");
                    }
                    if(vertex2 == null){
                        System.out.println(firstName2 + " not found!");
                    }
                }

                
            }

            // option 2 delete account
            if(userIn == 2){
                Scanner scan = new Scanner(System.in);
                System.out.println("Enter the first name of the account holder.");
                String firstName = scan.next();
                boolean studentRemoved = false; 
                Iterable<Vertex<StudentInfo>> vertexIterable = graph.vertices();
                for(Vertex<StudentInfo> vertex : vertexIterable){
                    if(vertex.getElement().getFirstName().equalsIgnoreCase(firstName)){
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
                Scanner scan = new Scanner(System.in);
                Iterable<Vertex<StudentInfo>> vertexIterable = graph.vertices();
                System.out.println("Enter the first name of the of the student.");
                String firstName = scan.next();
                Vertex<StudentInfo> student = null;
                int degree = 0;
                ArrayList<String> friendsList = new ArrayList<>();
                for(Vertex<StudentInfo> vertex : vertexIterable){
                    if(vertex.getElement().getFirstName().equalsIgnoreCase(firstName)){
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

            // Close friends circle
            if(userIn == 4){
                Scanner scan = new Scanner(System.in);
                System.out.println("Enter the name of college.");
                String college = scan.nextLine();
                ArrayList <Vertex<StudentInfo>> visited = new ArrayList<>();
                ArrayList <Vertex<StudentInfo>> students = new ArrayList<>();   
                Iterable <Vertex<StudentInfo>> verts = graph.vertices();
                for(Vertex<StudentInfo> vert : verts) {
                    if(vert.getElement().getCollege().equalsIgnoreCase(college)){
                        students.add(vert);
                    }
                }
                if(!students.isEmpty()){
                    System.out.println("Following are the friend circles in the " + college);
                    for(Vertex<StudentInfo> student : students){
                        visited = BFS(graph, student);
                        for(Vertex<StudentInfo> friend : visited){
                            if(students.contains(friend)){
                                System.out.print("-" + friend.getElement().getFirstName());
                            }
                            
                        }
                        System.out.println();
                    }
                }
                else{
                    System.out.println("There are no students in the " + college);
                }  
                
            }

            // Closeness centrality
            if(userIn == 5){
                Scanner scan = new Scanner(System.in);
                Map<Vertex<StudentInfo>, Integer> cloud = new ProbeHashMap<>( );
                Iterable<Vertex<StudentInfo>> verts = graph.vertices();
                Vertex<StudentInfo> student = null;
                System.out.println("Please enter the first name of the student.");
                String firstName = scan.nextLine();
                for(Vertex<StudentInfo> vert : verts){
                    if(vert.getElement().getFirstName().equalsIgnoreCase(firstName)){
                        student = vert;
                    }
                }
                if(student != null){
                    cloud = shortestPathLengths(graph, student);
                    Iterable<Integer> distances = cloud.values();
                    double cc = 0;
                    for(Integer i : distances){
                        if(i > 0 && i != Integer.MAX_VALUE)
                            try{
                                // System.out.println("Distance from vertex: " + i);
                                cc = cc + 1/(double)i;
                            }
                            catch(ArithmeticException e){
                                cc = cc + 0;
                                continue;
                            }
                        
                    }
                    
                    double ccNorm = cc/(graph.numVertices() - 1);
                    System.out.println("The Closeness Centrality for " + firstName + ": " + cc);
                    System.out.println("The Normalized Closeness Centrality for " + firstName + ": " + ccNorm);
                
            }
            else{
                System.out.println("Sorry..");
                System.out.println(firstName + " not found!");
            }

            
            }

            if(userIn == 6){
                break;
            }
    
        }
    }

    
    // Reads the text file into the adjacency map graph
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
                    
                    StudentInfo studentInfo = new StudentInfo(values[0], values[1], values[2], values[3].replaceAll("\"", ""), values[4], values[5], Integer.parseInt(values[6]), friends);
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
                            graph.insertEdge(vertex1, vertex2, 1);
                        }
                        
                        catch(IllegalArgumentException e){

                        }
                    }
                  }  
                }
            }
            System.out.println("\nInput file is read successfully..");
            System.out.println("Total nuber of vertices in the graph: " + graph.numVertices());
            System.out.println("Total nubmer of edges in the graph: " + graph.numEdges() + "\n");
        } 
        
        catch (IOException e) {
           System.out.println("File not found.");
        }
    return graph;
    }

    // BFS search algorithm
    public static ArrayList<Vertex<StudentInfo>> BFS(AdjacencyMapGraph<StudentInfo, Integer> graph, Vertex<StudentInfo> vertex){
        Queue<Vertex<StudentInfo>> q = new Queue<>();
        ArrayList<Vertex<StudentInfo>> visited = new ArrayList<>();
        q.enqueue(vertex);
        visited.add(vertex);
        while(!q.isEmpty()){
            Vertex<StudentInfo> w = q.dequeue();
            ArrayList<Vertex<StudentInfo>> neighbors = new ArrayList<>();
            Iterable<Edge<Integer>> edges = graph.outgoingEdges(vertex);
            for(Edge<Integer> edge : edges){
                neighbors.add(graph.opposite(vertex, edge));
                
            }
            for(Vertex<StudentInfo> vert : neighbors){
                if(!visited.contains(vert)){
                    q.enqueue(vert);
                    visited.add(vert);
                }
            }
        }
        return visited;

    }
    
    // Computes shortest-path distances from src vertex to all reachable vertices of g. Using Dijkstra's Algorithm
    public static Map<Vertex<StudentInfo>, Integer> shortestPathLengths(Graph<StudentInfo,Integer> g, Vertex<StudentInfo> src) {
        // d.get(v) is upper bound on distance from src to v
        Map<Vertex<StudentInfo>, Integer> d = new ProbeHashMap<>( );
        // map reachable v to its d value
        Map<Vertex<StudentInfo>, Integer> cloud = new ProbeHashMap<>( );
        // pq will have vertices as elements, with d.get(v) as key
        AdaptablePriorityQueue<Integer, Vertex<StudentInfo>> pq;
        pq = new HeapAdaptablePriorityQueue<>( );
        // maps from vertex to its pq locator
        Map<Vertex<StudentInfo>, Entry<Integer,Vertex<StudentInfo>>> pqTokens;
        pqTokens = new ProbeHashMap<>( );
        // for each vertex v of the graph, add an entry to the priority queue, with
        // the source having distance 0 and all others having infinite distance
        for (Vertex<StudentInfo> v : g.vertices( )) {
            if (v == src)
            d.put(v,0);
            else
                d.put(v, Integer.MAX_VALUE);
            pqTokens.put(v, pq.insert(d.get(v), v)); // save entry for future updates
        }
        // now begin adding reachable vertices to the cloud
        while (!pq.isEmpty( )) {
            Entry<Integer, Vertex<StudentInfo>> entry = pq.removeMin( );
            int key = entry.getKey( );
            Vertex<StudentInfo> u = entry.getValue( );
            cloud.put(u, key); // this is actual distance to u
            pqTokens.remove(u); // u is no longer in pq
            for (Edge<Integer> e : g.outgoingEdges(u)) {
                Vertex<StudentInfo> v = g.opposite(u,e);
                if (cloud.get(v) == null) {
                    // perform relaxation step on edge (u,v)
                    int wgt = e.getElement( );
                    if (d.get(u) + wgt < d.get(v)) { // better path to v?
                        d.put(v, d.get(u) + wgt); // update the distance
                        pq.replaceKey(pqTokens.get(v), d.get(v)); // update the pq entry
                    }
                }
            }
        }
        return cloud; // this only includes reachable vertices
    }




    // Prints the menu 
    public static void printMenu(){
        System.out.println("1. Remove friendship");
        System.out.println("2. Delete Account");
        System.out.println("3. Count friends");
        System.out.println("4. Friends Circle");
        System.out.println("5. Closeness centrality");
        System.out.println("6. Exit");
    }
}
