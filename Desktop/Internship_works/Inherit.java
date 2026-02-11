class Salary{
    String name;
    double salary;

    void display1(){
    System.out.println("Name:"+name);}
}
class Person extends Salary{

    void display(){
    System.out.println("Salary of A person:"+salary);
    }
}



public class Inherit {

    public static void main(String[] args) {
     Person p1=new Person();
     p1.name="MANU";
     p1.salary=50000;
     p1.display();  
     p1.display1(); 
    }
}