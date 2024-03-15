import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Student> students = new ArrayList<>();
    private static ArrayList<Book> books = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        books.add(new Book("388c-e681-9152", "Dilan 1990", "PidiBaiq", "Drama", 3));
        books.add(new Book("ed90-be30-5cdb", "JavaScript", "Budi", "Tech", 2));
        books.add(new Book("d95e-0c4a-9523", "Naruto", "Kishimoto", "Comic", 4));

        while (true) {
            System.out.println("===== Library System =====");
            System.out.println("1. Login as Student");
            System.out.println("2. Login as Admin");
            System.out.println("3. Exit");
            System.out.print("Your Choice (1-3): ");
            int userInput = scanner.nextInt();

            switch (userInput) {
                case 1:
                    loginAsStudent();
                    break;
                case 2:
                    loginAsAdmin();
                    break;
                case 3:
                    System.out.println("~ Thank You ~.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid.");
            }
        }
    }

    public static void loginAsStudent() {
        System.out.println("===== Student Menu ====");
        System.out.print("Enter Your NIM (99 for back): ");
        String nim = scanner.next();

        if (nim.equals("99")) {
            System.out.println("Back to Menu...");
            return;
        }

        Student student = findStudentByNim(nim);

        if (student == null) {
            System.out.println("Student Not Found");
            return;
        }

        while (true) {
            System.out.println("\n===== Student Menu ====");
            System.out.println("1. Buku Terpinjam");
            System.out.println("2. Pinjam Buku");
            System.out.println("3. Keluar");
            System.out.print("Choose Option (1-3): ");
            int userInput = scanner.nextInt();

            switch (userInput) {
                case 1:
                    student.viewBorrowedBooks();
                    break;
                case 2:
                    student.borrowBook(books, scanner);
                    break;
                case 3:
                    System.out.println("Keluar dari akun mahasiswa...");
                    return;
                default:
                    System.out.println("Input tidak valid. Silakan coba lagi.");
            }
        }
    }

    public static Student findStudentByNim(String nim) {
        for (Student s : students) {
            if (s.getNim().equals(nim)) {
                return s;
            }
        }
        return null;
    }

    public static void loginAsAdmin() {
        System.out.println("===== Admin Menu =====");
        System.out.println("1. Tambah Mahasiswa");
        System.out.println("2. Tampilkan Mahasiswa Terdaftar");
        System.out.println("3. Keluar");
        System.out.print("Pilih opsi (1-3): ");
        int userInput = scanner.nextInt();

        switch (userInput) {
            case 1:
                addStudent();
                break;
            case 2:
                displayRegisteredStudents();
                break;
            case 3:
                System.out.println("Keluar dari akun admin...");
                break;
            default:
                System.out.println("Input tidak valid. Silakan coba lagi.");
        }
    }

    public static void addStudent() {
        System.out.print("Masukkan nama mahasiswa: ");
        String name = scanner.next();
        System.out.print("Masukkan fakultas mahasiswa: ");
        String faculty = scanner.next();
        System.out.print("Masukkan NIM mahasiswa: ");
        String nim = scanner.next();
        System.out.print("Masukkan program studi mahasiswa: ");
        String program = scanner.next();

        students.add(new Student(name, faculty, nim, program));
        System.out.println("Mahasiswa berhasil ditambahkan!");
    }

    public static void displayRegisteredStudents() {
        System.out.println("\n===== Mahasiswa Terdaftar =====");
        System.out.printf("%-20s %-20s %-15s %-20s\n", "Nama", "Fakultas", "NIM", "Program Studi");
        for (Student student : students) {
            System.out.printf("%-20s %-20s %-15s %-20s\n", student.getName(), student.getFaculty(), student.getNim(), student.getProgram());
        }
    }
}

class Student {
    private String name;
    private String faculty;
    private String nim;
    private String program;
    private ArrayList<Book> borrowedBooks = new ArrayList<>();

    public Student(String name, String faculty, String nim, String program) {
        this.name = name;
        this.faculty = faculty;
        this.nim = nim;
        this.program = program;
    }

    public String getName() {
        return name;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getNim() {
        return nim;
    }

    public String getProgram() {
        return program;
    }

    public void viewBorrowedBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("Anda belum meminjam buku apapun.");
            return;
        }
        System.out.println("\n===== Buku Terpinjam =====");
        System.out.printf("%-10s %-20s %-20s %-10s\n", "ID", "Judul", "Penulis", "Stok");
        for (Book book : borrowedBooks) {
            System.out.printf("%-10s %-20s %-20s %-10d\n", book.getId(), book.getTitle(), book.getAuthor(), book.getStock());
        }
    }

    public void borrowBook(ArrayList<Book> books, Scanner scanner) {
        System.out.print("Masukkan ID buku yang ingin dipinjam: ");
        String id = scanner.next();

        if (id.isEmpty()) {
            System.out.println("ID buku tidak boleh kosong. Kembali ke menu mahasiswa...");
            return;
        }

        Book book = null;
        for (Book b : books) {
            if (b.getId().equals(id)) {
                book = b;
                break;
            }
        }

        if (book == null) {
            System.out.println("Buku tidak ditemukan. Kembali ke menu mahasiswa...");
            return;
        }

        if (book.getStock() > 0) {
            borrowedBooks.add(book);
            book.setStock(book.getStock() - 1);
            System.out.println("Buku berhasil dipinjam!");
        } else {
            System.out.println("Maaf, stok buku habis.");
        }
    }
}

class Admin {
    public static void loginAsAdmin(ArrayList<Student> students) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("===== Admin Menu =====");
        System.out.println("1. Tambah Mahasiswa");
        System.out.println("2. Tampilkan Mahasiswa Terdaftar");
        System.out.println("3. Keluar");
        System.out.print("Pilih opsi (1-3): ");
        int userInput = scanner.nextInt();

        switch (userInput) {
            case 1:
                addStudent(students);
                break;
            case 2:
                displayRegisteredStudents(students);
                break;
            case 3:
                System.out.println("Keluar dari akun admin...");
                break;
            default:
                System.out.println("Input tidak valid. Silakan coba lagi.");
        }

        scanner.close();
    }

    public static void addStudent(ArrayList<Student> students) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Masukkan nama mahasiswa: ");
            String name = scanner.nextLine();
            System.out.print("Masukkan fakultas mahasiswa: ");
            String faculty = scanner.nextLine();
            System.out.print("Masukkan NIM mahasiswa: ");
            String nim = scanner.nextLine();
            System.out.print("Masukkan program studi mahasiswa: ");
            String program = scanner.nextLine();

            students.add(new Student(name, faculty, nim, program));
        }
        System.out.println("Mahasiswa berhasil ditambahkan!");
    }

    public static void displayRegisteredStudents(ArrayList<Student> students) {
        System.out.println("\n===== Mahasiswa Terdaftar =====");
        System.out.printf("%-20s %-20s %-15s %-20s\n", "Nama", "Fakultas", "NIM", "Program Studi");
        for (Student student : students) {
            System.out.printf("%-20s %-20s %-15s %-20s\n", student.getName(), student.getFaculty(), student.getNim(), student.getProgram());
        }
    }
}

class Book {
    private String id;
    private String title;
    private String author;
    private String category;
    private int stock;

    public Book(String id, String title, String author, String category, int stock) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.stock = stock;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock =stock;
    }
}