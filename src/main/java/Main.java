import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    private static Queue<String> waitList = new PriorityQueue<>();
    private static Queue<String> tables = new LinkedList<>();
    private final static int MAX_TABLES = 2;

    private static void log(String message) {
        System.out.println(message);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while(true){
            log("1: Check In Someone ");
            log("2: Check Out Someone ");
            log("3: Quit ");
            try{
                int userSelection = scanner.nextInt();
                if(userSelection == 1){
                    checkUserIn(scanner);
                } else if( userSelection == 2){
                    checkUserOut(scanner);
                } else if ( userSelection == 3){
                    return;
                } else {
                    throw new InputMismatchException("Value must be either 1 or 2");
                }

                System.out.println("Tables: " + tables);
                System.out.println("Waitlist: " + waitList);
            } catch(Exception e){
                scanner.nextLine(); // clear the invalid input
                log("Invalid input - " + e.getMessage());
            } 
        }
    }

    private static void checkUserIn(Scanner sc) throws Exception{
        String name;
        while(true){
            log("Name: ");
            try{
                name = sc.next();
                if(name.equals("")){
                    throw new Exception("empty string");
                }
                break;
            } catch (InputMismatchException e){
                log("Input Error");
            } 
        }
        if(tables.size() >= MAX_TABLES){
            log("Tables Full adding to Waitlist");
            waitList.add(name);
            return;
        }
        if(tables.size() < MAX_TABLES){
            tables.add(name);
        }
    }

    private static void checkUserOut(Scanner sc) {
        if(tables.size() >= MAX_TABLES){
            System.out.println("Kicked: " + tables.remove());
        }
        if(tables.size() < MAX_TABLES && waitList.size() > 0){
            tables.add(waitList.remove());
        }
    }
}