


// public class Comparator {
//      // only works for my Record data type
//      protected int compare(AdaptablePQEntry<String,Record> entry1, AdaptablePQEntry<String,Record> entry2){
//         String unosStatus1 = entry1.getKey();
//         String unosStatus2 = entry2.getKey();
//         System.out.println("running custom compare function");
//         if(unosStatus1 == "" || unosStatus2 == "")
//             if(unosStatus1 == "" && unosStatus2 != ""){
//                 return -1;
//             }

//             else if(unosStatus2 == "" && unosStatus1 != ""){
//                 return 1;
//             }
//             else{
//                 return 0;
//             }
        
//         if(unosStatus1.compareTo(unosStatus2) < 0){
//             return -1;
//         }
//         else if(unosStatus1.compareTo(unosStatus2) > 0){
//             return 1;
//         }

//         else{
//             String dob1 = entry1.getValue().getDateOfBirth();
//             String dob2 = entry2.getValue().getDateOfBirth();
//             if(dob1.compareTo(dob2) < 0){
//                 return -1;   
//             }
//             else if(dob1.compareTo(dob2) > 0){
//             return 1;
//             }
//             else{
//                 return 0;
//             }
//         }

        
//     }
// }
