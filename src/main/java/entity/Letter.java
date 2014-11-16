package entity;

import java.util.HashMap;
import java.util.Map;

public class Letter implements TextComponent {
    char value;
    static Map<Character, Letter> characterLetterMap = new HashMap<Character, Letter>();

    private Letter(char letter) {
        this.value = letter;
    }

    @Override
    public String toSourceString() {
        return String.valueOf(value);
    }

    public static Letter valueOf(char charElement) {
        Letter letter = characterLetterMap.get(charElement);
        if (letter != null) return letter;
        letter = new Letter(charElement);
        characterLetterMap.put(charElement, letter);
        return letter;
    }
}
