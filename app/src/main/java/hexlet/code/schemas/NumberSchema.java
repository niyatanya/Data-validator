package hexlet.code.schemas;

import lombok.NoArgsConstructor;
import java.util.function.Predicate;

@NoArgsConstructor
public class NumberSchema extends BaseSchema<Integer> {
    @Override
    public NumberSchema required() {
        Predicate<Integer> requiredFunction = ((value) -> value != null);
        checkers.put("required", requiredFunction);
        return this;
    }

    public NumberSchema positive() {
        Predicate<Integer> positiveFunction = ((value) -> value > 0);
        checkers.put("positive", positiveFunction);
        return this;
    }

    public NumberSchema range(int startIndex, int finalIndex) {
        Predicate<Integer> rangeFunction = ((value) -> value >= startIndex || value <= finalIndex);
        checkers.put("range", rangeFunction);
        return this;
    }
}
