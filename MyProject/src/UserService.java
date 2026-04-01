import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class UserService {
    private List<RegisteredUsers> registeredUsersList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void addNewUsers() {
        System.out.print("\nHow many users would you like to add? ");
        int numUsers;
        try {
            numUsers = Integer.parseInt(scanner.nextLine());
            if (numUsers <= 0) {
                System.out.println("Please enter a positive number!");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number! Please try again.");
            return;
        }

        for (int i = 0; i < numUsers; i++) {
            System.out.println("\n--- Enter details for User " + (i + 1) + " ---");
            System.out.print("Full Name: ");
            String fullName = scanner.nextLine();

            System.out.print("Email Address: ");
            String emailAddress = scanner.nextLine();

            System.out.print("Date of Birth (YYYY-MM-DD): ");
            String dateOfBirth = scanner.nextLine();

            System.out.print("Card Number: ");
            String cardNumber = scanner.nextLine();

            System.out.print("Card Expiry Date (MM/YY): ");
            String cardExpiryDate = scanner.nextLine();

            System.out.print("Card Provider: ");
            String cardProvider = scanner.nextLine();

            int cvv = 0;
            while (true) {
                System.out.print("CVV: ");
                String cvvStr = scanner.nextLine();
                try {
                    cvv = Integer.parseInt(cvvStr);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid CVV! Please enter a number.");
                }
            }

            System.out.print("User Type: ");
            String userType = scanner.nextLine();
            String[] lastThreeTrips = new String[3];
            for (int j = 0; j < 3; j++) {
                System.out.println("\n--- Enter Trip " + (j + 1) + " Details ---");
                System.out.print("Trip Date (YYYY-MM-DD): ");
                String tripDate = scanner.nextLine();
                System.out.print("Source: ");
                String source = scanner.nextLine();
                System.out.print("Destination: ");
                String dest = scanner.nextLine();
                System.out.print("Fare (€): ");
                String fare = scanner.nextLine();
                System.out.print("Feedback (press ENTER for NULL): ");
                String feedback = scanner.nextLine();
                if (feedback.isEmpty()) feedback = "NULL";

                StringBuilder tripSb = new StringBuilder();
                tripSb.append("Date: ").append(tripDate)
                      .append(", Source: ").append(source)
                      .append(", Destination: ").append(dest)
                      .append(", Fare (€): ").append(fare)
                      .append(", Feedback: ").append(feedback);
                lastThreeTrips[j] = tripSb.toString();
            }
            RegisteredUsers newUser = new RegisteredUsers(fullName, emailAddress, dateOfBirth,
                    cardNumber, cardExpiryDate, cardProvider, cvv, userType, lastThreeTrips);
            registeredUsersList.add(newUser);
            System.out.println("User " + (i + 1) + " added successfully!");
        }
    }

    public void viewRegisteredUsers() {
        if (registeredUsersList.isEmpty()) {
            System.out.println("No registered users to display");
            return;
        }
        System.out.println("\n--- All Registered Users ---");
        for (RegisteredUsers user : registeredUsersList) {
            System.out.println(user);
        }
    }

    public void removeRegisteredUsers() {
        if (registeredUsersList.isEmpty()) {
            System.out.println("No registered users to remove");
            return;
        }
        System.out.print("\nEnter the email address of the user to remove: ");
        String targetEmail = scanner.nextLine();
        boolean found = false;

        Iterator<RegisteredUsers> iterator = registeredUsersList.iterator();
        while (iterator.hasNext()) {
            RegisteredUsers user = iterator.next();
            if (user.getEmailAddress().equals(targetEmail)) {
                iterator.remove();
                found = true;
                System.out.println("User with email " + targetEmail + " removed successfully!");
                break;
            }
        }

        if (!found) {
            System.out.println("No user found with this email address");
        }
    }

    public void updateRegisteredUsers() {
        if (registeredUsersList.isEmpty()) {
            System.out.println("No registered users to update");
            return;
        }
        System.out.print("\nEnter the email address of the user to update: ");
        String targetEmail = scanner.nextLine();
        RegisteredUsers targetUser = null;

        for (RegisteredUsers user : registeredUsersList) {
            if (user.getEmailAddress().equals(targetEmail)) {
                targetUser = user;
                break;
            }
        }

        if (targetUser == null) {
            System.out.println("No user found with this email address");
            return;
        }
        System.out.println("\n--- Update User Details (Press ENTER for no change) ---");
        System.out.print("New Full Name (Current: " + targetUser.getFullName() + "): ");
        String newFullName = scanner.nextLine();
        if (!newFullName.isEmpty()) targetUser.setFullName(newFullName);

        System.out.print("New Date of Birth (Current: " + targetUser.getDateOfBirth() + "): ");
        String newDateOfBirth = scanner.nextLine();
        if (!newDateOfBirth.isEmpty()) targetUser.setDateOfBirth(newDateOfBirth);

        System.out.print("New Card Provider (Current: " + targetUser.getCardProvider() + "): ");
        String newCardProvider = scanner.nextLine();
        if (!newCardProvider.isEmpty()) targetUser.setCardProvider(newCardProvider);

        System.out.print("New User Type (Current: " + targetUser.getUserType() + "): ");
        String newUserType = scanner.nextLine();
        if (!newUserType.isEmpty()) targetUser.setUserType(newUserType);

        System.out.println("\n--- Update Numeric Details (Enter original value for no change) ---");
        System.out.print("New Card Number (Current: " + targetUser.getCardNumber() + "): ");
                String newCardNumberStr = scanner.nextLine();
        if (!newCardNumberStr.isEmpty()) {
            try {
                long newCardNumber = Long.parseLong(newCardNumberStr);
                targetUser.setCardNumber(newCardNumber);
                System.out.println("Card number updated successfully.");
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid card number, please enter a valid number.");
            }
        }

        System.out.print("New Card Expiry Date (Current: " + targetUser.getCardExpiryDate() + "): ");
        String newCardExpiryDate = scanner.nextLine();
        if (!newCardExpiryDate.isEmpty()) targetUser.setCardExpiryDate(newCardExpiryDate);

        System.out.print("New CVV (Current: " + targetUser.getCvv() + "): ");
        String newCvvStr = scanner.nextLine();
        if (!newCvvStr.isEmpty()) {
            try {
                int newCvv = Integer.parseInt(newCvvStr);
                targetUser.setCvv(newCvv);
            } catch (NumberFormatException e) {
                System.out.println("Invalid CVV! Kept the original value.");
            }
        }

        System.out.println("User with email " + targetEmail + " updated successfully!");
    }
}