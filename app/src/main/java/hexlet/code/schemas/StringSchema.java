package hexlet.code.schemas;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;
import java.util.Arrays;
import java.util.List;

@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StringSchema {
    private List<Object> listOfNotValidContent;
    private int minLength = 0;
    private String contentToBeContained = "";


    public void required() {
        List<Object> list = Arrays.asList(null, "");
        this.listOfNotValidContent.addAll(list);
    }

    public StringSchema minLength(int length) {
        this.minLength = length;
        return new StringSchema(this.listOfNotValidContent, this.minLength, this.contentToBeContained);
    }

    public StringSchema contains(String content) {
        this.contentToBeContained = content;
        return new StringSchema(this.listOfNotValidContent, this.minLength, this.contentToBeContained);
    }

    public boolean isValid(String text) {
        if (text.length() > minLength && containsText(text, contentToBeContained)) {
            if (isRequired(text)) {
                return true;
            }
        }
        return false;
    }

    public boolean isRequired(String text) {
        for (Object item : listOfNotValidContent) {
            if (text.equals(item)) {
                return false;
            }
        }
        return true;
    }

    public boolean containsText(String text, String content) {
        return text.matches(content);
    }


}
