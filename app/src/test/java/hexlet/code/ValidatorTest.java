package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorTest {
    private static StringSchema stringSchema;
    private static NumberSchema numberSchema;
    @BeforeEach
    public void preparation() {
        Validator val = new Validator();
        stringSchema = val.string();
        Validator val1 = new Validator();
        numberSchema = val1.number();
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

    @Test
    public void numberSchemaTestNormal() {
        assertTrue(numberSchema.isValid(2));
        assertTrue(numberSchema.isValid(null));
    }

    @Test
    public void numberSchemaTestRequired() {
        numberSchema.required();
        assertFalse(numberSchema.isValid(null));
    }

    @Test
    public void numberSchemaTestPositive() {
        assertTrue(numberSchema.isValid(-10));
        numberSchema.positive();
        assertFalse(numberSchema.isValid(-10));
    }

    @Test
    public void numberSchemaTestRange() {
        numberSchema.range(5, 10);
        assertTrue(numberSchema.isValid(10));
    }
}
