package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import business_layer.ActivityDto;
import database_connection.DatabaseConnection;
import enums.EducationalLevel;

public class ActivityDao {
    private static final String INSERT_ACTIVITY_QUERY = "INSERT INTO Activity (name, educationalLevel, timetable, maxParticipants, monitorsRequired) VALUES (?, ?, ?, ?, ?)";
    private static final String GET_ACTIVITY_BY_NAME_QUERY = "SELECT * FROM Activity WHERE name = ?";
    private static final String UPDATE_ACTIVITY_QUERY = "UPDATE activities SET assignedMonitors = ? WHERE name = ?";

    public void createActivity(ActivityDto activity) {
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ACTIVITY_QUERY)) {
            preparedStatement.setString(1, activity.getName());
            preparedStatement.setString(2, activity.getEducationalLevel().name());
            preparedStatement.setString(3, activity.getTimetable());
            preparedStatement.setInt(4, activity.getMaxParticipants());
            preparedStatement.setInt(5, activity.getMonitorsRequired());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ActivityDto findActivityByName(String activityName) {
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(GET_ACTIVITY_BY_NAME_QUERY)) {
            preparedStatement.setString(1, activityName);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToActivity(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ActivityDto mapResultSetToActivity(ResultSet resultSet) throws SQLException {
        ActivityDto activity = new ActivityDto();
        activity.setName(resultSet.getString("name"));
        activity.setEducationalLevel(EducationalLevel.valueOf(resultSet.getString("educationalLevel")));
        activity.setTimetable(resultSet.getString("timetable"));
        activity.setMaxParticipants(resultSet.getInt("maxParticipants"));
        activity.setMonitorsRequired(resultSet.getInt("monitorsRequired"));
        return activity;
    }

    public void updateActivity(ActivityDto activity) {
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ACTIVITY_QUERY)) {

            // Convert the List of assigned monitors to a comma-separated string
            String assignedMonitorsString = activity.getAssignedMonitors().stream().map(Object::toString)
                    .reduce((str1, str2) -> str1 + "," + str2).orElse("");

            preparedStatement.setString(1, assignedMonitorsString);
            preparedStatement.setString(2, activity.getName());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}