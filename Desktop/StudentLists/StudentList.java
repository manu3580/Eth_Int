package StudentLists;
/*
Store 5 student name, marks, and grade
and connect to MySQL database
*/

import java.util.*;
import java.sql.*;

public class StudentList {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String Name[] = new String[5];
        int marks[] = new int[5];
        char grade[] = new char[5];

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/studentdb",
                "root",
                "My_Pass_Word"
            );

            System.out.println("Connected to Database");

            
            PreparedStatement ps;

            System.out.println("Enter Student Details:");

            for(int i=0;i<5;i++){

                System.out.print("Enter Name: ");
                Name[i] = sc.next();

                System.out.print("Enter Marks: ");
                marks[i] = sc.nextInt();

                // Grade logic
                if(marks[i]>=90)
                    grade[i]='A';
                else if(marks[i]>=75)
                    grade[i]='B';
                else if(marks[i]>=60)
                    grade[i]='C';
                else
                    grade[i]='D';

                ps = con.prepareStatement(
                    "INSERT INTO student(name, marks, grade) VALUES(?,?,?)"
                );

                ps.setString(1, Name[i]);
                ps.setInt(2, marks[i]);
                ps.setString(3, String.valueOf(grade[i]));

                ps.executeUpdate();
            }

            System.out.println("\nStudent Records:");

            for(int i=0;i<5;i++){
                System.out.println(Name[i]+" : "+marks[i]+" : "+grade[i]);
            }

            con.close();

        }
        catch(Exception e){
            System.out.println(e);
        }

    }
}
