package hexlet.code.schemas;

import lombok.NoArgsConstructor;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;

@NoArgsConstructor
public class MapSchema<K, L> extends BaseSchema<Map<K, L>> {

    @Override
    public MapSchema<K, L> required() {
        Predicate<Map<K, L>> requiredFunction = ((value) -> value != null);
        checkers.put("required", requiredFunction);
        return this;
    }

    public MapSchema<K, L> sizeof(int size) {
        Predicate<Map<K, L>> sizeofFunction = ((value) -> value.size() == size);
        checkers.put("sizeof", sizeofFunction);
        return this;
    }

    public MapSchema<K, L> shape(Map<String, BaseSchema<L>> schemas) {
        Predicate<Map<K, L>> shapeFunction = ((value) -> {
            for (Entry<K, L> entry : value.entrySet()) {
                K key = entry.getKey();
                BaseSchema<L> schema = schemas.get(key);
                if (!schema.isValid((entry.getValue()))) {
                    return false;
                }
            }
            return true;
        });
        checkers.put("shape", shapeFunction);
        return this;
    }
}
