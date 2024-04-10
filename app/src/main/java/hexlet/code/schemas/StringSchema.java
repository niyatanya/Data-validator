package hexlet.code.schemas;

import lombok.NoArgsConstructor;
import java.util.function.Predicate;

@NoArgsConstructor
public class StringSchema extends BaseSchema<String> {

    @Override
    public StringSchema required() {
        Predicate<String> requiredFunction = ((value) -> value != null && !value.isEmpty());
        checkers.put("required", requiredFunction);
        return this;
    }

    public StringSchema minLength(int minLength) {
        Predicate<String> minLengthFunction = ((value) -> value.length() > minLength);
        checkers.put("minLength", minLengthFunction);
        return this;
    }

    public StringSchema contains(String content) {
        Predicate<String> containsFunction = ((value) -> value.contains(content));
        checkers.put("contains", containsFunction);
        return this;
    }
}
