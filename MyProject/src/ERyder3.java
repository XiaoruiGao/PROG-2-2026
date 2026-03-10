public class ERyder3 {
    private String bikeID;
    private int batteryLevel;
    public boolean isAvailable;
    public float kmDriven;
    

public ERyder3(String bikeID, int batteryLevel, boolean isAvailable, float kmDriven){
    this.bikeID = bikeID;
    this.batteryLevel = batteryLevel;
    this.isAvailable = isAvailable;
    this.kmDriven = kmDriven;
}
public ERyder3(){
    this.bikeID = "Unknown";
    this.batteryLevel = 0;
    this.isAvailable = false;
    this.kmDriven = 0.0f;
}
public void ride(){
    if(this.batteryLevel > 0 && this.isAvailable){
        System.out.println("The bike isavailable.");
    }else{
        System.out.println("The bike is not available.");
    }
}
public void printBikeDetails(){
    System.out.println("Bike ID: " + this.bikeID);
    System.out.println("Battery Level: " + this.batteryLevel);
    System.out.println("Available: " + (this.isAvailable ? "Avaliable" : "Not Avaialbe"));
    System.out.println("Distance Travalied: " + this.kmDriven + " km");
}
public String getBikeId(){
    return bikeID;
}
public int getBatteryLevel(){
    return batteryLevel;
}
public boolean getIsAvailable(){
    return isAvailable;
}
public float getKmDriven(){
    return kmDriven;
}
public void setBatteryLevel(int batteryLevel){
    if (batteryLevel >= 0 && batteryLevel <= 100){
        this.batteryLevel = batteryLevel;
    }else{
        System.out.println("Error: Battery level must be between 0 and 100.");
    }
}
public void setIsAvailable(boolean isAvailable){
    this.isAvailable = isAvailable;
}
public void setKmDriven(float kmDriven){
    this.kmDriven = kmDriven;
}
public static void main(String[]args){
    ERyder3 bike = new ERyder3("123456", 80, true, 15.5f);
    bike.ride();
    bike.printBikeDetails();
    bike.setBatteryLevel(30);
    System.out.println("Current battery: " + bike.getBatteryLevel());
}
}