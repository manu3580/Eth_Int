/*Elctricity bill calculation First 100unit 5 rupees per unit 100unit 7U above 200 u 10Rs per Unit */
import java.util.*;
public class Electricity {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Electricity Bill Calculation");
        System.out.println();
        System.out.println("Enter the Units you used: ");
        int Units=sc.nextInt();
        int Bill=0;
        
        // if(Units<=100){
        //     Bill=Units*5;
        // }
        // else if(Units<=200){
        //     Bill=(100*5)+(Units-100)*7;
        // }
        // if(Units{
        //     Bill=(100*5)+(100*7)+(Units-200)*10;
        // }

        Bill=Units<=100?Units*5 :
        Units<=200 ? ((100*5)+((Units-100)*7)) :
        ((100*5)+(100*7)+(Units-200)*10);
        
        // if(Units<=300 || Units>300){
        //     Units=(100*5)+(100*7)+(Units-200)*10;
        // }
        System.out.println("Bill : "+Bill);
    }
    
}
//login validation user name=admin and Password =1234