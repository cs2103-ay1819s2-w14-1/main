package braintrain.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import braintrain.model.card.Card;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalCards {

    public static final Card BELGIUM = new CardBuilder().build();

    public static final String JAPAN_QUESTION = "What is the capital of Japan?";
    public static final String JAPAN_ANSWER = "Tokyo";
    public static final String JAPAN_HINT = "Starts with T";
    public static final Card JAPAN = new CardBuilder()
            .withCores(JAPAN_QUESTION, JAPAN_ANSWER)
            .withOptionals(JAPAN_HINT).build();

    private TypicalCards() {} // prevents instantiation

    public static List<Card> getTypicalCards() {
        return new ArrayList<>(Arrays.asList(BELGIUM, JAPAN));
    }
}
