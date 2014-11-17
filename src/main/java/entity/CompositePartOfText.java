package entity;

import util.Parser;

import java.util.ArrayList;
import java.util.List;

public class CompositePartOfText implements TextComponent {
    private CompositeType compositeType;
    private String sourceText;
    private List<TextComponent> childElementsList = new ArrayList<TextComponent>();

    public CompositePartOfText(CompositeType compositeType, String sourceText) {
        this.compositeType = compositeType;
        this.sourceText = sourceText;
    }

    public CompositeType getCompositeType() {
        return compositeType;
    }

    public String getSourceText() {
        return sourceText;
    }

    public void setSourceText(String sourceText) {
        this.sourceText = sourceText;
    }

    public <T extends TextComponent> T get(int index) {
        return (T) childElementsList.get(index);
    }

    public void add(TextComponent textComponent) {
        childElementsList.add(textComponent);
    }

    public void remove(int index) {
        childElementsList.remove(index);
    }

    public void remove(TextComponent textComponent) {
        childElementsList.remove(textComponent);
    }

    public String toSourceString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (TextComponent part : childElementsList) {
            stringBuilder.append(part.toSourceString());
        }
        return stringBuilder.toString();
    }

    public TextComponent parse(String source) {
        setSourceText(source);
        return Parser.parse(this);
    }

    public TextComponent parse() {
        return Parser.parse(this);
    }

    public CompositeType getChildType() {
        switch (getCompositeType()) {
            case TEXT:
                return CompositeType.PARAGRAPH;
            case PARAGRAPH:
                return CompositeType.SENTENCE;
            case SENTENCE:
                return CompositeType.WORD;
            default:
                return CompositeType.TEXT;
        }
    }
}
