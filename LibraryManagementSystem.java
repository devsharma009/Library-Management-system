import java.util.*;

class Book {
    int id;
    String title;
    String author;
    boolean isAvailable;
    String issuedTo;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
        this.issuedTo = "";
    }
}

public class LibraryManagementSystem {
    static List<Book> library = new ArrayList<>();
    static Queue<Map.Entry<Integer, String>> issuerQueue = new LinkedList<>();

    public static void addBook(Scanner scanner) {
        System.out.print("Enter book ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();
        library.add(new Book(id, title, author));
        library.sort(Comparator.comparingInt(b -> b.id));
        System.out.println("Book added successfully!");
    }

    public static void searchBook(Scanner scanner) {
        System.out.println("Search by:\n1. ID\n2. Title");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 1) {
            System.out.print("Enter book ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            for (Book book : library) {
                if (book.id == id) {
                    displayBook(book);
                    return;
                }
            }
        } else if (choice == 2) {
            System.out.print("Enter book title: ");
            String title = scanner.nextLine();
            for (Book book : library) {
                if (book.title.equalsIgnoreCase(title)) {
                    displayBook(book);
                    return;
                }
            }
        } else {
            System.out.println("Invalid choice!");
            return;
        }
        System.out.println("Book not found!");
    }

    public static void issueBook(Scanner scanner) {
        System.out.print("Enter book ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        for (Book book : library) {
            if (book.id == id && book.isAvailable) {
                System.out.print("Enter student name: ");
                String name = scanner.nextLine();
                book.isAvailable = false;
                book.issuedTo = name;
                issuerQueue.add(Map.entry(id, name));
                System.out.println("Book issued successfully!");
                return;
            }
        }
        System.out.println("Book not found or already issued!");
    }

    public static void returnBook(Scanner scanner) {
        System.out.print("Enter book ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        for (Book book : library) {
            if (book.id == id && !book.isAvailable) {
                book.isAvailable = true;
                book.issuedTo = "";
                System.out.println("Book returned successfully!");
                return;
            }
        }
        System.out.println("Book not found or not issued!");
    }

    public static void listBooks() {
        System.out.println("List of all books:");
        for (Book book : library) {
            displayBook(book);
        }
    }

    public static void deleteBook(Scanner scanner) {
        System.out.print("Enter book ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Iterator<Book> iterator = library.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.id == id) {
                iterator.remove();
                System.out.println("Book deleted successfully!");
                return;
            }
        }
        System.out.println("Book not found!");
    }

    public static void displayBook(Book book) {
        System.out.println("ID: " + book.id);
        System.out.println("Title: " + book.title);
        System.out.println("Author: " + book.author);
        System.out.println("Availability: " + (book.isAvailable ? "Available" : "Issued"));
        if (!book.isAvailable) {
            System.out.println("Issued to: " + book.issuedTo);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Library Management System");
            System.out.println("1. Add a book");
            System.out.println("2. Search for a book");
            System.out.println("3. Issue a book");
            System.out.println("4. Return a book");
            System.out.println("5. List all books");
            System.out.println("6. Delete a book");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    addBook(scanner);
                    break;
                case 2:
                    searchBook(scanner);
                    break;
                case 3:
                    issueBook(scanner);
                    break;
                case 4:
                    returnBook(scanner);
                    break;
                case 5:
                    listBooks();
                    break;
                case 6:
                    deleteBook(scanner);
                    break;
                case 7:
                    System.out.println("Thanks for using the Library Management System!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
            System.out.println();
        }
    }
}
