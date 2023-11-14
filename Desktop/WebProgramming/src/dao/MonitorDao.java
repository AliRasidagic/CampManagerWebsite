package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import business_layer.MonitorDto;
import database_connection.DatabaseConnection;

public class MonitorDao {
    private static final String GET_MONITOR_BY_ID_QUERY = "SELECT * FROM monitors WHERE monitorId = ?";
    private static final String INSERT_MONITOR_QUERY = "INSERT INTO monitors (monitorId, firstName, surname, isSpecialEducator) VALUES (?, ?, ?, ?)";

    public MonitorDto getMonitorById(int monitorId) {
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(GET_MONITOR_BY_ID_QUERY)) {
            preparedStatement.setInt(1, monitorId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToMonitor(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void createMonitor(MonitorDto monitor) {
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MONITOR_QUERY)) {
            preparedStatement.setInt(1, monitor.getMonitorId());
            preparedStatement.setString(2, monitor.getFirstName());
            preparedStatement.setString(3, monitor.getSurname());
            preparedStatement.setBoolean(4, monitor.isSpecialEducator());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private MonitorDto mapResultSetToMonitor(ResultSet resultSet) throws SQLException {
        MonitorDto monitor = new MonitorDto();
        monitor.setMonitorId(resultSet.getInt("monitorId"));
        monitor.setFirstName(resultSet.getString("firstName"));
        monitor.setSurname(resultSet.getString("surname"));
        monitor.setSpecialEducator(resultSet.getBoolean("isSpecialEducator"));
        return monitor;
    }
}
