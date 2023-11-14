package business_layer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class CampDto {
    protected int campIdentifier;
    protected Date startDate;
    protected Date endDate;
    protected String educationalLevel;
    protected int maxParticipants;
    protected List<ParticipantDto> registeredParticipants;
    protected List<MonitorDto> assignedMonitors;
    protected List<MonitorDto> specialAttentionMonitors;
    protected List<ActivityDto> activities;
    protected boolean started;

    public CampDto() {
    }

    public CampDto(Date startDate, Date endDate, String educationalLevel, int maxParticipants) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.educationalLevel = educationalLevel;
        this.maxParticipants = maxParticipants;
    }

    public boolean hasStarted() {
        return started;
    }

    public void startCamp() {
        this.started = true;
    }

    public boolean hasAvailableSpots() {
        return registeredParticipants != null && registeredParticipants.size() < maxParticipants;
    }

    public int getCampIdentifier() {
        return campIdentifier;
    }

    public void setCampIdentifier(int campIdentifier) {
        this.campIdentifier = campIdentifier;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getEducationalLevel() {
        return educationalLevel;
    }

    public void setEducationalLevel(String educationalLevel) {
        this.educationalLevel = educationalLevel;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public List<MonitorDto> getAssignedMonitors() {
        return assignedMonitors != null ? assignedMonitors : Collections.emptyList();
    }

    public void setAssignedMonitors(MonitorDto monitor) {
        if (this.assignedMonitors == null) {
            this.assignedMonitors = new ArrayList<>();
        }
        this.assignedMonitors.add(monitor);
    }

    public List<ActivityDto> getActivities() {
        return activities;
    }

    public void setActivities(ActivityDto activity) {
        if (this.activities == null) {
            this.activities = new ArrayList<>();
        }
        this.activities.add(activity);
    }

    public List<MonitorDto> getSpecialAttentionMonitors() {
        return specialAttentionMonitors;
    }

    public void setSpecialAttentionMonitors(MonitorDto monitor) {
        if (monitor.isSpecialEducator()) {
            if (this.specialAttentionMonitors == null) {
                this.specialAttentionMonitors = new ArrayList<>();
            }
            this.specialAttentionMonitors.add(monitor);
        } else {
            System.out.println("Error: The monitor is not a special educator.");
        }
    }

    @Override
    public String toString() {
        return "Camp Details:\nCamp Identifier: " + campIdentifier + "\nStart Date: " + startDate + "\nEnd Date: " + endDate
                + "\nEducational Level: " + educationalLevel + "\nMax Participants: " + maxParticipants;
    }

}
