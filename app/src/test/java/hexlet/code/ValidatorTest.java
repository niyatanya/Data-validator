package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import hexlet.code.schemas.BaseSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorTest {
    private static StringSchema stringSchema;
    private static NumberSchema numberSchema;
    private static MapSchema<String, String> mapSchema;

    private static MapSchema<String, String> mapSchema2;
    private static Validator val = new Validator();

    @BeforeEach
    public void preparation() {
        stringSchema = val.string();
        numberSchema = val.number();
        mapSchema = val.map();
        mapSchema2 = val.map();
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

    @Test
    public void mapSchemaTestNormal() {
        Map<String, String> map = new HashMap<>();
        map.put("Alex", "composer");
        assertTrue(mapSchema.isValid(map));
        Map<String, String> emptyMap = new HashMap<>();
        assertTrue(mapSchema.isValid(emptyMap));
        assertTrue(mapSchema.isValid(null));
    }

    @Test
    public void mapSchemaTestRequired() {
        mapSchema.required();
        assertFalse(mapSchema.isValid(null));
        Map<String, String> emptyMap = new HashMap<>();
        assertTrue(mapSchema.isValid(emptyMap));
    }

    @Test
    public void mapSchemaTestSizeof() {
        mapSchema.sizeof(3);
        Map<String, String> map = new HashMap<>();
        map.put("Alex", "composer");
        map.put("Max", "drums");
        assertFalse(mapSchema.isValid(map));
        map.put("Kate", "singer");
        assertTrue(mapSchema.isValid(map));
    }

    @Test
    public void mapShapedSchemaTest() {
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", val.string().required());
        schemas.put("lastName", val.string().required().minLength(2));
        mapSchema2.shape(schemas);

        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        assertTrue(mapSchema2.isValid(human1));

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        assertFalse(mapSchema2.isValid(human2));

        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        assertFalse(mapSchema2.isValid(human3));
    }
}
