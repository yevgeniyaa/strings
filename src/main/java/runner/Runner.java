package runner;

import entity.CompositePartOfText;
import entity.CompositeType;
import entity.Letter;

public class Runner {
    public static void main(String[] args) {
        String stringToParse = "Пузырь Алькубьерре — идея, основанная на верном решении уравнений Эйнштейна. " +
                "Предложенная мексиканским физиком-теоретиком Мигелем Алькубьерре.    \n" +
                "\tВ которой космический аппарат может достичь сверхсветовой скорости." +
                "\t\nДвижение выше скорости света невозможно для объектов в нормальном пространстве-времени.";

        CompositePartOfText text = new CompositePartOfText(CompositeType.TEXT, stringToParse);
        text.parse();

        CompositePartOfText paragraph = text.get(0);
        System.out.println(paragraph.toSourceString());

        CompositePartOfText sentence = paragraph.get(1);
        System.out.println(sentence.toSourceString());

        CompositePartOfText word = sentence.get(2);
        System.out.println(word.toSourceString());

        Letter letter = word.get(3);
        System.out.println(letter.toSourceString());
    }
}
