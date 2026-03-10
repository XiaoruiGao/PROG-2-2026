public class ERyder {
    private static final String COMPANY_NAME = "ERyder";
    private static final double BASE_FARE = 1.0;
    private static final double PER_MINUTE_FARE = 0.5;

    private final String LINKED_ACCOUNT;
    private final String LINKED_PHONE_NUMBER;

    private int totalUsageInMinutes;
    private double totalFare;
    private String bikeID;
    private int batteryLevel;
    public boolean isAvailable;
    public double kmDriven;

    public ERyder(String bikeID, int batteryLevel, boolean isAvailable, double kmDriven, String linkedAccount, String linkedPhoneNumber) {
        this.bikeID = bikeID;
        this.batteryLevel = batteryLevel;
        this.isAvailable = isAvailable;
        this.kmDriven = kmDriven;
        this.LINKED_ACCOUNT = linkedAccount;
        this.LINKED_PHONE_NUMBER = linkedPhoneNumber;

    }
    public ERyder(String bikeID, String linkedAccount, String linkedPhoneNumber){
        this.bikeID = bikeID;
        this.batteryLevel = 100;
        this.isAvailable = true;
        this.kmDriven = 0.0;
        this.LINKED_ACCOUNT = linkedAccount;
        this.LINKED_PHONE_NUMBER = linkedPhoneNumber;
    }
    public void printRideDetails(int totalUsageInMinutes){
        this.totalUsageInMinutes = totalUsageInMinutes;
        this.totalFare = calculateFare(totalUsageInMinutes);
       
        System.out.println("BikeID" + this.bikeID);
        System.out.println("Linked Account" + LINKED_ACCOUNT);
        System.out.println("Linked phone number" + LINKED_PHONE_NUMBER);
        System.out.println("Usage time" + totalUsageInMinutes + " minutes");
        System.out.println("Total fare: " + totalFare);
    }
    private double calculateFare(int usageInMinutes){
        return BASE_FARE + (PER_MINUTE_FARE * usageInMinutes);
      
    }
    public static void main(String[] args){
        ERyder bike1 = new ERyder("12345", 80, true, 15.5, "HD2345", "123456");
        ERyder bike2 = new ERyder("123456", "JG3456", "98765");
        bike1.printRideDetails(30);
        System.out.println();
        bike2.printRideDetails(40);
    }
    
}
