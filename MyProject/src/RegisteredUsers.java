import java.util.Arrays;
public class RegisteredUsers {
    private String fullName;
    private String emailAddress;
    private String dateOfBirth;
    private long cardNumber;
    private String cardProvider;
    private String cardExpiryDate;
    private double feeToCharge;
    private int cvv;
    private String userType;
    private String[] lastThreeTrips;
    public RegisteredUsers(){
        this.lastThreeTrips = new String[3];
    }
    public RegisteredUsers(String fullName, String emailAddress, String dateOfBirth, long cardNumber, String cardExpiryDate, double feeToCharge, String cardProvider, int cvv, String userType, String[] lastThreeTrips){
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.dateOfBirth = dateOfBirth;
        this.cardNumber = cardNumber;
        this.cardProvider = cardProvider;
        this.cardExpiryDate = cardExpiryDate;
        this.feeToCharge = feeToCharge;
        this.cvv = cvv;
        this.userType = userType;
        this.lastThreeTrips = lastThreeTrips;

    }
    public String getFullName(){
        return fullName;
    }
    public String getEmailAddress(){
        return emailAddress;
    }
    public String getDateOfBirth(){
        return dateOfBirth;
    }
    public long getCardNumber(){
        return cardNumber;
    }
    public String grtCardProvider(){
        return cardProvider;
    }
    public String getCardExpiryDate(){
        return cardExpiryDate;
    }
    public double getFeeToCharge(){
        return feeToCharge;
    }
    public int getCvv(){
        return cvv;
    }
    public String getUserType(){
        return userType;
    }
    public String[] getLastThreeTrips(){
       return lastThreeTrips;
    }
    public void setFullName(String fullName) { this.fullName = fullName; }
public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }
public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }
public void setCardNumber(long cardNumber) { this.cardNumber = cardNumber; }
public void setCardExpiryDate(String cardExpiryDate) { this.cardExpiryDate = cardExpiryDate; }
public void setCardProvider(String cardProvider) { this.cardProvider = cardProvider; }
public void setCvv(int cvv) { this.cvv = cvv; }
public void setUserType(String userType) { this.userType = userType; }
public void setLastThreeTrips(String[] lastThreeTrips) { this.lastThreeTrips = lastThreeTrips; }
    @Override
    public String toString(){
        return "RegisteredUsers{" +
        "fullName = '" + fullName + '\'' +
        ", emailAddress='" + emailAddress + '\'' +
        ", dateOfBirth='" + dateOfBirth + '\'' +
        ", cardNumber='" + cardNumber + '\'' +
         ", cardExpiryDate='" + cardExpiryDate + '\'' +
        ", cardProvider='" + cardProvider + '\'' +
        ", cvv='" + cvv + '\'' +
        ", userType='" + userType + '\'' +
        ", lastThreeTrips=" + Arrays.toString(lastThreeTrips) +
        '}';
    
    }
    public void addTrip(String tripDescription) {
        System.arraycopy(this.lastThreeTrips, 1, this.lastThreeTrips, 0, 2);
        this.lastThreeTrips[2] = tripDescription;
    }
    
}
