import java.util.*;

public class Taxidetails {
    int TaxiId;
    char CurrentLocation;
    int FreeTime;
    int Earnings;
    List<Booking> Bookings;

    Taxidetails(int id){
        this.TaxiId=id;
        this.CurrentLocation='A';
        this.FreeTime=0;
        this.Earnings=0;
        this.Bookings=new ArrayList<>();
    }
}
