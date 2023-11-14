package business_layer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import dao.CampDao;
import enums.RegistrationType;

public class RegistrationManager {
    private List<RegistrationDto> registrations;

    private CampDao campDao;

    public RegistrationManager() {
        registrations = new ArrayList<>();
        campDao = new CampDao();
    }

    public static void allowEarlyAndLateRegistrations(RegistrationManager registrationManager, Scanner scanner) {
        System.out.println("Select an option:");
        System.out.println("1. Register for early camp");
        System.out.println("2. Register for late camp");
        System.out.println("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        switch (choice) {
        case 1:
            RegistrationManager.registerForEarlyCamp(registrationManager, scanner);
            break;
        case 2:
            RegistrationManager.registerForLateCamp(registrationManager, scanner);
            break;
        default:
            System.out.println("Invalid choice. Please try again.");
        }
    }

    public boolean registerEarly(int participantId, int campId, Date registrationDate, String regType) {
        for (RegistrationDto registration : registrations) {
            if (registration.getParticipantId() == participantId && registration.getCampId() == campId) {
                System.out.println("Participant is already registered for this camp.");
                return false;
            }
        }

        for (RegistrationDto registration : registrations) {
            if (registration.getParticipantId() == participantId && registration.getCampId() == campId
                    && registration.getRegistrationType() == RegistrationType.LATE) {
                System.out.println("Participant has a late registration for this camp. Cancel it first.");
                return false;
            }
        }

        float price = calculatePrice(campId, RegistrationType.EARLY);
        if (regType.equals("Full") || regType.equals("FULL")) {
            FullRegistration fullRegistration = FullRegistration.createEarlyRegistration(participantId, campId, registrationDate, price,
                    RegistrationType.EARLY);
            registrations.add(fullRegistration);
        } else {
            PartialRegistration partialRegistration = PartialRegistration.createEarlyRegistration(participantId, campId, registrationDate,
                    price, RegistrationType.EARLY);
            registrations.add(partialRegistration);
        }

        System.out.println("Early registration successful.");
        return true;
    }

    public boolean registerLate(int participantId, int campId, Date registrationDate, String regType) {

        float price = calculatePrice(campId, RegistrationType.LATE);

        if (regType.equals("Full") || regType.equals("FULL")) {
            FullRegistration fullRegistration = FullRegistration.createEarlyRegistration(participantId, campId, registrationDate, price,
                    RegistrationType.EARLY);
            registrations.add(fullRegistration);
        } else {
            PartialRegistration partialRegistration = PartialRegistration.createEarlyRegistration(participantId, campId, registrationDate,
                    price, RegistrationType.EARLY);
            registrations.add(partialRegistration);
        }

        System.out.println("Late registration successful.");
        return true;
    }

    public static void registerForEarlyCamp(RegistrationManager registrationManager, Scanner scanner) {
        System.out.println("Registering for early camp.");
        System.out.print("Enter participant ID: ");
        int participantId = scanner.nextInt();
        System.out.print("Enter camp ID: ");
        int campId = scanner.nextInt();
        System.out.print("Full or Partial registration? ");
        String regType = scanner.next();
        Date registrationDate = new Date();
        registrationManager.registerEarly(participantId, campId, registrationDate, regType);
    }

    public static void registerForLateCamp(RegistrationManager registrationManager, Scanner scanner) {
        System.out.println("Registering for late camp.");
        System.out.print("Enter participant ID: ");
        int participantId = scanner.nextInt();
        System.out.print("Enter camp ID: ");
        int campId = scanner.nextInt();
        System.out.print("Full or Partial registration? ");
        String regType = scanner.next();
        Date registrationDate = new Date();
        registrationManager.registerLate(participantId, campId, registrationDate, regType);
    }

    private float calculatePrice(int campId, RegistrationType registrationType) {
        CampDto camp = campDao.getCampById(campId);
        if (camp == null) {
            return 0.0f;
        }

        float activityCost = 0;
        float baseCost = registrationType == RegistrationType.EARLY ? 300.0f : 100.0f;
        if (camp.getActivities() == null) {
            activityCost = 0;
        } else {
            activityCost = camp.getActivities().size() * 20.0f;
        }
        return baseCost + activityCost;
    }
}