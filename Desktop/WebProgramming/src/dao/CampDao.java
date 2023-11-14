package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import business_layer.CampDto;
import database_connection.DatabaseConnection;

public class CampDao {
    private static final String GET_CAMP_BY_ID_QUERY = "SELECT * FROM camps WHERE campIdentifier = ?";
    private static final String GET_ALL_CAMPS_QUERY = "SELECT * FROM camps";
    private static final String CREATE_CAMP_QUERY = "INSERT INTO camps (campIdentifier, startDate, endDate, educationalLevel, maxParticipants) VALUES (?, ?, ?, ?, ?)";

    public CampDto getCampById(int campId) {
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(GET_CAMP_BY_ID_QUERY)) {
            preparedStatement.setInt(1, campId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToCamp(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<CampDto> getAllCamps() {
        List<CampDto> camps = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_CAMPS_QUERY);
                ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                camps.add(mapResultSetToCamp(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return camps;
    }

    public void createCamp(CampDto campDTO) {
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(CREATE_CAMP_QUERY)) {
            preparedStatement.setInt(1, campDTO.getCampIdentifier());
            preparedStatement.setDate(2, new java.sql.Date(campDTO.getStartDate().getTime()));
            preparedStatement.setDate(3, new java.sql.Date(campDTO.getEndDate().getTime()));
            preparedStatement.setString(4, campDTO.getEducationalLevel());
            preparedStatement.setInt(5, campDTO.getMaxParticipants());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private CampDto mapResultSetToCamp(ResultSet resultSet) throws SQLException {
        CampDto camp = new CampDto();
        camp.setCampIdentifier(resultSet.getInt("campIdentifier"));
        camp.setStartDate(resultSet.getDate("startDate"));
        camp.setEndDate(resultSet.getDate("endDate"));
        camp.setEducationalLevel(resultSet.getString("educationalLevel"));
        camp.setMaxParticipants(resultSet.getInt("maxParticipants"));
        return camp;
    }
}
