import controllers.BookController;
import controllers.UserController;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MyApplication {
    private final BookController controller;
    private final UserController u_controller;
    private final Scanner scanner;

    public MyApplication(BookController controller, UserController uController) {
        this.controller = controller;
        this.u_controller = uController;
        scanner = new Scanner(System.in);
    }


    public void start() {
        while (true) {
            System.out.println();
            System.out.println("Welcome to out library");
            System.out.println("Select option:");
            System.out.println("1. Register");
            System.out.println("2. Log In");
            System.out.println("0. Exit");
            System.out.println();
            try {
                System.out.print("Enter option: ");
                int option = scanner.nextInt();
                if (option == 1) {
                    Register();
                } else if (option == 2) {
                    logIn();
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input must be integer: " + e);
                scanner.nextLine();
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }

            System.out.println("*************************");
        }
    }

    public void getAllBooksMenu() {
        String response = controller.getAllBooks();
        System.out.println(response);
    }
    public void getAllUsers(){
        String u_response = u_controller.getAllUsers();
        System.out.println(u_response);
    }

    public void getBookByIdMenu() {
        System.out.println("Please enter id of the book");

        int id = scanner.nextInt();
        String response = controller.getBook(id);
        System.out.println(response);
    }

    public void createBookMenu() {
        scanner.useDelimiter("\\n");
        System.out.println("Please enter bookname");
        String bookname = scanner.next();
        System.out.println("Please enter author");
        String author = scanner.next();
        System.out.println("Please enter year");
        int year = Integer.parseInt(scanner.next());
        System.out.println("Please enter price");
        int price = Integer.parseInt(scanner.next());


        String response = controller.createBook(bookname, author, year, price);
        System.out.println(response);
    }
    public void removeBook(){
        System.out.println("Enter id of the book");
        int bookId = scanner.nextInt();
        String response = controller.removeBook(bookId);
        System.out.println(response);
    }
    public void Register(){
        System.out.println("Please enter username: ");
        String username = scanner.next();
        System.out.println("Please enter phone number: ");
        String phone = scanner.next();
        System.out.println("Please enter email: ");
        String email = scanner.next();
        System.out.println("Please enter password: ");
        String password = scanner.next();
        String role = "user";
        String reg_response = u_controller.Register(username,phone,email,password,role);
        System.out.println(reg_response);
    }
    public void logIn(){
        System.out.println("Please enter username: ");
        String username = scanner.next();
        System.out.println("Please enter password: ");
        String password = scanner.next();
        String role = u_controller.getUserRole(username);
        if (role.equals("admin")){
            AdminMenu();
        }
        else if (role.equals("user")){
            UserMenu();
        }
    }

    public void AdminMenu(){
        while(true){
            System.out.println();
            System.out.println("Welcome, Admin");
            System.out.println("Select option: ");
            System.out.println("1.View all books");
            System.out.println("2.Add a book");
            System.out.println("3.View all users");
            System.out.println("4.Remove a book");
            System.out.println("5.Alter a book");
            System.out.println("0.Exit");
            try {
                System.out.print("Enter option: ");
                int option = scanner.nextInt();
                switch (option) {
                    case 1:
                        getAllBooksMenu();
                        break;
                    case 2:
                        createBookMenu();
                        break;
                    case 3:
                        getAllUsers();
                        break;
                    case 4:
                        removeBook();
                        break;
                    case 5:
                        alterBook();
                        break;
                    case 0:
                        System.out.println("Logging out...");
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input must be an integer.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public void UserMenu(){
        while(true){
            System.out.println();
            System.out.println("Welcome, User!");
            System.out.println("Select option:");
            System.out.println("1. View all books");
            System.out.println("2. Borrow a book");
            System.out.println("3. Return a book");
            System.out.println("4. View my history/View my borrowed books");
            System.out.println("0. Log Out");
            try {
                System.out.print("Enter option: ");
                int option = scanner.nextInt();
                switch (option) {
                    case 1:
                        getAllBooksMenu();
                        break;
                    case 2:
                        borrowBook();
                        break;
                    case 3:
                        returnBook();
                        break;
                    case 4:
                        // Implement viewing user's history
                        break;
                    case 0:
                        System.out.println("Logging out...");
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input must be an integer.");
                scanner.nextLine(); // Clear input buffer
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    public void borrowBook(){
        System.out.println("Please enter the id of the book which you want to borrow: ");
        int bookId = scanner.nextInt();
        System.out.println("Please enter username: ");
        String username = scanner.next();
        int userId = u_controller.getUserId(username);
        String ura = controller.borrowBook(bookId,userId);
        System.out.println(ura);
   }
    public void returnBook(){
        System.out.println("Please enter book id: ");
        int bookId = scanner.nextInt();
        String response = controller.returnBook(bookId);
        System.out.println(response);
    }
    public void alterBook(){
        scanner.useDelimiter("\\n");
        System.out.println("Please enter book id: ");
        int bookId = scanner.nextInt();
        System.out.println("Enter the name of book: ");
        String bookname = scanner.next();
        System.out.println("Enter the author of book: ");
        String author = scanner.next();
        System.out.println("Please enter price: ");
        int price = scanner.nextInt();
        System.out.println("Please enter year: ");
        int year = scanner.nextInt();
        String response = controller.alterBook(bookId,bookname,author,price,year);
        System.out.println(response);
    }
}
