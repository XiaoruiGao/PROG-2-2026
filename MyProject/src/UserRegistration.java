import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class UserRegistration {
    private static final double VIP_DISCOUNT_UNDER_18_BIRTHDAY = 25.0;
    private static final double VIP_DISCOUNT_UNDER_18 = 20.0;
    private static final double VIP_BASE_FEE = 100.0;
    private String fullName;
    private String emailAddress;
    private String dateOfBirth;
    private long cardNumber;
    private String cardProvider;
    private String cardExpiryDate;
    private double feeToCharge;
    private int cvv;
    private String userType;
    private boolean emailValid;
    private boolean minor;
    private boolean minorAndBirthday;
    private boolean ageValid;
    private boolean cardNumberValid;
    private boolean cardStillValid;
    private boolean validCVV;
    public void registration(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to ERyder Registration");
        System.out.println("Here are your two options:");
        System.out.println("1. Register as a Regular User");
        System.out.println("2. Register as a VIP User");
        System.out.println("Please enter your choice (1 or 2): ");
        int choice = sc.nextInt();
        sc.nextLine();
        if(choice == 1){
            userType = "Regular User";
        }else if(choice == 2){
            userType = "VIP User";
        }else{
            System.out.println("Invalid choice. Please restart the regisitration process.");
            return;
        }
        System.out.println("Let's proceed with the registration...\n");
        System.out.println("Please enter your Full Name: ");
        fullName = sc.nextLine();
        System.out.println("Please enter your Email Address: ");
        emailAddress = sc.nextLine();
        System.out.println("Checking your email address's validity...");
        emailValid = analyseEmail(emailAddress);

        System.out.println("Please enter your date of birth as YYY-MM-DD: ");
        dateOfBirth = sc.nextLine();
        System.out.println("Checing your age validity...");
        LocalDate dob = LocalDate.parse(dateOfBirth);
        ageValid = analyseAge(dob);
        System.out.println("Please enter your card number: ");
        cardNumber = sc.nextLong();
        sc.nextLine();
        System.out.println("Checking your card number's validity...");
        cardNumberValid = analyseCardNumber(cardNumber);
        System.out.println("Please enter your card expiry date (MM/YY): ");
        cardExpiryDate = sc.nextLine();
        System.out.println("Checking if your card is still valid...");
        cardStillValid = analyseCardExpiryDate(cardExpiryDate);
        System.out.println("Please enter your CVV: ");
        cvv = sc.nextInt();
        validCVV = analyseCVV(cvv);
        finalCheckpoint();
        sc.close();
    }
    private boolean analyseEmail(String email){
        if(email.contains("@") && email.contains(".")){
            System.out.println("Email is valid.");
            return true;
        }else{
            System.out.println("Invalid email address. Going back to the start of the registration");
            return false;
        }
    }
        private boolean analyseAge(LocalDate dob){
            LocalDate today = LocalDate.now();
            Period agePeriod = Period.between(dob, today);
            int age = agePeriod.getYears();
            minor= false;
            minorAndBirthday = false;
            boolean isBirthday = false;
            if(dob.getMonthValue() == today.getMonthValue() && dob.getDayOfMonth() == today.getDayOfMonth()){
               isBirthday = true; 
            }
            if ("VIP User".equals(userType)) {
           
            if (isBirthday && age > 12 && age <= 18) {
                System.out.println("Happy Birthday!");
                System.out.println("You get " + VIP_DISCOUNT_UNDER_18_BIRTHDAY + "% discount on the VIP subscription fee for being born today and being under 18!");
                minorAndBirthday = true;
              
                feeToCharge = VIP_BASE_FEE * (100 - VIP_DISCOUNT_UNDER_18_BIRTHDAY) / 100;
            }
         
            else if (!isBirthday && age > 12 && age <= 18) {
                System.out.println("You get " + VIP_DISCOUNT_UNDER_18 + "% discount on the VIP subscription fee for being under 18!");
                minor = true;
              
                feeToCharge = VIP_BASE_FEE * (100 - VIP_DISCOUNT_UNDER_18) / 100;
            }
        }

        
        if (age <= 12 || age >= 120) {
            System.out.println("Looks like you are either too young or already dead. Sorry, you can't be our user. Have a nice day");
            System.exit(0); 
        }
   
        ageValid = !dob.isAfter(today);
        return ageValid;


        }

    
    

private boolean analyseCardNumber(long cardNum) {
        String cardStr = String.valueOf(cardNum);
       
        boolean isVisa = cardStr.matches("^4[0-9]{12}(?:[0-9]{3})?$");
        boolean isMasterCard = cardStr.matches("^5[1-5][0-9]{14}$");
        boolean isAmex = cardStr.matches("^3[47][0-9]{13}$");

        cardNumberValid = isVisa || isMasterCard || isAmex;
        if (cardNumberValid) {
            
            if (isVisa) cardProvider = "Visa";
            else if (isMasterCard) cardProvider = "MasterCard";
            else if (isAmex) cardProvider = "American Express";
            System.out.println("Card number is valid. Card Provider: " + cardProvider);
        } else {
            System.out.println("Invalid card number! Only Visa/MasterCard/American Express are accepted.");
        }
        return cardNumberValid;
    }

   
    private boolean analyseCardExpiryDate(String expiry) {
        try {
            // 解析MM/YY格式
            String[] parts = expiry.split("/");
            int month = Integer.parseInt(parts[0]);
            int year = Integer.parseInt(parts[1]) + 2000; 

            
            if (month < 1 || month > 12) {
                cardStillValid = false;
                System.out.println("Invalid expiry date! Month must be 01-12.");
                return false;
            }

            
            LocalDate expiryDate = LocalDate.of(year, month, 1).plusMonths(1).minusDays(1);
            LocalDate today = LocalDate.now();

            
            cardStillValid = expiryDate.isAfter(today);
            if (cardStillValid) {
                System.out.println("Card is still valid. Expiry date: " + expiryDate);
            } else {
                System.out.println("Card has expired!");
            }
        } catch (Exception e) {
            cardStillValid = false;
            System.out.println("Invalid expiry date format! Please enter as MM/YY.");
        }
        return cardStillValid;
    }

    
    private boolean analyseCVV(int cvvNum) {
        String cvvStr = String.valueOf(cvvNum);
        
        validCVV = (cvvStr.length() == 3 && "Visa,MasterCard".contains(cardProvider))
                || (cvvStr.length() == 4 && "American Express".equals(cardProvider));
        if (validCVV) {
            System.out.println("CVV is valid.");
        } else {
            System.out.println("Invalid CVV! " + cardProvider + " requires " + (cardProvider.equals("American Express") ? 4 : 3) + " digits.");
        }
        return validCVV;
    }

    
    private void finalCheckpoint() {
        System.out.println("\n===== Final Registration Checkpoint =====");
        System.out.println("User Type: " + userType);
        System.out.println("Full Name: " + fullName);
        System.out.println("Email Valid: " + emailValid);
        System.out.println("Age Valid: " + ageValid);
        System.out.println("Minor: " + minor + " | Minor and Birthday: " + minorAndBirthday);
        System.out.println("Card Number Valid: " + cardNumberValid);
        System.out.println("Card Still Valid: " + cardStillValid);
        System.out.println("CVV Valid: " + validCVV);
        
        if ("VIP User".equals(userType) && (minor || minorAndBirthday)) {
            System.out.printf("VIP Subscription Fee (After Discount): $%.2f%n", feeToCharge);
        } else if ("VIP User".equals(userType)) {
            System.out.printf("VIP Subscription Fee (Original): $%.2f%n", VIP_BASE_FEE);
        }
        
        if (emailValid && ageValid && cardNumberValid && cardStillValid && validCVV) {
            System.out.println("\ntrue Registration Successful! Welcome to ERyder!");
        } else {
            System.out.println("\nfalse Registration Failed! Please check your information and try again.");
        }
    }

    public static void main(String[] args) {
        UserRegistration registration = new UserRegistration();
        registration.registration();

}
}