package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    protected Map<String, Predicate<T>> checkers = new LinkedHashMap<>();
    public final void addCheck(String checkName, Predicate<T> fn) {
        checkers.put(checkName, fn);
    }

    /**
     * <p>Добавляет проверку на пустое поле и/или на null.</p>
     * @return Возвращает схему, в которую добавлена эта проверка.
     */
    public BaseSchema<T> required() {
        addCheck(
                "required",
                value -> value != null
        );
        return this;
    }

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
