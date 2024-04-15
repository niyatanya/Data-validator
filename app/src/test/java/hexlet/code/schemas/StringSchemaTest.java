package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringSchemaTest {
    private static StringSchema stringSchema;

    @BeforeEach
    public final void preparation() {
        Validator val = new Validator();
        stringSchema = val.string();
    }

    @Test
    public void stringSchemaTestNormal() {
        assertTrue(stringSchema.isValid("hexlet"));
        assertTrue(stringSchema.isValid(""));
        assertTrue(stringSchema.isValid(null));
    }

    @Test
    public void stringSchemaTestRequired() {
        stringSchema.required();
        assertTrue(stringSchema.isValid("hexlet"));
        assertFalse(stringSchema.isValid(""));
        assertFalse(stringSchema.isValid(null));
    }

    @Test
    public void stringSchemaTestMinLength() {
        stringSchema.minLength(4);
        assertTrue(stringSchema.isValid("hexlet"));
        assertFalse(stringSchema.isValid("hex"));
    }

    @Test
    public void stringSchemaTestContains() {
        assertTrue(stringSchema.contains("hex").isValid("hexlet"));
        assertFalse(stringSchema.contains("hes").isValid("hexlet"));
    }
}
