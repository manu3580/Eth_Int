import java.util.HashMap;
import java.util.Scanner;

public class Contact {
    public static void main(String[] args) {
        HashMap<String,String> contacts=new HashMap<>();
        Scanner sc=new Scanner(System.in);
        int choice;
        while(true){
            System.out.println("1.Insert Contact");
            System.out.println("2.Delete Contact");
            System.out.println("3.Search Contact");
            System.out.println("4.Display Contacts");
            System.out.println("5.Exit");
            System.out.println("Enter your Choice:");
            choice=sc.nextInt();
            sc.nextLine();
            switch (choice) {


                case 1:
                    System.out.println("Enter the Name :");
                    String name=sc.nextLine();
                    System.out.println("Enter Ph No. :");
                    String phNo=sc.nextLine();
                    contacts.put(name,phNo);
                    System.out.println("Contact Saved");
                    break;


                case 2:
                    System.out.println("Enter the Name you want delete:");
                    String delname=sc.nextLine();
                    if(contacts.containsKey(delname)){
                        contacts.remove(delname);
                        System.out.println("Contact Deleted");
                    }
                    else{
                        System.out.println("contact not found");
                    }
                    break;

                case 3:
                    System.out.println("search name:");
                    String searchName=sc.nextLine();
                    if(contacts.containsKey(searchName)){
                        System.out.println("Phone:"+contacts.get(searchName));
                    }
                    else{
                        System.out.println("Contact not found");
                    }
                    break;
                case 4:
                    if(contacts.isEmpty()){
                        System.out.println("Contact List is Empty");
                    }
                    else{
                    for(String key:contacts.keySet()) {
                        System.out.println("Name: "+key+"\nph No. "+contacts.get(key));
                    }
                    }
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Argument");
                    break;
            }
        }
    }
}
