package business_layer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.ActivityDao;
import enums.EducationalLevel;

public class ActivityManager {
    private List<ActivityDto> activities;
    private ActivityDao activityDao;
    private ActivityDto activityDto;

    public ActivityManager() {
        activities = new ArrayList<>();
        activityDao = new ActivityDao();
        activityDto = new ActivityDto();
    }

    public void createActivity(Scanner scanner) {
        System.out.print("Enter activity name: ");
        String name = scanner.nextLine();
        System.out.print("Enter educational level: ");
        String educationalLevelString = scanner.nextLine();
        EducationalLevel educationalLevel = EducationalLevel.valueOf(educationalLevelString.toUpperCase());
        System.out.print("Enter timetable: ");
        String timetable = scanner.nextLine();
        System.out.print("Enter maximum participants: ");
        int maxParticipants = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter monitors required: ");
        int monitorsRequired = Integer.parseInt(scanner.nextLine());

        // Now let's collect monitor information
        List<MonitorDto> assignedMonitors = new ArrayList<>();
        boolean addMoreMonitors = true;
        while (addMoreMonitors) {
            System.out.print("Enter monitor first name: ");
            String firstName = scanner.nextLine();
            System.out.print("Enter monitor surname: ");
            String surname = scanner.nextLine();
            System.out.print("Is the monitor a special educator? (true/false): ");
            boolean isSpecialEducator = Boolean.parseBoolean(scanner.nextLine());

            // Create a MonitorDto instance and add it to the list of assigned
            // monitors
            MonitorDto monitor = new MonitorDto(firstName, surname, isSpecialEducator);
            assignedMonitors.add(monitor);

            // Ask if the user wants to add more monitors
            System.out.print("Do you want to add more monitors? (true/false): ");
            addMoreMonitors = Boolean.parseBoolean(scanner.nextLine());
        }

        ActivityDto activity = new ActivityDto(name, educationalLevel, timetable, maxParticipants, monitorsRequired, assignedMonitors);
        activities.add(activity);
        activityDao.createActivity(activity);
        System.out.print("Activity succesfuly added!");

    }

    public void listActivities() {
        if (activities.isEmpty()) {
            System.out.println("No activities available.");
        } else {
            System.out.println("List of activities:");
            for (ActivityDto activity : activities) {
                System.out.println(activity.toString());
            }
        }
    }

    private void findActivityByName(String activityName) {
        activityDto = activityDao.findActivityByName(activityName);
        if (activityDto != null) {
            System.out.println("Activity Details:");
            System.out.println(activityDto);
        } else {
            System.out.println("Activity not found with ID: " + activityDto);
        }
    }
}
