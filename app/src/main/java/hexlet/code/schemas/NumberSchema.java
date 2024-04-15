package hexlet.code.schemas;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NumberSchema extends BaseSchema<Integer> {

    public final NumberSchema positive() {
        addCheck(
                "positive",
                value ->  value == null || value > 0
        );
        return this;
    }

    public final NumberSchema range(int startIndex, int finalIndex) {
        addCheck(
                "range",
                value -> value >= startIndex && value <= finalIndex
        );
        return this;
    }
}
