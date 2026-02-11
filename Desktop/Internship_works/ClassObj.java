class Car{
    String brand;
    
    int year;
    void display(){
        System.out.println("Brand :"+brand);
        System.out.println("Year :"+year);
    }
}

class Mine{
    String Place;
    String name;
    void display1(){
        System.out.println("Name :"+name);
        System.out.println("Native :"+Place);
    }
}
public class ClassObj {
    public static void main(String[] args) {
        Car car1 = new Car();
        car1.brand="Toyota";
        car1.year=2020;
        car1.display();
        Mine mine=new Mine();
        mine.name="MANU";
        mine.Place="Haveri";
        mine.display1();

    }
}
