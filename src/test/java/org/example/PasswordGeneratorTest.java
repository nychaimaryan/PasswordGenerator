package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PasswordGeneratorTest {

    @Test
    public void testGeneratePasswordLength() {
        String password = PasswordGenerator.generatePassword(12);
        assertEquals(12, password.length());
    }

    @Test
    public void testGeneratePasswordTooShort() {
        assertThrows(IllegalArgumentException.class, () -> {
            PasswordGenerator.generatePassword(4);
        });
    }

    @Test
    public void testCheckStrength() {
        assertEquals("Сильний", PasswordGenerator.checkStrength("Ab1!pass123"));
        assertEquals("Слабкий", PasswordGenerator.checkStrength("12345678"));
    }
}
