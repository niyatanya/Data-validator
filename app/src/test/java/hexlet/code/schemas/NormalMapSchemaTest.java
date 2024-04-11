package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NormalMapSchemaTest {
    private static MapSchema<String, String> mapSchema;

    @BeforeEach
    public void preparation() {
        Validator val = new Validator();
        mapSchema = val.map();
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
        map.put("Alex", "Smith");
        map.put("Max", "Paine");
        assertFalse(mapSchema.isValid(map));
        map.put("Kate", "Winslet");
        assertTrue(mapSchema.isValid(map));
    }
}
