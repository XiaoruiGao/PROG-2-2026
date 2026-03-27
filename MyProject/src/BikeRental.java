import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Iterator;
class UserRegistration{
    public void registration(){
        System.out.println("Processing user registration...");
    }
}
class ActiveRental{
    private String bikeID;
    public ActiveRental(String bikeID){
        this.bikeID = bikeID;
    }
    public String getBikeID(){
        return bikeID;
    }
}
class Bike {
    private String bikeID;
    private String location;
    private boolean available;

    public Bike(String bikeID, String location, boolean available) {
        this.bikeID = bikeID;
        this.location = location;
        this.available = available;
    }

    public String getBikeID() {
        return bikeID;
    }

    public String getLocation() {
        return location;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
class BikeDatabase {
    public static LinkedList<Bike> bikes = new LinkedList<>();

    static {
       
        bikes.add(new Bike("B001", "Center", true));
        bikes.add(new Bike("B002", "Campus", true));
    }
}
public class BikeRental {
    private boolean isRegisteredUser;
    private String emailAddress;
    private String location;
    private LocalDateTime tripStartTime;
    private String bikeID;
    private boolean locationValid;
    private UserRegistration userRegistration = new UserRegistration();
    private ActiveRental activeRental;
    private LinkedList<ActiveRental> activeRentalsList = new LinkedList<>();
    private Scanner scanner = new Scanner(System.in);
    public void simulateApplicationInput(){
        System.out.println("This is the simulation of the e-bike rental process.");
        System.out.print("Are you a registered user?(true/false): ");
        isRegisteredUser = scanner.nextBoolean();
        scanner.nextLine();
        System.out.print("Please enter your email address: ");
        emailAddress = scanner.nextLine();
        System.out.print("Please enter the rental location: ");
        location = scanner.nextLine();
        System.out.println("Simulating the analysis of the rental request.");
        bikeID = analyseRequest(isRegisteredUser, emailAddress, location);
        if(!locationValid){
            return;
        }
        System.out.println("Simulating e-bike reservation...");
        reserveBike(bikeID);
        System.out.println("Displaying the active rentals...");
        viewActiveRentals();
        System.out.println("Simulating the end of the trip...");
        removeTrip(bikeID);
        System.out.println("Displaying the active rentals after trip end...");
        viewActiveRentals();
    }   
    private String analyseRequest(boolean isRegistered, String email, String location){
        if(isRegistered){
            System.out.println("Welcome back, " + email + "!");
        }else{
            System.out.println("You're not our registered user. Please consider registering.");
            userRegistration.registration();

        }
        return validateLocation(location);
    }
public String validateLocation(String location){
    for (Bike bike : BikeDatabase.bikes) {
            if (bike.getLocation().equals(location) && bike.isAvailable()) {
                System.out.println("A bike is available at the location you requested.");
                locationValid = true;
                return bike.getBikeID();
            }
        }
    System.out.println("Sorry, no bikes are available at the location you requested. Please try again later.");
        locationValid = false;
        return null;
}
    private void reserveBike(String bikeID) {
        if (bikeID == null) return;

        activeRental = new ActiveRental(bikeID);
        activeRentalsList.add(activeRental);

        for (Bike bike : BikeDatabase.bikes) {
            if (bike.getBikeID().equals(bikeID)) {
                bike.setAvailable(false);
                System.out.println("Bike " + bikeID + " reserved successfully!");
                break;
            }
        }
    }
     private void viewActiveRentals() {
        if (activeRentalsList.isEmpty()) {
            System.out.println("No active rentals.");
            return;
        }
        for (ActiveRental ar : activeRentalsList) {
            System.out.println("Active rental: Bike " + ar.getBikeID());
        }
    }
     private void removeTrip(String bikeID) {
        Iterator<ActiveRental> iterator = activeRentalsList.iterator();
        while (iterator.hasNext()) {
            ActiveRental ar = iterator.next();
            if (ar.getBikeID().equals(bikeID)) {
                iterator.remove();
                break;
            }
        }

        for (Bike bike : BikeDatabase.bikes) {
            if (bike.getBikeID().equals(bikeID)) {
                bike.setAvailable(true);
                System.out.println("Trip ended. Bike " + bikeID + " returned.");
                break;
            }
        }
    }
 public static void main(String[] args) {
        new BikeRental().simulateApplicationInput();
    }
}
