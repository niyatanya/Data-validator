package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumberSchemaTest {
    private static NumberSchema numberSchema;

    @BeforeEach
    public void preparation() {
        Validator val = new Validator();
        numberSchema = val.number();
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
