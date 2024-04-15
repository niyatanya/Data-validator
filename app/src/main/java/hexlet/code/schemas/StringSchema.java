package hexlet.code.schemas;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class StringSchema extends BaseSchema<String> {

    @Override
    public final StringSchema required() {
        addCheck(
                "required",
                value -> value != null && !value.isEmpty()
        );
        return this;
    }

    public final StringSchema minLength(int minLength) {
        addCheck(
                "minLength",
                value -> value.length() > minLength
        );
        return this;
    }

    public final StringSchema contains(String content) {
        addCheck(
                "contains",
                value -> value.contains(content)
        );
        return this;
    }
}
