package braintrain.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import braintrain.model.card.Card;
import braintrain.model.lesson.Lesson;

/**
 * A utility class to help with building {@code Lesson} objects.
 */
public class LessonBuilder {
    public static final String DEFAULT_NAME = "Capitals";
    public static final int DEFAULT_CORE_COUNT = 2;
    public static final List<String> DEFAULT_FIELDS = List.of("Country", "Capital");

    private String name;
    private List<Card> cards;
    private List<String> cardFields;
    private int coreCount;

    public LessonBuilder() {
        name = DEFAULT_NAME;
        cards = new ArrayList<>();
        cardFields = DEFAULT_FIELDS;
        coreCount = DEFAULT_CORE_COUNT;
    }

    /**
     * Initializes the LessonBuilder with the data of {@code lessonToCopy}.
     */
    public LessonBuilder(Lesson lessonToCopy) {
        name = lessonToCopy.getName();
        cards = lessonToCopy.getCards();
        cardFields = lessonToCopy.getCardFields();
        coreCount = lessonToCopy.getCoreCount();
    }

    /**
     * Parses the {@code cores} into a {@code List<cores>} and set it to the {@code Card} which we are building.
     */
    public LessonBuilder withCards(Card ... cards) {
        this.cards = Arrays.asList(cards);
        return this;
    }

    /**
     * Parses the {@code cores} into a {@code List<cores>} and set it to the {@code Card} which we are building.
     */
    public LessonBuilder withFields(String ... fields) {
        this.cardFields = Arrays.asList(fields);
        return this;
    }

    /**
     * Parses the {@code cores} into a {@code List<cores>} and set it to the {@code Card} which we are building.
     */
    public LessonBuilder withFields(List<String> fields) {
        this.cardFields = fields;
        return this;
    }

    public Lesson build() {
        return new Lesson(DEFAULT_NAME, DEFAULT_CORE_COUNT, DEFAULT_FIELDS);
    }
}