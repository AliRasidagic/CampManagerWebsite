package business_layer;

import java.util.Scanner;

import dao.MonitorDao;

public class MonitorManager {
    private MonitorDao monitorDao;
    private MonitorDto monitorDto;

    public MonitorManager() {
        monitorDao = new MonitorDao();
        monitorDto = new MonitorDto();
    }

    public void createMonitor(Scanner scanner) {
        System.out.print("Enter monitor ID: ");
        int monitorId = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter surname: ");
        String surname = scanner.nextLine();

        System.out.print("Is special educator? (true/false): ");
        boolean isSpecialEducator = Boolean.parseBoolean(scanner.nextLine());

        MonitorDto monitorDTO = new MonitorDto();
        monitorDTO.setMonitorId(monitorId);
        monitorDTO.setFirstName(firstName);
        monitorDTO.setSurname(surname);
        monitorDTO.setSpecialEducator(isSpecialEducator);

        monitorDao.createMonitor(monitorDTO);
        System.out.println("Monitor created successfully.");
    }

    public void findMonitorById(int monitorId) {
        monitorDto = monitorDao.getMonitorById(monitorId);

        if (monitorDto != null) {
            System.out.println("Camp Details:");
            System.out.println(monitorDto.toString());
        } else {
            System.out.println("Camp not found with ID: " + monitorId);
        }
    }
}
