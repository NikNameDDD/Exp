package dataproviders;

import pojos.User;

import java.util.Properties;

public class UserDataProvider {

    private static final String USERS_PROPERTY_FILE_PATH = "src/main/resources/user.properties";

    private static UserDataProvider instance;

    private Properties propertyReader = PropertyReader.getInstance().loadProperties(USERS_PROPERTY_FILE_PATH);

    private UserDataProvider() {
    }

    public static UserDataProvider getInstance() {
        if (instance == null) {
            instance = new UserDataProvider();
        }
        return instance;
    }

    public User getUserWithoutEmailFromPropertyFile() {
        return User
                .builder()
                .firstName(propertyReader.getProperty("userWithoutEmail.firstName"))
                .middleName(propertyReader.getProperty("userWithoutEmail.middleName"))
                .lastName(propertyReader.getProperty("userWithoutEmail.lastName"))
                .mobilePhone(propertyReader.getProperty("userWithoutEmail.mobilePhone"))
                .passportNumber(propertyReader.getProperty("userWithoutEmail.passportNumber"))
                .password(propertyReader.getProperty("userWithoutEmail.password"))
                .securityQuestion(propertyReader.getProperty("userWithoutEmail.securityQuestion"))
                .securityAnswer(propertyReader.getProperty("userWithoutEmail.securityAnswer"))
                .countryOfResidence(
                        Boolean.parseBoolean(propertyReader.getProperty("userWithoutEmail.countryOfResidence")))
                .email(propertyReader.getProperty("userWithoutEmail.email"))
                .clientStatus(propertyReader.getProperty("userWithoutEmail.clientStatus"))
                .build();
    }

    public User getUserWithEmailFromPropertyFile() {
        return User
                .builder()
                .firstName(propertyReader.getProperty("userWithEmail.firstName"))
                .middleName(propertyReader.getProperty("userWithEmail.middleName"))
                .lastName(propertyReader.getProperty("userWithEmail.lastName"))
                .mobilePhone(propertyReader.getProperty("userWithEmail.mobilePhone"))
                .passportNumber(propertyReader.getProperty("userWithEmail.passportNumber"))
                .password(propertyReader.getProperty("userWithEmail.password"))
                .securityQuestion(propertyReader.getProperty("userWithEmail.securityQuestion"))
                .securityAnswer(propertyReader.getProperty("userWithEmail.securityAnswer"))
                .countryOfResidence(
                        Boolean.parseBoolean(propertyReader.getProperty("userWithEmail.countryOfResidence")))
                .email(propertyReader.getProperty("userWithEmail.email"))
                .clientStatus(propertyReader.getProperty("userWithEmail.clientStatus"))
                .build();
    }
}