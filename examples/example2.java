import java.util.Scanner;

public class Diva {

    public static void main(String[] args) {
        int m;
        Scanner in = new Scanner(System.in);
        m=in.nextInt();
        in.nextLine(); 
        int [] num = new int[m];
        for (int i=0; i<m; i++){
            num[i]= in.nextInt();
        }
        int mayor1= num [0]*num [1];
          for (int i=0;i<=m-2;i++){
          for (int h=i+1;h<=m-1; h++){
              if((num[i]*num[h])>mayor1){
                  mayor1=num[i]*num[h];
              }
             }         
          }
        System.out.print(mayor1);
    }
