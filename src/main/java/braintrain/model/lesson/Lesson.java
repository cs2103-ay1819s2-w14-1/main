package braintrain.model.lesson;

import java.util.ArrayList;
import java.util.List;

import braintrain.model.card.Card;
import braintrain.model.lesson.exceptions.MissingCoreValueException;





/**
 * Represents a lesson that stores multiple cards.
 */
public class Lesson {
    private static final int CORE_COUNT_MINIMUM = 2;

    private String name;

    private List<Card> cards;
    private List<String> cardFields;
    private int coreCount;
    private int optionalCount;

    //pointers to column index
    private int questionIndex;
    private int answerIndex;
    private boolean[] optionals;

    /**
     * Constructor for lesson. Takes in formats and field names in preparation for
     * card input.
     *
     * @param coreCount TODO
     * @param fields    The list of names of each column corresponding to the format.
     */
    public Lesson(String name, int coreCount, List<String> fields) {
        if (name == null || name.length() == 0) {
            throw new IllegalArgumentException("Invalid name");
        }

        if (coreCount < CORE_COUNT_MINIMUM) {
            throw new IllegalArgumentException("CoreCount less than " + CORE_COUNT_MINIMUM + ": " + coreCount);
        }

        if (fields == null || fields.size() < CORE_COUNT_MINIMUM) {
            throw new IllegalArgumentException("Invalid fields");
        }

        cards = new ArrayList<>();

        this.name = name;
        this.coreCount = coreCount;
        cardFields = fields;
        optionalCount = fields.size() - coreCount;
        optionals = new boolean[optionalCount];
    }

    /**
     * @param cardValues List of values.
     * @return Whether add was successful. See List.add().
     */
    public boolean addCard(List<String> cardValues) throws MissingCoreValueException, IllegalArgumentException {
        //TODO: More proper exception handling
        if (cardValues.size() != cardFields.size()) {
            StringBuilder sb = new StringBuilder();
            cardValues.forEach(s -> sb.append(s + ","));
            throw new IllegalArgumentException("Line: " + sb.toString() + " does not match lesson format");
        }

        List<String> newCores = cardValues.subList(0, coreCount);
        for (int i = 0; i < newCores.size(); i++) {
            if (newCores.get(i).isEmpty()) {
                throw new MissingCoreValueException("Core value: " + i + " is empty");
            }
        }
        List<String> newOptionals = cardValues.subList(cardValues.size() - optionalCount, cardValues.size());
        Card newCard = new Card(newCores, newOptionals);
        return cards.add(newCard);
    }

    public void setQuestionAnswerIndices(int question, int answer) throws IllegalArgumentException {
        if (question < 0 || question >= coreCount) {
            throw new IllegalArgumentException("Question index: " + question + " out of bounds");
        }

        if (answer < 0 || answer >= coreCount) {
            throw new IllegalArgumentException("Answer index: " + answer + " out of bounds");
        }

        questionIndex = question;
        answerIndex = answer;
    }

    public boolean setOptionalShown(int index, boolean flag) {
        if (index < 0 || index >= optionalCount) {
            return false;
        }
        if (optionals[index] != flag) {
            optionals[index] = flag;
        }
        return true;
    }

    public int getCoreCount() {
        return coreCount;
    }

    public int getQuestionIndex() {
        return questionIndex;
    }

    public int getAnswerIndex() {
        return answerIndex;
    }

    public List<Card> getCards() {
        return cards;
    }

    public List<String> getCardFields() {
        return cardFields;
    }

    public boolean[] getOptionals() {
        return optionals;
    }

    public boolean isInitalized() {
        return cards != null;
    }

    /* TODO
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(name);

        return sb.toString();
    }
    */
}
