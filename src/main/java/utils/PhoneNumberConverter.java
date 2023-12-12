package utils;

public class PhoneNumberConverter {

    public static String convertPhoneNumber(String phoneToConvert) {
        StringBuilder phone = new StringBuilder(phoneToConvert);
        switch (phoneToConvert.length()) {
            case 10:
                phone.insert(0, "+7 ").insert(6, " ").insert(10, " ");
                break;
            case 9:
                phone.insert(0, "+375 ").insert(7, " ").insert(11, " ")
                        .insert(14, " ");
                break;
        }
        return phone.toString();
    }
}