package business_layer;

import java.util.List;
import java.util.Scanner;

import dao.ActivityDao;
import dao.CampDao;
import dao.MonitorDao;
import dao.ParticipantDao;

public class AssociateManager {

    public void associateMonitorToActivity(Scanner scanner) {
        MonitorDao monitorDao = new MonitorDao();
        ActivityDao activityDao = new ActivityDao();

        System.out.println("Enter the monitor ID: ");
        int monitorId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the activity name: ");
        String activityName = scanner.nextLine();

        MonitorDto monitor = monitorDao.getMonitorById(monitorId);
        ActivityDto activity = activityDao.findActivityByName(activityName);

        if (monitor != null && activity != null) {
            List<MonitorDto> assignedMonitors = activity.getAssignedMonitors();
            if (assignedMonitors.size() < activity.getMonitorsRequired()) {
                assignedMonitors.add(monitor);
                activityDao.updateActivity(activity);
                System.out.println("Monitor successfully assigned to the activity.");
            } else {
                System.out.println("The activity already has the required number of monitors.");
            }
        } else {
            System.out.println("Monitor or activity not found.");
        }
    }

    public void associateActivityToCamp(Scanner scanner) {
        ActivityDao activityDao = new ActivityDao();
        CampDao campDao = new CampDao();

        System.out.println("Enter the activity name: ");
        String activityName = scanner.nextLine();
        System.out.println("Enter the camp ID: ");
        int campIdentifier = scanner.nextInt();

        ActivityDto activity = activityDao.findActivityByName(activityName);
        CampDto camp = campDao.getCampById(campIdentifier);

        if (activity != null && camp != null) {
            camp.setActivities(activity);
            System.out.println("Activity successfully assigned to the camp.");
        } else {
            System.out.println("Either the activity or the camp is not found.");
        }
    }

    public void associateMonitorToCamp(Scanner scanner) {
        MonitorDao monitorDao = new MonitorDao();
        CampDao campDao = new CampDao();

        System.out.println("Enter the monitor ID: ");
        int monitorId = scanner.nextInt();
        System.out.println("Enter the camp ID: ");
        int campIdentifier = scanner.nextInt();

        MonitorDto monitor = monitorDao.getMonitorById(monitorId);
        CampDto camp = campDao.getCampById(campIdentifier);

        if (monitor != null && camp != null) {
            camp.setAssignedMonitors(monitor);
            System.out.println("Monitor successfully assigned to the camp.");
        } else {
            System.out.println("Either the monitor or the camp is not found.");
        }
    }

    public void associateSpecialAttentionMonitorsToCamps(Scanner scanner) {
        CampDao campDao = new CampDao();
        MonitorDao monitorDao = new MonitorDao();
        ParticipantDao participantDao = new ParticipantDao();

        System.out.println("Enter the monitor ID: ");
        int monitorId = scanner.nextInt();
        System.out.println("Enter the camp ID: ");
        int campIdentifier = scanner.nextInt();

        MonitorDto monitor = monitorDao.getMonitorById(monitorId);
        CampDto camp = campDao.getCampById(campIdentifier);

        if (monitor != null && camp != null) {
            List<ParticipantDto> participants = participantDao.getAllParticipants();

            for (ParticipantDto participant : participants) {
                if (participant.isNeedsSpecialAttention()) {
                    if (!camp.getAssignedMonitors().contains(monitor)) {
                        camp.setAssignedMonitors(monitor);
                        System.out.println("Monitor successfully associated with the camp.");
                        return;
                    }
                }
            }
            System.out.println("Either no participants need special attention or the monitor is already associated with the camp.");
        } else {
            System.out.println("Either the monitor or the camp is not found.");
        }
    }

}
