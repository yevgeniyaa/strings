package util;

import entity.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public static TextComponent parse(CompositePartOfText source) {
        if (source.getCompositeType() == CompositeType.WORD) return parseToSymbols(source);
        return parseOn(source);
    }

    private static TextComponent parseToSymbols(CompositePartOfText source) {
        char[] symbols = source.getSourceText().toCharArray();
        for (char currentSymbol : symbols) {
            Letter letter = Letter.valueOf(currentSymbol);
            source.add(letter);
        }
        return source;
    }

    private static TextComponent parseOn(CompositePartOfText source) {
        Pattern pattern = Pattern.compile(source.getCompositeType().getRegex(), Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(source.getSourceText());
        while (matcher.find()) {
            String componentText = matcher.group();
            CompositePartOfText componentObject = new CompositePartOfText(source.getChildType(), componentText);
            source.add(parse(componentObject));
        }
        return source;
    }
}
