import java.util.Scanner;

public class AdminPanel {
    private Scanner scanner = new Scanner(System.in);
    private UserService userService = new UserService();
    private BikeService bikeService = new BikeService();
    private RentalService rentalService = new RentalService();
    private UserRegistration reg = new UserRegistration();

    public void showAdminMenu() {
        while (true) {
            System.out.println("\n===== ERyder Admin =====");
            System.out.println("1. Bike Rental Demo");
            System.out.println("2. User Management");
            System.out.println("3. View System Logs");       
            System.out.println("4. Manage Pending Requests");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            try {
                int op = Integer.parseInt(scanner.nextLine());

                switch (op) {
                    case 1:
                        runRentalDemo();
                        break;
                    case 2:
                        userMenu();
                        break;
                    case 3:                      
                        bikeService.viewSystemLogs();
                        break;
                    case 4:
                        managePendingRequestsMenu();
                        break;
                    case 0:
                        System.out.println("Exit.");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }
    }
    private void managePendingRequestsMenu() {
        while (true) {
            System.out.println("\n--- Manage Pending Bike Requests ---");
            System.out.println("1. View Queue");
            System.out.println("2. Update Queue (Remove First)");
            System.out.println("3. Back to Admin Menu");
            System.out.print("Choose: ");

            try {
                int c = Integer.parseInt(scanner.nextLine());
                switch (c) {
                    case 1:
                        bikeService.viewRequestQueue();
                        break;
                    case 2:                  
                        bikeService.updateQueue();
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input!");
            }
        }
    }

    private void runRentalDemo() {
        System.out.print("Registered user? (true/false): ");
        boolean regUser = Boolean.parseBoolean(scanner.nextLine());
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Location: ");
        String loc = scanner.nextLine();

        if (!regUser) {
            System.out.println("Please register.");
            reg.registration();
        } else {
            System.out.println("Welcome back, " + email);
        }
         bikeService.reserveBikeWithUser(email, loc);
        String bikeID = bikeService.validateAndFindBike(loc);
        if (bikeID == null) return;

        bikeService.reserveBike(bikeID);
        rentalService.startRental(bikeID);
        rentalService.showActiveRentals();

        rentalService.endRental(bikeID);
        bikeService.returnBike(bikeID);
        rentalService.showActiveRentals();
    }

    private void userMenu() {
        while (true) {
            System.out.println("\n--- User Menu ---");
            System.out.println("1.Add 2.View 3.Remove 4.Update 5.Back");
            System.out.print("Choose: ");

            try {
                int c = Integer.parseInt(scanner.nextLine());
                switch (c) {
                    case 1:
                        userService.addNewUsers();
                        break;
                    case 2:
                        userService.viewRegisteredUsers();
                        break;
                    case 3:
                        userService.removeRegisteredUsers();
                        break;
                    case 4:
                        userService.updateRegisteredUsers();
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Invalid.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input!");
            }
        }
    }

    public static void main(String[] args) {
        new AdminPanel().showAdminMenu();
    }
}