package business_layer;

import java.util.Date;

public class ParticipantDto {
    protected int personId;
    protected String firstName;
    protected String surname;
    protected Date dateOfBirth;
    protected boolean needsSpecialAttention;

    public ParticipantDto() {
    }

    public ParticipantDto(int personId, String firstName, String surname, Date dateOfBirth, boolean needsSpecialAttention) {
        this.personId = personId;
        this.firstName = firstName;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.needsSpecialAttention = needsSpecialAttention;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean isNeedsSpecialAttention() {
        return needsSpecialAttention;
    }

    public void setNeedsSpecialAttention(boolean needsSpecialAttention) {
        this.needsSpecialAttention = needsSpecialAttention;
    }

    @Override
    public String toString() {
        return "Participant Details:\nPerson ID: " + personId + "\nFirst Name: " + firstName + "\nSurname: " + surname + "\nDate of Birth: "
                + dateOfBirth + "\nNeeds Special Attention: " + (needsSpecialAttention ? "Yes" : "No");
    }
}
