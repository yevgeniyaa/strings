package util;

import entity.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public static TextComponent parse(CompositePartOfText source) {
        CompositeType typeOfSonsTextPart;
        switch (source.getCompositeType()) {
            case TEXT:
                typeOfSonsTextPart = CompositeType.PARAGRAPH;
                break;
            case PARAGRAPH:
                typeOfSonsTextPart = CompositeType.SENTENCE;
                break;
            case SENTENCE:
                typeOfSonsTextPart = CompositeType.WORD;
                break;
            case WORD:
                char[] symbols = source.getSourceText().toCharArray();
                for (char currentSymbol : symbols) {
                    Letter letter = Letter.valueOf(currentSymbol);
                    source.add(letter);
                }
                return source;
            default:
                return source;
        }

        Pattern pattern = Pattern.compile(source.getCompositeType().getRegex(), Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(source.getSourceText());
        while (matcher.find()) {
            String componentText = matcher.group();
            CompositePartOfText componentObject = new CompositePartOfText(typeOfSonsTextPart, componentText);
            source.add(parse(componentObject));
        }
        return source;
    }
}
