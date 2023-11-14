package business_layer;

import java.util.List;

import enums.EducationalLevel;

public class ActivityDto {

    protected String name;
    protected EducationalLevel educationalLevel;
    protected String timetable;
    protected int maxParticipants;
    protected int monitorsRequired;
    protected List<MonitorDto> assignedMonitors;

    public ActivityDto() {
    }

    public ActivityDto(String name, EducationalLevel educationalLevel, String timetable, int maxParticipants, int monitorsRequired,
            List<MonitorDto> assignedMonitors) {
        this.name = name;
        this.educationalLevel = educationalLevel;
        this.timetable = timetable;
        this.maxParticipants = maxParticipants;
        this.monitorsRequired = monitorsRequired;
        this.assignedMonitors = assignedMonitors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EducationalLevel getEducationalLevel() {
        return educationalLevel;
    }

    public void setEducationalLevel(EducationalLevel educationalLevel) {
        this.educationalLevel = educationalLevel;
    }

    public String getTimetable() {
        return timetable;
    }

    public void setTimetable(String timetable) {
        this.timetable = timetable;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public int getMonitorsRequired() {
        return monitorsRequired;
    }

    public void setMonitorsRequired(int monitorsRequired) {
        this.monitorsRequired = monitorsRequired;
    }

    public List<MonitorDto> getAssignedMonitors() {
        return assignedMonitors;
    }

    public void setAssignedMonitors(List<MonitorDto> assignedMonitors) {
        this.assignedMonitors = assignedMonitors;
    }

    @Override
    public String toString() {
        return "ActivityDTO{" + "name='" + name + '\'' + ", educationalLevel=" + educationalLevel + ", timetable='" + timetable + '\''
                + ", maxParticipants=" + maxParticipants + ", monitorsRequired=" + monitorsRequired + ", assignedMonitors="
                + assignedMonitors + '}';
    }
}