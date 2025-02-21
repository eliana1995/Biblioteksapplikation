import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final BookDAO bookDAO = new BookDAO();
    private static final LoanDAO loanDAO = new LoanDAO();

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n-- Main Menu --");
            System.out.println("1. List all books");
            System.out.println("2. Borrow a book");
            System.out.println("3. Return a book");
            System.out.println("4. List my current loans");
            System.out.println("5. Add a new book (Admin)");
            System.out.println("6. Delete a book (Admin)");
            System.out.println("7. Exit");
            System.out.print("Select an option: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    listAllBooks();
                    break;
                case 2:
                    borrowBook();
                    break;
                case 3:
                    returnBook();
                    break;
                case 4:
                    listCurrentLoans();
                    break;
                case 5:
                    addNewBook();
                    break;
                case 6:
                    deleteBook();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void listAllBooks() {
        List<Book> books = bookDAO.getAllBooks();
        for (Book book : books) {
            System.out.println(book);
        }
    }

    private static void borrowBook() {
        System.out.print("Enter book ID to borrow: ");
        int bookId = scanner.nextInt();
        // Assuming userId is 1 for now, replace with actual user input
        int userId = 1;


        List<Book> books = bookDAO.getAllBooks();
        for (Book book : books) {
            if (book.getId() == bookId && book.isAvailable()) {
                loanDAO.borrowBook(userId, bookId);
                bookDAO.updateBookAvailability(bookId, false);  // Mark the book as borrowed
                System.out.println("Book borrowed successfully!");
                return;
            }
        }
        System.out.println("Book not available or doesn't exist.");
    }

    private static void returnBook() {
        System.out.print("Enter loan ID to return: ");
        int loanId = scanner.nextInt();

        loanDAO.returnBook(loanId);
        System.out.println("Book returned successfully!");
    }

    private static void listCurrentLoans() {
        System.out.print("Enter your user ID: ");
        int userId = scanner.nextInt();

        List<Loan> loans = loanDAO.getLoansByUser(userId);
        for (Loan loan : loans) {
            System.out.println(loan);
        }
    }

    private static void addNewBook() {
        System.out.print("Enter book title: ");
        String title = scanner.next();
        System.out.print("Enter book author: ");
        String author = scanner.next();

        Book newBook = new Book(0, title, author, true);  // New book will be available by default
        bookDAO.addBook(newBook);
        System.out.println("New book added successfully!");
    }

    private static void deleteBook() {
        System.out.print("Enter book ID to delete: ");
        int bookId = scanner.nextInt();

        bookDAO.deleteBook(bookId);
        System.out.println("Book deleted successfully!");
    }
}
