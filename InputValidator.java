package util;

public class InputValidator {

    // Check positive ID
    public static boolean isValidId(int id) {
        return id > 0;
    }

    // Check quantity
    public static boolean isValidQuantity(int qty) {
        return qty >= 0;
    }

    // Check contact number
    public static boolean isValidContact(String contact) {
        return contact.matches("\\d{10}");
    }

    // Check text input
    public static boolean isValidText(String text) {
        return text != null && !text.trim().isEmpty();
    }
}

