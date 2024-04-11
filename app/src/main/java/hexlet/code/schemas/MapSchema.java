package hexlet.code.schemas;

import lombok.NoArgsConstructor;
import java.util.Map;
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
}
