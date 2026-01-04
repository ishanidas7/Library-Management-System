import service.BookService;
import service.LoginService;
import java.util.Scanner;
import service.BookService;
import service.StaffService;


public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        LoginService loginService = new LoginService();

        System.out.println("===== Library Management System =====");
        System.out.print("Username: ");
        String username = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();
        if (username.trim().isEmpty() || password.trim().isEmpty()) {
        System.out.println("❌ Username or password cannot be empty.");
        return;   // stop login attempt
}

        if (loginService.login(username, password)) {
            System.out.println("\nLogin Successful!");
            showMenu();
        } else {
            System.out.println("\nInvalid Credentials. Try Again.");
        }
    }

   public static void showMenu() {
    Scanner sc = new Scanner(System.in);
    BookService bookService = new BookService();
    StaffService staffService = new StaffService();


    while (true) {
        System.out.println("\n===== ADMIN MENU =====");
        System.out.println("1. View Books Available");
        System.out.println("2. View Staff Details");
        System.out.println("3. Add Book");
        System.out.println("4. Remove Book");
        System.out.println("5. Add Staff");
        System.out.println("6. Remove Staff");
        System.out.println("7. Exit");
        System.out.println("8. Update Book");
        System.out.println("9. Update Staff");


        System.out.print("Choose an option: ");

          int choice;
        try {
            choice = sc.nextInt();
        } catch (Exception e) {
            System.out.println("❌ Invalid input. Please enter a number.");
            sc.nextLine(); // clear wrong input
            continue;      // go back to menu
        }

        switch (choice) {
            case 1:
                bookService.viewBooks();
                break;

            case 7:
                System.out.println("Exiting system...");
                System.exit(0);

            case 3:
            bookService.addBook();
            break;

            case 4:
            bookService.removeBook();
            break;

            case 2:
            staffService.viewStaff();
            break;

            case 5:
            staffService.addStaff();
            break;

            case 6:
            staffService.removeStaff();
            break;
            case 8:
            bookService.updateBook();
            break;
            case 9:
            staffService.updateStaff();
            break;



            default:
                System.out.println("Option coming soon...");
        }
    }
}
}


