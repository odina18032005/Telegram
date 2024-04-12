package backend.forScanner;

import java.util.Scanner;

public interface Scan {
    static Integer scanInt(String hint){
        Scanner scanner = new Scanner(System.in);
        System.out.print(hint + ": ");
        return scanner.nextInt();
    }
    static String scanStr(String hint){
        Scanner scanner = new Scanner(System.in);
        System.out.print(hint + ": ");
        return scanner.nextLine();
    }
}
