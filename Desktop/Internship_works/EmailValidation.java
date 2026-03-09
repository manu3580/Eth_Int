import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;
import java.util.regex.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface ValidEmail{
    String message() default "Invalid Email";
}

class Student{

    @ValidEmail
    String email;

    Student(String email){
        this.email=email;
    }
}

public class EmailValidation{

    public static void main(String[] args) throws Exception{

        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Email:");
        String email=sc.nextLine();
        Student s=new Student(email);
        checkEmail(s);
    }

    public static void checkEmail(Object obj) throws Exception{

        Class c=obj.getClass();
        Field[] fields=c.getDeclaredFields();

        Map<String,String> map=new HashMap<>();

        for(Field f:fields){

            if(f.isAnnotationPresent(ValidEmail.class)){

                f.setAccessible(true);
                String value=(String)f.get(obj);

                String regex="^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

                Pattern p=Pattern.compile(regex);
                Matcher m=p.matcher(value);

                if(m.matches()){
                    map.put(f.getName(),"Valid Email");
                }else{
                    ValidEmail v=f.getAnnotation(ValidEmail.class);
                    map.put(f.getName(),v.message());
                }
            }
        }

        for(String key:map.keySet()){
            System.out.println(key+" : "+map.get(key));
        }
    }
}