import java.util.Scanner;
class OopCal{
    double add(double n1,double n2){
        return n1+n2;
    }
    double sub(double n1,double n2){
        return n1-n2;
    }
    double mul(double n1,double n2){
        return n1*n2;
    }
    double div(double n1,double n2){
        return n1/n2;
    }
    
}

public class Calculator {
    public static void main(String args[]){
        double result=0;
        Scanner sc=new Scanner(System.in);
        OopCal cal=new OopCal();
        System.out.println("Simple calculator");
        double num1=sc.nextDouble();
        char op=sc.next().charAt(0);
        double num2=sc.nextDouble();
        switch (op) {
            case '+':
                result=cal.add(num1,num2);
                break;
            case '-':
                result=cal.sub(num1,num2);
                break;

            case '*':
                result=cal.mul(num1,num2);
                break;

            case '/':
                result=cal.div(num1,num2);
                break;
            default:
                System.out.println("Invalid operator");
                break;
        }
        System.out.println("Result:"+result);
    }
}
