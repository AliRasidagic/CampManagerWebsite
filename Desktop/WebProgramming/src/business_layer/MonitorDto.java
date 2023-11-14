package business_layer;

public class MonitorDto {
    protected int monitorId;
    protected String firstName;
    protected String surname;
    protected boolean isSpecialEducator;

    public MonitorDto() {
    }

    public MonitorDto(String firstName, String surname, boolean isSpecialEducator) {
        this.firstName = firstName;
        this.surname = surname;
        this.isSpecialEducator = isSpecialEducator;
    }

    public int getMonitorId() {
        return monitorId;
    }

    public void setMonitorId(int monitorId) {
        this.monitorId = monitorId;
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

    public boolean isSpecialEducator() {
        return isSpecialEducator;
    }

    public void setSpecialEducator(boolean specialEducator) {
        isSpecialEducator = specialEducator;
    }

    @Override
    public String toString() {
        return String.format("Monitor Details:\nMonitor ID: %d\nFirst Name: %s\nSurname: %s\nIs Special Educator: %s", monitorId, firstName,
                surname, isSpecialEducator ? "Yes" : "No");
    }
}
