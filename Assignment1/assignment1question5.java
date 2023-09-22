package Assignment1;
public class assignment1question5 {
    public static void main(String[] args){
        int n ;
        int sum = 0;
        for(int i = 1; i < n; i = i*2){
            for(int j = n; j > 0; j = j/2){
                for(int k = j; k < n; k = k +2){
                    sum = sum + i + j + k;
                    System.out.println(sum);
                }
            }
        }
    }
}
