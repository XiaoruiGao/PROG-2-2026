import java.util.LinkedList;
import java.util.Iterator;

public class RentalService {

    private LinkedList<ActiveRental> activeRentalsList = new LinkedList<>();

    public void startRental(String bikeID) {
        if (bikeID == null) return;
        activeRentalsList.add(new ActiveRental(bikeID));
    }
 public void showActiveRentals() {
        if (activeRentalsList.isEmpty()) {
            System.out.println("No active rentals.");
            return;
        }
        for (ActiveRental ar : activeRentalsList) {
            System.out.println("Active rental: Bike " + ar.getBikeID());
        }
    }

    public void endRental(String bikeID) {
        if (bikeID == null) return;
        Iterator<ActiveRental> iterator = activeRentalsList.iterator();
        while (iterator.hasNext()) {
            ActiveRental ar = iterator.next();
            if (ar.getBikeID().equals(bikeID)) {
                iterator.remove();
                break;
            }
        }
    }
}