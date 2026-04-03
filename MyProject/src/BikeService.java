import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
public class BikeService {

    private static final Stack<ERyder> logStack = new Stack<>();
    private static final Queue<BikeRequest> requestQueue = new ArrayDeque<>();
    public String validateAndFindBike(String location) {
        for (Bike bike : BikeDatabase.bikes) {
            if (bike.getLocation().equals(location) && bike.getIsAvailable()) {
                System.out.println("A bike is available at the location you requested.");
                return bike.getBikeID();
            }
        }
        System.out.println("Sorry, no bikes are available at the location you requested. Please try again later.");
        return null;
    }
    public void reserveBike(String bikeID) {
        if (bikeID == null) return;
        for (Bike bike : BikeDatabase.bikes) {
            if (bike.getBikeID().equals(bikeID)) {
                bike.setIsAvailable(false);
                break;
            }
        }
    }
    public void returnBike(String bikeID) {
        if (bikeID == null) return;
        for (Bike bike : BikeDatabase.bikes) {
            if (bike.getBikeID().equals(bikeID)) {
                bike.setIsAvailable(true);
                break;
            }
        }
        processNextRequestFromQueue(); 
    }
   public void reserveBikeWithUser(String userEmail, String location) {
        String bikeID = validateAndFindBike(location);

        if (bikeID != null) {
        
            reserveBike(bikeID);
            addLogToStack(bikeID, "Bike rented by " + userEmail + " at " + location);
        } else {
         
            BikeRequest request = new BikeRequest(userEmail, location, LocalDateTime.now());
            requestQueue.offer(request);
            System.out.println("Request added to queue: " + userEmail + " | " + location);
        }
    }
    private void addLogToStack(String bikeID, String event) {
        ERyder log = new ERyderLog(bikeID, event, LocalDateTime.now());
        logStack.push(log);
    }

    private void processNextRequestFromQueue() {
        if (!requestQueue.isEmpty()) {
            BikeRequest nextRequest = requestQueue.poll();
            System.out.println("\nProcessing next request from queue: " + nextRequest.getUserEmail());
       
            reserveBikeWithUser(nextRequest.getUserEmail(), nextRequest.getLocation());
        }
    }
    public void viewSystemLogs() {
        System.out.println("\n===== SYSTEM LOGS (STACK) =====");
        if (logStack.isEmpty()) {
            System.out.println("No logs yet.");
            return;
        }
        for (ERyder log : logStack) {
            System.out.println(log);
        }
    }
    public void viewRequestQueue() {
        System.out.println("\n===== PENDING REQUESTS (QUEUE) =====");
        if (requestQueue.isEmpty()) {
            System.out.println("No pending requests.");
            return;
        }
        for (BikeRequest req : requestQueue) {
            System.out.println(req);
        }
    }
    public void updateQueue() {
        if (!requestQueue.isEmpty()) {
            BikeRequest removed = requestQueue.poll();
            System.out.println("Removed from queue: " + removed.getUserEmail());
        } else {
            System.out.println("Queue is empty.");
        }
    }
}