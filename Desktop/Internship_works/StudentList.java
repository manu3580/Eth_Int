/*
    1.Store 5 student name in String array store their marks
     in the int array display name , total marks,and grade
*/

import java.util.*;
public class StudentList {
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        System.out.println("Student Details:");
        String Name[]=new String[5];
        int marks[]=new int[5];
        char grade[]=new char[5];

        for(int i=0;i<5;i++){
            System.out.println("Enter the Name of Student");
            Name[i]=sc.next();

            System.out.println("Enter the marks");
            marks[i]=sc.nextInt();

            if(marks[i]>=90){
                grade[i]= 'A';
                
            }
            else if(marks[i]>=75){
                grade[i]='B';
                
            }
            else if(marks[i]>=60){
                grade[i]='C';
                
            }
            else{
                grade[i]='D';
                
            }
        }
        for(int i=0;i<5;i++){
            System.out.println(Name[i]+" : "+marks[i]+" : "+grade[i]);
        }
    }
}
