package utils;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.String.valueOf;

public class DataGenerator {

    private static DataGenerator instance;

    private DataGenerator() {}

    public static DataGenerator getInstance() {
        if (instance == null) {
            instance = new DataGenerator();
        }
        return instance;
    }

    public static String generatePhoneNumber(Enum country) {
        StringBuilder phone = new StringBuilder();
        switch (valueOf(country)) {
            case "RU":
                phone.append(977);
                for (int i = 0; i < 7; i++) {
                    phone.append((int) (Math.random() * 10));
                }
                break;
            case "BY":
                for (int i = 0; i < 9; i++) {
                    phone.append((int) (Math.random() * 10));
                }
                break;
        }
        return phone.toString();
    }

    public String generateDots(int dotNumber) {
        return Stream.generate(() -> "•").limit(dotNumber).collect(Collectors.joining());
    }
}