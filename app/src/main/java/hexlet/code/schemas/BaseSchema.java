package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    protected Map<String, Predicate<T>> checkers = new LinkedHashMap<>();
    public abstract BaseSchema<T> required();

    public final boolean isValid(T item) {
        if (checkers.isEmpty()) {
            return true;
        }

        for (Entry<String, Predicate<T>> checkerEntry : checkers.entrySet()) {
            if (!checkerEntry.getValue().test(item)) {
                return false;
            }
        }
        return true;
    }
}
