package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NestedMapSchemaTest {
    private static MapSchema<String, String> mapSchema2;

    @BeforeEach
    public void preparation() {
        Validator val = new Validator();
        mapSchema2 = val.map();

        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", val.string().required());
        schemas.put("lastName", val.string().required().minLength(2));
        mapSchema2.shape(schemas);
    }

    @Test
    public void mapShapedSchemaTest() {
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
