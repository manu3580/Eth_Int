import java.util.*;

public class TaxiBooking {
    static List<Taxidetails> taxis =new ArrayList<>();
    static int bookingCounter=1;


    //Distance
    static int calculateDistance(char From,char To){
        return Math.abs(From-To)*15;/*Abs for Absolute value  (remove the Math method)
                                    From-To means Destination-Pickup point
                                    multiply with 15 beacause distance b/w One P-P */
    }


    //Earnings
    static int calculateEarning(int distance){
        if(distance<=5){
            return 100;
        }
        return 100+(distance-5)*10;/*Example:A->B distance=15km
                                    100+(15-5)*10->100+100=200*/
    }
    
    //Travel Time
    static int calculateTravelTime(char From,char To){
        return Math.abs(From-To);/*Math.abs for Giving the integer value by Taking the ASCII value of 
                                    the single Character*/
    }

    //Taxi Booking with these inputs
    static Taxidetails bookTaxi(int CustomerId,char From,char To,int PickUpTime) 
            throws TaxiNotAvailableException {

        Taxidetails allocatedTaxi=null;//stores which taxi 
        int minDist=Integer.MAX_VALUE;//check for minimum value
        
        for(Taxidetails taxi:taxis){
            if(taxi.FreeTime<=PickUpTime){//check free taxi based on requested time
                int distance=Math.abs(taxi.CurrentLocation-From);

                if(distance<minDist){/*if the distance is less than the minDist then 
                                        assign the distance value to minDist and 
                                        allocate the nearest distance taxi */
                    minDist=distance;
                    allocatedTaxi=taxi;
                }
            }
        }

        if(allocatedTaxi==null){

            throw new TaxiNotAvailableException("Booking Rejected. No Taxi available");

        }
        else{
            Booking booking=new Booking();
            booking.BookingId=bookingCounter++;
            booking.CustomerId=CustomerId;//Right side:booking object variable , store customer ID
                                          //Left :Method input variable 
            booking.From=From;
            booking.To=To;
            booking.PickUpTime=PickUpTime;
            int travelTime=calculateTravelTime(From, To);
            booking.DropTime=PickUpTime+travelTime;
            int distance=calculateDistance(From, To);
            int amount=calculateEarning(distance);
            booking.Amount=amount;


            allocatedTaxi.Bookings.add(booking);
            allocatedTaxi.Earnings+=amount;
            allocatedTaxi.CurrentLocation=To;
            allocatedTaxi.FreeTime=booking.DropTime;

            // System.out.println("Taxi "+allocatedTaxi.TaxiId+" is Allotted to customer Id "+booking.CustomerId);
            
        }
        return allocatedTaxi;

    }


   static void displayTaxiDetails(){
        for(Taxidetails taxi:taxis){
            System.out.println("Taxi "+taxi.TaxiId+ " Total Earnings:Rs."+taxi.Earnings);
            for(Booking booking:taxi.Bookings){
                System.out.println(
                    "Booking ID: "+booking.BookingId+
                    " Customer ID: "+booking.CustomerId+
                    " From: "+booking.From+
                    " To: "+booking.To+
                    " PickUp Time: "+booking.PickUpTime+
                    " Drop Time: "+booking.DropTime+
                    " Total Charge: "+booking.Amount
                );
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    // creating 4 taxis
    for(int i=1;i<=2;i++)
        taxis.add(new Taxidetails(i));


    while(true) {

        System.out.println("\n1. Book Taxi");
        System.out.println("2. Display Details");
        System.out.println("3. Exit");

        System.out.print("Enter your choice: ");

        int choice = sc.nextInt();


        if(choice == 1) {

            try {

                System.out.print("Enter Customer ID: ");
                int CustomerId = sc.nextInt();

                System.out.print("Enter Pickup Point: ");
                char From = sc.next().charAt(0);

                System.out.print("Enter Drop Point: ");
                char To = sc.next().charAt(0);

                System.out.print("Enter Pickup Time: ");
                int PickUpTime = sc.nextInt();


                Taxidetails taxi = bookTaxi(CustomerId, From, To, PickUpTime);

                System.out.println("Taxi-"+taxi.TaxiId+" Allotted");


            }
            catch(Exception e) {

                System.out.println(e.getMessage());

            }

        }

        else if(choice == 2) {

            displayTaxiDetails();

        }

        else {

            System.out.println("Thank You");
            break;

        }

    }

}
}
