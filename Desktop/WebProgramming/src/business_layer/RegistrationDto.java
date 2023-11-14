package business_layer;

import java.util.Date;

import enums.RegistrationType;

public abstract class RegistrationDto {
    private int participantId;
    private int campId;
    private Date registrationDate;
    private float price;
    private RegistrationType registrationType;

    public RegistrationDto() {
    }

    public RegistrationDto(int participantId, int campId, Date registrationDate, float price, RegistrationType registrationType) {
        this.participantId = participantId;
        this.campId = campId;
        this.registrationDate = registrationDate;
        this.price = price;
        this.registrationType = registrationType;

        if (!checkParticipantExistence(participantId)) {
            throw new IllegalArgumentException("Participant ID does not exist.");
        }
        if (!checkCampExistence(campId)) {
            throw new IllegalArgumentException("Camp ID does not exist.");
        }
    }

    private boolean checkCampExistence(int campId) {
        if (campId == -1) {
            return false;
        } else {
            return true;
        }
    }

    private boolean checkParticipantExistence(int participantId) {
        if (participantId == -1) {
            return false;
        } else {
            return true;
        }
    }

    public int getParticipantId() {
        return participantId;
    }

    public void setParticipantId(int participantId) {
        this.participantId = participantId;
    }

    public int getCampId() {
        return campId;
    }

    public void setCampId(int campId) {
        this.campId = campId;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public RegistrationType getRegistrationType() {
        return registrationType;
    }

    public void setRegistrationType(RegistrationType registrationType) {
        this.registrationType = registrationType;
    }

    @Override
    public String toString() {
        return "Registration [participantId=" + participantId + ", campId=" + campId + ", registrationDate=" + registrationDate + ", price="
                + price + ", registrationType=" + registrationType + "]";
    }
}
