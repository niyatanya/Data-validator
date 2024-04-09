package hexlet.code.schemas;

import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    Map<String, Predicate<T>> checkers;
    public abstract BaseSchema<T> required();

    public final boolean isValid(T item) throws NullPointerException {
        if (checkers.isEmpty()) {
            return true;
        }
        try {
            for (Entry<String, Predicate<T>> checkerEntry : checkers.entrySet()) {
                if (!checkerEntry.getValue().test(item)) {
                    return false;
                }
            }
        } catch (NullPointerException e) {
            e.getStackTrace();
        }
        return true;
    }
}
