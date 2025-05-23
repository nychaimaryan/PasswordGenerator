
package org.example;

import java.security.SecureRandom;
import java.util.Scanner;

public class PasswordGenerator {

    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL = "!@#$%^&*()-_=+<>?";

    private static final String ALL_CHARS = UPPER + LOWER + DIGITS + SPECIAL;
    private static final SecureRandom random = new SecureRandom();

    public static void printPasswordRequirements() {
        System.out.println("=== Вимоги до сильного пароля ===");
        System.out.println("- Мінімум 12 символів");
        System.out.println("- Великі літери (A–Z)");
        System.out.println("- Малі літери (a–z)");
        System.out.println("- Цифри (0–9)");
        System.out.println("- Спеціальні символи (!@#$%^&*...)");
        System.out.println("==================================");
    }


    public static String generatePassword(int length) {
            if (length < 8) {
            throw new IllegalArgumentException("Пароль повинен бути не менше 8 символів.");
        }

        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            password.append(ALL_CHARS.charAt(random.nextInt(ALL_CHARS.length())));
        }
        return password.toString();
    }

    public static String checkStrength(String password) {
        int score = 0;

        if (password.length() >= 12) score++;
        if (password.matches(".*[a-z].*")) score++;
        if (password.matches(".*[A-Z].*")) score++;
        if (password.matches(".*\\d.*")) score++;
        if (password.matches(".*[!@#$%^&*()\\-_=+<>?].*")) score++;

        return switch (score) {
            case 5 -> "Дуже сильний";
            case 4 -> "Сильний";
            case 3 -> "Середній";
            case 2 -> "Посередній";
            case 1 -> "Слабкий";
            default -> "Дуже слабкий";
        };
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть бажану довжину пароля: ");
        int length = scanner.nextInt();

        try {
            String password = generatePassword(length);
            System.out.println("Згенерований пароль: " + password);
            System.out.println("Надійність пароля: " + checkStrength(password));
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
}
