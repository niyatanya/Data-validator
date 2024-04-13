package hexlet.code.schemas;

import lombok.NoArgsConstructor;
import java.util.Map;

@NoArgsConstructor
public class MapSchema extends BaseSchema<Map<?, ?>> {

    public MapSchema sizeof(int size) {
        addCheck(
                "sizeof",
                value -> value.size() == size
        );
        return this;
    }

    public <T> MapSchema shape(Map<String, BaseSchema<T>> schemas) {
        addCheck(
                "shape",
                map -> {
                    return schemas.entrySet().stream().allMatch(e -> {
                        var v = map.get(e.getKey());
                        var schema = e.getValue();
                        return schema.isValid((T) v);
                    });
                }
        );
        return this;
    }
}
