import java.util.*;

class Directory {

    class Person{
        String FirstName,LastName;
        String Gender;
        int age;

        Person(String Firstname,String LastName,int age,String Gender){
            this.FirstName=Firstname;
            this.LastName=LastName;
            this.age=age;
            this.Gender=Gender;
        }

        String getName(){
           if(Gender.equals("M"))
                return "Mr. "+FirstName+" "+LastName+"\tAge:"+age+"\tGender:"+Gender;
           else
                return "Ms. "+FirstName+" "+LastName+"\tAge:"+age+"\tGender:"+Gender;
        }
    }

    void bubbleSort(List<Person> list){
        for(int i=0;i<list.size()-1;i++){
            for(int j=0;j<list.size()-i-1;j++){
                if(list.get(j).age>list.get(j+1).age){
                    Person temp=list.get(j);
                    list.set(j,list.get(j+1));
                    list.set(j+1,temp);
                }
            }
        }
    }

    void Details(){
        Scanner sc=new Scanner(System.in);
        List<Person> list=new ArrayList<>();
        System.out.println("Enter number of Persons:");
        int n=sc.nextInt();
        sc.nextLine();
        for(int i=1;i<=n;i++){
            System.out.println("Person No."+i);
            System.out.println("Enter First Name:");
            String FirstName=sc.nextLine();
            System.out.println("Enter Last Name:");
            String LastName=sc.nextLine();
            System.out.println("Enter the Age:");
            int age=sc.nextInt();
            sc.nextLine();
            System.out.println("Enter The Gender:");
            String Gender=sc.nextLine();
            Person p=new Person(FirstName,LastName,age,Gender);
            list.add(p);
        }

        bubbleSort(list);

        for(Person p:list){
            System.out.println(p.getName());
        }
    }
}



public class NameDirectory {

    public static void main(String[] args) {

        Directory dir=new Directory();

        dir.Details();

    }

}