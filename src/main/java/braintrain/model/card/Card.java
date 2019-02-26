package braintrain.model.card;

import static braintrain.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a flashcard which minimally contains two core fields, Question and Answer.
 * Guarantees: Core fields are present and not null, core and optional field values are validated.
 */
public class Card {
    // Identity fields
    private int hashCode; // Used for identification and equality purposes.

    // Data fields
    private ArrayList<String> cores; // Core fields a Card must have such as Question and Answer.
    private ArrayList<String> optionals; // Optional fields a Card can have such as Hint.

    /**
     * Every field must be present and not null.
     */
    public Card(List<String> cores, List<String> optionals) {
        requireAllNonNull(cores, optionals);
        this.cores = new ArrayList<>();
        this.optionals = new ArrayList<>();

        this.cores.addAll(cores);
        this.optionals.addAll(optionals);
        hashCode = generateHashCode();
    }

    public ArrayList<String> getCores() {
        return cores;
    }

    public ArrayList<String> getOptionals() {
        return optionals;
    }

    public void setCores(List<String> cores) {
        this.cores.addAll(cores);
    }

    public void setOptionals(List<String> optionals) {
        this.optionals.addAll(optionals);
    }

    public String getCore(int index) {
        return cores.get(index);
    }

    public String getOptional(int index) {
        return optionals.get(index);
    }

    public void setCore(int index, String s) {
        cores.set(index, s);
        hashCode = generateHashCode();
    }

    public void setOptional(int index, String s) {
        optionals.set(index, s);
        hashCode = generateHashCode();
    }

    /**
     * Returns true if both cards have the same data fields.
     * This defines a strong notion of equality between two cards.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Card)) {
            return false;
        }

        Card otherCard = (Card) other;
        return otherCard.hashCode() == this.hashCode();
    }

    /**
     * Generates a hash code using cores and optionals as input.
     * The hash code is used for identification and equality purposes.
     * @return A hash code generated using cores and optionals as input.
     */
    private int generateHashCode() {
        return Objects.hash(cores, optionals);
    }

    /**
     * Returns a hash code for identification and equality purposes.
     * Hash code is only updated with generateHashCode() when the card is created or modified.
     * @return A hash code identifying and representing a Card.
     */
    @Override
    public int hashCode() {
        return hashCode;
    }
}
