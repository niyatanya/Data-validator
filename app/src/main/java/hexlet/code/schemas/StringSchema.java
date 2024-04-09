package hexlet.code.schemas;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.Map;
import java.util.function.Predicate;

@NoArgsConstructor
@AllArgsConstructor
public class StringSchema extends BaseSchema<String> {

    Map<String, Predicate<String>> checkers;
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
        Predicate<String> containsFunction = ((value) -> value.matches(content));
        checkers.put("contains", containsFunction);
        return this;
    }
}
