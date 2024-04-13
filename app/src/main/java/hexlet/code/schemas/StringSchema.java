package hexlet.code.schemas;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class StringSchema extends BaseSchema<String> {

    @Override
    public StringSchema required() {
        addCheck(
                "required",
                value -> value != null && !value.isEmpty()
        );
        return this;
    }

    public StringSchema minLength(int minLength) {
        addCheck(
                "minLength",
                value -> value.length() > minLength
        );
        return this;
    }

    public StringSchema contains(String content) {
        addCheck(
                "contains",
                value -> value.contains(content)
        );
        return this;
    }
}
