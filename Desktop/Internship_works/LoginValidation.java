import java.util.*;
public class LoginValidation {
    public static void main(String[] args) {
       Scanner sc=new Scanner(System.in);
       System.out.println("Enter the UserName:");
       String name=sc.next();
       System.out.println("Enter The password");
       int key=sc.nextInt();

       if(name.equals("Admin")&& key==(1234)){
        System.out.println("Login Successful");
       }
       else{
        System.out.println("Invalid Credentials");
       }
    //    if(name.equals("Admin")){
    //         if(key==(1234)){
    //             System.out.println("Login successful");
    //         }
    //         else{
    //             System.out.println("Invalid Password");
    //         }
    //    }
    //    else{
    //     System.out.println("Invalid UserName");
    //    }
    }
}
