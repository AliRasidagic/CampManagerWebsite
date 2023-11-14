package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import business_layer.ParticipantDto;
import database_connection.DatabaseConnection;

public class ParticipantDao {
    private static final String GET_PARTICIPANT_BY_ID_QUERY = "SELECT * FROM participants WHERE personId = ?";
    private static final String GET_ALL_PARTICIPANTS_QUERY = "SELECT * FROM participants";
    private static final String UPDATE_PARTICIPANT_QUERY = "UPDATE participants SET firstName = ?, surname = ?, dateOfBirth = ?, needsSpecialAttention = ? WHERE personId = ?";
    private static final String INSERT_PARTICIPANT_QUERY = "INSERT INTO participants (personId, firstName, surname, dateOfBirth, needsSpecialAttention) VALUES (?, ?, ?, ?, ?)";

    public ParticipantDto getParticipantById(int personId) {
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(GET_PARTICIPANT_BY_ID_QUERY)) {
            preparedStatement.setInt(1, personId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToParticipant(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ParticipantDto> getAllParticipants() {
        List<ParticipantDto> participants = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_PARTICIPANTS_QUERY);
                ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                participants.add(mapResultSetToParticipant(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return participants;
    }

    public void createParticipant(ParticipantDto participant) {
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PARTICIPANT_QUERY)) {
            preparedStatement.setInt(1, participant.getPersonId());
            preparedStatement.setString(2, participant.getFirstName());
            preparedStatement.setString(3, participant.getSurname());
            preparedStatement.setDate(4, new java.sql.Date(participant.getDateOfBirth().getTime()));
            preparedStatement.setBoolean(5, participant.isNeedsSpecialAttention());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateParticipant(ParticipantDto participant) {
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PARTICIPANT_QUERY)) {
            preparedStatement.setInt(1, participant.getPersonId());
            preparedStatement.setString(2, participant.getFirstName());
            preparedStatement.setString(3, participant.getSurname());
            preparedStatement.setDate(4, new java.sql.Date(participant.getDateOfBirth().getTime()));
            preparedStatement.setBoolean(5, participant.isNeedsSpecialAttention());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ParticipantDto mapResultSetToParticipant(ResultSet resultSet) throws SQLException {
        ParticipantDto participant = new ParticipantDto();
        participant.setPersonId(resultSet.getInt("personId"));
        participant.setFirstName(resultSet.getString("firstName"));
        participant.setSurname(resultSet.getString("surname"));
        participant.setDateOfBirth(resultSet.getDate("dateOfBirth"));
        participant.setNeedsSpecialAttention(resultSet.getBoolean("needsSpecialAttention"));
        return participant;
    }
}
