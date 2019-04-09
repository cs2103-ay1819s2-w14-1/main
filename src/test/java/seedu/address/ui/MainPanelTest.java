package seedu.address.ui;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import guitests.guihandles.MainPanelHandle;
import seedu.address.model.quiz.QuizMode;
import seedu.address.model.quiz.QuizUiDisplayFormatter;

public class MainPanelTest extends GuiUnitTest {

    private MainPanel mainPanel;
    private MainPanelHandle mainPanelHandle;

    @Before
    public void setUp() {
        mainPanel = new MainPanel();
        uiPartRule.setUiPart(mainPanel);

        mainPanelHandle = new MainPanelHandle(getChildNode(mainPanel.getRoot(),
            MainPanelHandle.RESULT_DISPLAY_ID));
    }

    @Test
    public void display() {
        // default result text
        guiRobot.pauseForHuman();
        assertEquals("", mainPanelHandle.getText());

        // both question and answer
        QuizUiDisplayFormatter formatter = new QuizUiDisplayFormatter("Question", "some question",
            "Answer", "some answer", 0, QuizMode.PREVIEW);

        guiRobot.interact(() -> mainPanel.setFeedbackToUser(formatter));
        guiRobot.pauseForHuman();
        assertEquals("Question: some question\nAnswer: some answer\n\nPress Enter to go to the next question"
                + "\n\nCurrent total correct questions: 0", mainPanelHandle.getText());

        // only question
        QuizUiDisplayFormatter formatterReview = new QuizUiDisplayFormatter(
            "Question", "some question", "Answer", 0, QuizMode.REVIEW);

        guiRobot.interact(() -> mainPanel.setFeedbackToUser(formatterReview));
        guiRobot.pauseForHuman();
        assertEquals("Question: some question\n\nType the Answer for the Question above and press Enter"
                + "\n\nCurrent total correct questions: 0", mainPanelHandle.getText());

        // wrong twice in a row
        QuizUiDisplayFormatter formatterWrongTwice = new QuizUiDisplayFormatter(
            "Question", "some question", "Answer", "some answer",
                0, QuizMode.PREVIEW, true);

        guiRobot.interact(() -> mainPanel.setFeedbackToUser(formatterWrongTwice));
        guiRobot.pauseForHuman();
        assertEquals("Question: some question\nAnswer: some answer\n"
                + "\nType the Answer for the Question above and press Enter"
                + "\n\nCurrent total correct questions: 0", mainPanelHandle.getText());

        // switch back to management mode, so become blank
        guiRobot.interact(() -> mainPanel.setFeedbackToUser(null));
        guiRobot.pauseForHuman();
        assertEquals("", mainPanelHandle.getText());
    }
}
