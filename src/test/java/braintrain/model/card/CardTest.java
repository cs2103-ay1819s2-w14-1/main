package braintrain.model.card;

import static braintrain.testutil.TypicalCards.BELGIUM;
import static braintrain.testutil.TypicalCards.JAPAN;
import static braintrain.testutil.TypicalCards.JAPAN_ANSWER;
import static braintrain.testutil.TypicalCards.JAPAN_HINT;
import static braintrain.testutil.TypicalCards.JAPAN_QUESTION;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import braintrain.testutil.CardBuilder;

public class CardTest {
    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(BELGIUM.equals(BELGIUM));

        // different object -> returns false
        assertFalse(BELGIUM.equals(JAPAN));

        // same cores and optionals -> returns true
        Card belgiumCopy = new CardBuilder(BELGIUM).build();
        assertTrue(BELGIUM.equals(belgiumCopy));

        // same cores with modified optionals -> returns false
        Card modifiedCopy = new CardBuilder(JAPAN).withOptionals("Same characters as Kyoto").build();
        assertFalse(JAPAN.equals(modifiedCopy));

        // modify existing card to have same cores and optionals as another card -> returns true
        modifiedCopy = new CardBuilder(BELGIUM)
                .withCores(JAPAN_QUESTION, JAPAN_ANSWER)
                .withOptionals(JAPAN_HINT).build();
        assertTrue(JAPAN.equals(modifiedCopy));
    }
}
