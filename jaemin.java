import java.util.Scanner;

class jaemin {
    private static String[] collegestudent = { "202310370311131", "202310370311141", "202310370311151" };
    private static String[] collegestudentpassword = { "leejeno90", "heeseung34", "taehyung67" };
    private static String adminLogin = "admin";
    private static String adminPassword = "najaemin56";
    private static int studentIndex = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("====== Library System ======");
            System.out.println("1. Login College Student");
            System.out.println("2. Login Admin");
            System.out.println("3. Out");
            System.out.print("Your Choice (1-3): ");
            int Choice = scanner.nextInt();

            if (Choice == 1) {
                System.out.print("Enter Your Student ID: ");
                String nim = scanner.next();
                System.out.print("Enter Your Student Password : ");
                String pass = scanner.next();
                if (loginStudent(nim, pass)) {
                    System.out.println("Successfully entered as a college student");
                } else {
                    System.out.println("Data not found");
                }
            } else if (Choice == 2) {
                System.out.print("Enter username (admin): ");
                String username = scanner.next();
                System.out.print("Enter password (admin): ");
                String password = scanner.next();
                if (loginAdmin(username, password)) {
                    System.out.println("Successfully entered as an Admin");
                } else {
                    System.out.println("Data Admin not found");
                }
            } else if (Choice == 3) {
                System.out.println("~ 이 시스템에 접속해주셔서 감사합니다 ㅋㅋㅋㅋㅋ T_T ~");
                break;
            }
        }
    }

    private static boolean loginStudent(String nim, String pass) {
        for (int i = 0; i < collegestudent.length; i++) {
            if (collegestudent[i].equals(nim)) {
                studentIndex = i;
                return true;
            }
        }
        return false;
    }

    private static boolean loginAdmin(String username, String password) {
        return adminLogin.equals(username) && adminPassword.equals(password);
}
}