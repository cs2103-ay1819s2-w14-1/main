package braintrain.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import braintrain.model.card.Card;

/**
 * A utility class to help with building Person objects.
 */
public class CardBuilder {

    public static final List<String> DEFAULT_CORE = Collections.unmodifiableList(
            Arrays.asList("What is the capital of Belgium?", "Brussels"));

    public static final List<String> DEFAULT_OPTIONAL = Collections.unmodifiableList(
            Arrays.asList("Starts with B"));

    private List<String> cores;
    private List<String> optionals;

    public CardBuilder() {
        cores = new ArrayList<>();
        optionals = new ArrayList<>();
        cores.addAll(DEFAULT_CORE);
        optionals.addAll(DEFAULT_OPTIONAL);
    }

    /**
     * Initializes the CardBuilder with the data of {@code cardToCopy}.
     */
    public CardBuilder(Card cardToCopy) {
        cores = cardToCopy.getCores();
        optionals = cardToCopy.getOptionals();
    }

    /**
     * Parses the {@code cores} into a {@code List<cores>} and set it to the {@code Card} which we are building.
     */
    public CardBuilder withCores(String ... cores) {
        this.cores = Arrays.asList(cores);
        return this;
    }

    /**
     * Parses the {@code optionals} into a {@code List<optionals>} and set it to the {@code Card} which we are building.
     */
    public CardBuilder withOptionals(String ... optionals) {
        this.optionals = Arrays.asList(optionals);
        return this;
    }

    public Card build() {
        return new Card(cores, optionals);
    }
}
