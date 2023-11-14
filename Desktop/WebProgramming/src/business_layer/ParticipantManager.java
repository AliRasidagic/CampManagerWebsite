package business_layer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import dao.ParticipantDao;

public class ParticipantManager {
    private List<ParticipantDto> participants;
    private ParticipantDto participantDto;
    private ParticipantDao participantDao;

    public ParticipantManager() {
        participants = new ArrayList<>();
        participantDao = new ParticipantDao();
        participantDto = new ParticipantDto();
    }

    public void registerParticipant(Scanner scanner) {
        SimpleDateFormat dateForm = new SimpleDateFormat("MM/dd/yyyy");

        System.out.print("Enter participant ID: ");
        int id = scanner.nextInt();

        System.out.print("Enter first name: ");
        String firstName = scanner.next();

        System.out.print("Enter last name: ");
        String lastName = scanner.next();

        System.out.print("Enter date of birth (yyyy/MM/dd): ");
        String dobStr = scanner.next();

        System.out.print("Does the participant require special attention? (true/false): ");
        boolean specialAttention = scanner.nextBoolean();

        Date dob = null;
        try {
            dob = dateForm.parse(dobStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        participantDto.setPersonId(id);
        participantDto.setFirstName(firstName);
        participantDto.setSurname(lastName);
        participantDto.setDateOfBirth(dob);
        participantDto.setNeedsSpecialAttention(specialAttention);

        participantDao.createParticipant(participantDto);
        System.out.println("Participant registered successfully.");
    }

    public void updateParticipant(Scanner scanner) {
        SimpleDateFormat dateForm = new SimpleDateFormat("MM/dd/YYYY");

        System.out.print("Enter the participant ID to update: ");
        int id = scanner.nextInt();

        participantDto = participantDao.getParticipantById(id);

        if (participantDto != null) {
            System.out.print("Enter new first name: ");
            String newFirstName = scanner.next();

            System.out.print("Enter new last name: ");
            String newLastName = scanner.next();

            System.out.print("Enter new date of birth (yyyy/MM/dd): ");
            String newDobStr = scanner.next();

            System.out.print("Does the participant require special attention? (true/false): ");
            boolean newSpecialAttention = scanner.nextBoolean();

            Date newDob = null;
            try {
                newDob = dateForm.parse(newDobStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            participantDto.setFirstName(newFirstName);
            participantDto.setSurname(newLastName);
            participantDto.setDateOfBirth(newDob);
            participantDto.setNeedsSpecialAttention(newSpecialAttention);

            participantDao.updateParticipant(participantDto);
            System.out.println("Participant updated successfully.");
        } else {
            System.out.println("Participant with ID " + id + " not found.");
        }
    }

    public void listParticipants() {
        participants = participantDao.getAllParticipants();
        if (participants.isEmpty()) {
            System.out.println("No participants available.");
        } else {
            System.out.println("List of Participants:");
            for (ParticipantDto participant : participants) {
                System.out.println(participant.toString());
            }
        }
    }
}