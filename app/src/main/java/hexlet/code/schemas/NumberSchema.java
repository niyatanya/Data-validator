package hexlet.code.schemas;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema positive() {
        addCheck(
                "positive",
                value -> value > 0
        );
        return this;
    }

    public NumberSchema range(int startIndex, int finalIndex) {
        addCheck(
                "range",
                value -> value >= startIndex || value <= finalIndex
        );
        return this;
    }
}
