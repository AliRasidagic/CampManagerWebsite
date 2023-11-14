package business_layer;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import enums.RegistrationType;

public class PartialRegistration extends RegistrationDto {

    public PartialRegistration(int participantId, int campId, Date registrationDate, float price, RegistrationType registrationType) {
        super(participantId, campId, registrationDate, price, registrationType);
    }

    public static PartialRegistration createEarlyRegistration(int participantId, int campId, Date registrationDate, float price,
            RegistrationType registrationType) {
        Date currentDate = new Date();
        long diffInMillies = Math.abs(registrationDate.getTime() - currentDate.getTime());
        long dateDiff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        if (dateDiff >= 15) {
            return new PartialRegistration(participantId, campId, registrationDate, price, registrationType);
        } else {
            return null;
        }
    }

    public static PartialRegistration createLateRegistration(int participantId, int campId, Date registrationDate, float price,
            RegistrationType registrationType) {
        Date currentDate = new Date();
        long diffInMillies = Math.abs(registrationDate.getTime() - currentDate.getTime());
        long dateDiff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        if (dateDiff < 15 && dateDiff >= 2) {
            return new PartialRegistration(participantId, campId, registrationDate, price, registrationType);
        } else {
            System.out.println("Registration does not meet the criteria for a late registration.");
            return null;
        }
    }
}