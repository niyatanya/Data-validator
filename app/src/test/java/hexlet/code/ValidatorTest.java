package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorTest {
    private static StringSchema stringSchema;
    @BeforeEach
    public void preparation() {
        Validator val = new Validator();
        stringSchema = val.string();
    }

    @Test
    public void stringSchemaTestNormal() throws NullPointerException {
        assertTrue(stringSchema.isValid("hexlet"));
        assertTrue(stringSchema.isValid(""));
        assertTrue(stringSchema.isValid(null));
    }

    @Test
    public void stringSchemaTestRequired() throws NullPointerException {
        stringSchema.required();
        assertTrue(stringSchema.isValid("hexlet"));
        assertFalse(stringSchema.isValid(""));
        assertFalse(stringSchema.isValid(null));
    }

    @Test
    public void stringSchemaTestMinLength() throws NullPointerException {
        stringSchema.minLength(4);
        assertTrue(stringSchema.isValid("hexlet"));
        assertFalse(stringSchema.isValid("hex"));
    }

    @Test
    public void stringSchemaTestContains() throws NullPointerException {
        assertTrue(stringSchema.contains("hex").isValid("hexlet"));
        assertFalse(stringSchema.contains("hes").isValid("hexlet"));
    }
}
