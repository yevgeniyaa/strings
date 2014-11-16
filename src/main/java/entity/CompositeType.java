package entity;

public enum CompositeType {
    WORD(".{1}"),
    SENTENCE("([^(\\s)]*)([(\\W)]*)"),
    PARAGRAPH("([^(\\.|!|\\?)]+)([\\S])(\\. *|! *|\\? *)"),
    TEXT("([^\\s*](.+))");

    private String regex;

    CompositeType(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
