package display;

import java.text.ParseException;
import java.util.Scanner;

import business_layer.ActivityManager;
import business_layer.AssociateManager;
import business_layer.CampManager;
import business_layer.MonitorManager;
import business_layer.ParticipantManager;
import business_layer.RegistrationManager;

public class Application {
    static ParticipantManager participantManager = new business_layer.ParticipantManager();
    static CampManager campManager = new business_layer.CampManager();
    static RegistrationManager registrationManager = new business_layer.RegistrationManager();
    static ActivityManager activityManager = new business_layer.ActivityManager();
    static MonitorManager monitorManager = new business_layer.MonitorManager();
    static AssociateManager associateManager = new business_layer.AssociateManager();

    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Register a participant");
            System.out.println("2. Create a camp");
            System.out.println("3. Create an activity");
            System.out.println("4. Create a monitor");
            System.out.println("5. List participants");
            System.out.println("6. List camps");
            System.out.println("7. List activities");
            System.out.println("8. Register for a camp");
            System.out.println("9. Check camp availability");
            System.out.println("10. Assign a monitor to the camp");
            System.out.println("11. Assign an activity to the camp");
            System.out.println("12. Assign a special monitor to the camp");
            System.out.println("13. Exit the application");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
            case 1:
                participantManager.registerParticipant(scanner);
                break;
            case 2:
                campManager.createCamp(scanner);
                break;
            case 3:
                activityManager.createActivity(scanner);
                break;
            case 4:
                monitorManager.createMonitor(scanner);
                break;
            case 5:
                participantManager.listParticipants();
                break;
            case 6:
                campManager.displayAllCamps();
                break;
            case 7:
                activityManager.listActivities();
                break;
            case 8:
                RegistrationManager.allowEarlyAndLateRegistrations(registrationManager, scanner);
                break;
            case 9:
                campManager.checkCampAvailability(campManager);
                break;
            case 10:
                associateManager.associateMonitorToCamp(scanner);
                break;
            case 11:
                associateManager.associateActivityToCamp(scanner);
                break;
            case 12:
                associateManager.associateSpecialAttentionMonitorsToCamps(scanner);
                break;
            case 13:
                System.out.println("Exiting the application.");
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
