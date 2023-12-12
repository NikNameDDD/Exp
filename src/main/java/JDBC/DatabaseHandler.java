package JDBC;

import dataproviders.ConnectionsDataProvider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import static constants.DatabaseRequests.DELETE_FROM_CLIENT;
import static constants.DatabaseRequests.DELETE_FROM_FINGERPRINT;
import static constants.DatabaseRequests.DELETE_FROM_SMS_BLOCK_SENDING;
import static constants.DatabaseRequests.DELETE_FROM_USER_PROFILE;
import static constants.DatabaseRequests.DELETE_FROM_VERIFICATION;
import static constants.DatabaseRequests.UPDATE_CLIENT_STATUS_TO_NOT_ACTIVE;
import static constants.DatabaseRequests.UPDATE_CLIENT_STATUS_TO_NOT_REGISTERED;

public class DatabaseHandler {

    static Connection dbConnection;
    private static DatabaseHandler instance;

    private ConnectionsDataProvider connectionsData = ConnectionsDataProvider.getInstance();

    public static DatabaseHandler getInstance() {
        if (instance == null) {
            instance = new DatabaseHandler();
        }
        return instance;
    }

    private Connection getDbConnection() {
        String connectionString = "jdbc:postgresql://" + connectionsData.getDataBaseHost()
                + ":" + connectionsData.getDataBasePort() + "/" + connectionsData.getDataBaseName();
        try {
            Class.forName("org.postgresql.Driver");
            dbConnection = DriverManager.getConnection(
                    connectionString, connectionsData.getDataBaseUser(), connectionsData.getDataBasePassword());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return dbConnection;
    }

    private static String addCountryCodeTo(String phone) {
        if (phone.length() == 10) {
            phone = "7" + phone;
        } else {
            phone = "375" + phone;
        }
        return phone;
    }

    public String getSMSCode(String phone) {
        phone = addCountryCodeTo(phone);
        String smsCode = "";
        try (Statement statement = getDbConnection().createStatement()) {
            do {
                ResultSet resultSet = statement.executeQuery("SELECT verification_code " +
                        "FROM \"user-service\".verification WHERE mobile_phone='" + phone + "'");
                while (resultSet.next()) {
                    smsCode = resultSet.getString("verification_code");
                }
            } while (smsCode.isEmpty());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return smsCode;
    }

    public void executeDatabaseScript(String request) {
        try (Statement statement = getDbConnection().createStatement()) {
            statement.executeUpdate(request);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRecordFromVerification(String phone) {
        phone = addCountryCodeTo(phone);
        executeDatabaseScript(String.format(DELETE_FROM_VERIFICATION, phone));
    }

    public void deleteRecordFromSMSBlockSending(String phone) {
        phone = addCountryCodeTo(phone);
        executeDatabaseScript(String.format(DELETE_FROM_SMS_BLOCK_SENDING, phone));
    }

    public void resetClientStatusToNotRegistered(String phone) {
        phone = addCountryCodeTo(phone);
        executeDatabaseScript(String.format(UPDATE_CLIENT_STATUS_TO_NOT_REGISTERED, phone));
    }

    public void resetClientStatusToNotActive(String phone) {
        phone = addCountryCodeTo(phone);
        executeDatabaseScript(String.format(UPDATE_CLIENT_STATUS_TO_NOT_ACTIVE, phone));
    }

    public void resetPossibilityToReceiveSMS(String phone) {
        deleteRecordFromVerification(phone);
        deleteRecordFromSMSBlockSending(phone);
    }

    public void deleteUserFromClientTableById(String userId) {
        executeDatabaseScript(String.format(DELETE_FROM_CLIENT, userId));
    }

    public void deleteUserFromUserProfileTableByClientId(String clientId) {
        executeDatabaseScript(String.format(DELETE_FROM_USER_PROFILE, clientId));
    }

    public void deleteFingerprintFromFingerprintTableByClientId(String clientId) {
        executeDatabaseScript(String.format(DELETE_FROM_FINGERPRINT, clientId));
    }

    public void deleteUserFromUserServiceDataBaseById(String userId) {
        deleteFingerprintFromFingerprintTableByClientId(userId);
        deleteUserFromUserProfileTableByClientId(userId);
        deleteUserFromClientTableById(userId);
    }

    public String getUserIdByPassportNumber(String passportNumber) {
        String id = "";
        String request = "SELECT id FROM \"user-service\".client WHERE passport_Number = ?";
        try (Connection connection = getDbConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(request)) {
            preparedStatement.setString(1, passportNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getString("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    private Boolean getUserBooleanInfoByClientId(String clientId, String dbColumnName) {
        Boolean columnDataInRow = null;
        ResultSet resultSet;
        String request = "SELECT * FROM \"user-service\".user_profile WHERE client_id = ?";
        try (Connection connection = getDbConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(request)) {
            preparedStatement.setObject(1, UUID.fromString(clientId));
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                columnDataInRow = resultSet.getBoolean(dbColumnName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columnDataInRow;
    }

    private String getUserStringInfoByClientId(String clientId, String dbColumnName) {
        ResultSet resultSet = null;
        String columnDataInRow = "";
        String request = "SELECT * FROM \"user-service\".user_profile WHERE client_id = ?";
        try (Connection connection = getDbConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(request)) {
            preparedStatement.setObject(1, UUID.fromString(clientId));
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                columnDataInRow = resultSet.getString(dbColumnName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columnDataInRow;
    }

    public String getEmailByClientId(String clientId) {
        return getUserStringInfoByClientId(clientId, "email");
    }

    public Boolean getSmsNotificationsStatusByClientId(String clientId) {
        return getUserBooleanInfoByClientId(clientId, "sms_notification");
    }

    public Boolean getPushNotificationsStatusByClientId(String clientId) {
        return getUserBooleanInfoByClientId(clientId, "push_notification");
    }

    public Boolean getEmailNotificationsStatusByClientId(String clientId) {
        return getUserBooleanInfoByClientId(clientId, "email_subscription");
    }

    public String getSecurityAnswerByClientId(String clientId) {
        return getUserStringInfoByClientId(clientId, "security_answer");
    }

    public String getSecurityQuestionByClientId(String clientId) {
        return getUserStringInfoByClientId(clientId, "security_question");
    }
}