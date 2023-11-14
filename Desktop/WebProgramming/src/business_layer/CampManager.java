package business_layer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import dao.CampDao;
import enums.EducationalLevel;

public class CampManager {
    private List<CampDto> camps;
    private CampDao campDao;
    private CampDto campDto;

    public CampManager() {
        camps = new ArrayList<>();
        campDao = new CampDao();
        campDto = new CampDto();
    }

    public void createCamp(Scanner scanner) {
        SimpleDateFormat dateForm = new SimpleDateFormat("MM/dd/yyyy");
        EducationalLevel educationLevel = null;

        System.out.print("Enter camp ID: ");
        int campIdentifier = scanner.nextInt();

        System.out.print("Enter start date: ");
        String startDateStr = scanner.next();

        System.out.print("Enter end date: ");
        String endDateStr = scanner.next();

        System.out.print("Enter educational level (CHILDREN/YOUTH/TEENAGER): ");
        String educationalLvl = scanner.next();
        if (educationalLvl.equals("CHILDREN")) {
            educationLevel = EducationalLevel.CHILDREN;
        }
        if (educationalLvl.equals("YOUTH")) {
            educationLevel = EducationalLevel.YOUTH;
        }
        if (educationalLvl.equals("TEENAGER")) {
            educationLevel = EducationalLevel.TEENAGER;
        }

        System.out.print("Enter max participants: ");
        int maxParticipants = scanner.nextInt();

        Date startDate = null;
        Date endDate = null;
        try {
            startDate = dateForm.parse(startDateStr);
            endDate = dateForm.parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        campDto.setCampIdentifier(campIdentifier);
        campDto.setStartDate(startDate);
        campDto.setEndDate(endDate);
        campDto.setEducationalLevel(educationLevel.toString());
        campDto.setMaxParticipants(maxParticipants);

        campDao.createCamp(campDto);
        System.out.println("Camp created successfully.");
    }

    public void displayAllCamps() {
        camps = campDao.getAllCamps();
        if (camps.isEmpty()) {
            System.out.println("No camps available.");
        } else {
            System.out.println("List of Camps:");
            for (CampDto camp : camps) {
                System.out.println(camp.toString());
            }
        }
    }

    public void displayCampById(int campId) {
        campDto = campDao.getCampById(campId);
        if (campDto != null) {
            System.out.println("Camp Details:");
            System.out.println(campDto.toString());
        } else {
            System.out.println("Camp not found with ID: " + campId);
        }
    }

    public void checkCampAvailability(CampManager campManager) {
        System.out.println("Camps that have not yet started and have places available:");
        for (CampDto camp : campManager.getAvailableCamps()) {
            System.out.println(camp.toString());
        }
    }

    public CampDto[] getAvailableCamps() {
        List<CampDto> availableCampsList = campDao.getAllCamps();
        for (CampDto camp : camps) {
            if (!camp.hasStarted() && camp.hasAvailableSpots()) {
                availableCampsList.add(camp);
            }
        }
        return availableCampsList.toArray(new CampDto[0]);
    }
}