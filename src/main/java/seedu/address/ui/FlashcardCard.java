package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.address.model.card.Card;

/**
 * An UI component that displays information of a {@link Card}.
 */
public class FlashcardCard extends UiPart<Region> {

    private static final String FXML = "FlashcardCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Card card;

    //@FXML
    //private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private FlowPane headers;

    public FlashcardCard(Card card, int displayedIndex, int questionIndex, int answerIndex) {
        super(FXML);
        this.card = card;
        id.setText(displayedIndex + ". ");
        name.setText(card.getCore(questionIndex) + " / " + card.getCore(answerIndex));

        for (int i = 0; i < card.getCores().size(); i++) {
            Label label = new Label(card.getCore(i));

            if (i == questionIndex || i == answerIndex) {
                label.getStyleClass().add("questionAnswer");
            } else {
                label.getStyleClass().add("core");
            }

            headers.getChildren().add(label);
        }

        for (int i = 0; i < card.getOptionals().size(); i++) {
            Label label = new Label(card.getOptional(i));
            label.getStyleClass().add("opt");
            headers.getChildren().add(label);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FlashcardCard)) {
            return false;
        }

        // state check
        FlashcardCard card = (FlashcardCard) other;
        return id.getText().equals(card.id.getText())
                && this.equals(card);
    }
}