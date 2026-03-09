import java.util.regex.*;
import java.lang.reflect.*;
import java.util.*;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface validName{
    String  message() default "Invalid Name";
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface validPhone{
    String message() default "Invalid Phone Number";
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface validMail{
    String message() default "Invalid Email";
}

class Employee{
    @validName
    String name;

    @validPhone
    String phoneNo;

    @validMail
    String email;

    Employee(String name, String phoneNo,String email){
        this.name=name;
        this.phoneNo=phoneNo;
        this.email=email;
    }
}
public class EmployeeValidation {
    public static void main(String[] args) throws Exception{
        Scanner sc =new Scanner(System.in);
        System.out.println("Enter name");
        String name=sc.nextLine();

        System.out.println("Enter phone Number :");
        String phoneNo=sc.nextLine();

        System.out.println("Enter email:");
        String email =sc.nextLine();

        Employee emp=new Employee(name, phoneNo, email);
        checkDetails(emp);
    }

    public static void checkDetails( Object obj)throws Exception{
        Class c=obj.getClass();
        Field[] fields=c.getDeclaredFields();

        Map<String,String> map=new HashMap<>();

        for(Field f:fields){
            f.setAccessible(true);
            String value=(String)f.get(obj);

            if(f.isAnnotationPresent(validName.class)){
                String regex="^[A-Za-z ]+$";
                Pattern p=Pattern.compile(regex);
                Matcher m=p.matcher(value);

                if(m.matches()){
                    map.put(f.getName(),"valid Name");
                }
                else{
                    validName v=f.getAnnotation(validName.class);
                    map.put(f.getName(),v.message());
                }
            }

            if(f.isAnnotationPresent(validPhone.class)){
                String regex="^[0-9]{10}+$";
                Pattern p=Pattern.compile(regex);
                Matcher m=p.matcher(value);

                if(m.matches()){
                    map.put(f.getName(),"valid Number");
                }
                else{
                    validPhone v=f.getAnnotation(validPhone.class);
                    map.put(f.getName(),v.message());
                }
            }

            if(f.isAnnotationPresent(validMail.class)){

                String regex="^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
                Pattern p=Pattern.compile(regex);
                Matcher m=p.matcher(value);

                if(m.matches()){
                    map.put(f.getName(),"Valid Email");
                }else{
                    validMail v=f.getAnnotation(validMail.class);
                    map.put(f.getName(),v.message());
                }
            }
        }
        for(String key:map.keySet()){
            System.out.println(key+" : "+map.get(key));
        }
    }
}
