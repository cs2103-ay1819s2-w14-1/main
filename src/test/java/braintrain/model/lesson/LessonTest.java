package braintrain.model.lesson;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import braintrain.model.card.Card;
import braintrain.model.lesson.exceptions.MissingCoreValueException;
import braintrain.testutil.Assert;


public class LessonTest {

    private static final String NAME_DEFAULT = "Test Lesson";

    private static final int CORE_COUNT_DEFAULT = 2;

    private static final List<String> FIELDS_DEFAULT = Arrays.asList("Country", "Capital");
    private static final List<String> FIELDS_OPTIONALS = Arrays.asList("Country", "Capital", "Hint", "Country Code");
    private static final List<String> CARD_STRINGS_DEFAULT = Arrays.asList("Japan", "Tokyo");
    private static final List<String> CARD_STRINGS_OPTIONALS = Arrays.asList("Japan", "Tokyo", "Starts with T", "JP");
    private static final Card CARD_JAPAN =
        new Card(Arrays.asList("Japan", "Tokyo"), Arrays.asList("Starts with T", "JP"));

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            new Lesson("", CORE_COUNT_DEFAULT, FIELDS_DEFAULT);
        });
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            new Lesson(null, CORE_COUNT_DEFAULT, FIELDS_DEFAULT);
        });
    }

    @Test
    public void constructor_invalidCoreCount_throwsIllegalArgumentException() {
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            new Lesson(NAME_DEFAULT, 0, FIELDS_DEFAULT);
        });
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            new Lesson(NAME_DEFAULT, 1, FIELDS_DEFAULT);
        });
    }

    @Test
    public void constructor_invalidFields_throwsIllegalArgumentException() {
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            new Lesson(NAME_DEFAULT, CORE_COUNT_DEFAULT, new ArrayList<>());
        });
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            new Lesson(NAME_DEFAULT, CORE_COUNT_DEFAULT, Arrays.asList("Country"));
        });
    }

    @Test
    public void addCard_invalidList_throwsIllegalArgumentException() {
        Lesson lesson = new Lesson(NAME_DEFAULT, CORE_COUNT_DEFAULT, FIELDS_DEFAULT);
        List<String> invalidList = Arrays.asList("Japan", "Tokyo", "Hint");
        StringBuilder sb = new StringBuilder();
        invalidList.forEach(s -> {
            sb.append(s + ",");
        });
        Assert.assertThrows(IllegalArgumentException.class,
            "Line: " + sb.toString() + " does not match lesson format", () -> {
                lesson.addCard(invalidList);
            });
    }

    @Test
    public void addCard_invalidCoreValue_throwsMissingCoreValueException() {
        Lesson lesson = new Lesson(NAME_DEFAULT, CORE_COUNT_DEFAULT, FIELDS_DEFAULT);
        Assert.assertThrows(MissingCoreValueException.class, "Core value: 0 is empty", () -> {
            lesson.addCard(Arrays.asList("", "Tokyo"));
        });
        Assert.assertThrows(MissingCoreValueException.class, "Core value: 1 is empty", () -> {
            lesson.addCard(Arrays.asList("Japan", ""));
        });
    }

    @Test
    public void addCard() {
        Lesson lesson = new Lesson(NAME_DEFAULT, CORE_COUNT_DEFAULT, FIELDS_DEFAULT);
        assertTrue(lesson.addCard(CARD_STRINGS_DEFAULT));
        assertTrue(lesson.addCard(CARD_STRINGS_DEFAULT));
    }

    @Test
    public void setQuestionAnswerIndices_invalidQuestionIndex_throwsIllegalArgumentException() {
        Lesson lesson = new Lesson(NAME_DEFAULT, CORE_COUNT_DEFAULT, FIELDS_DEFAULT);
        Assert.assertThrows(IllegalArgumentException.class, "Question index: -1 out of bounds", () -> {
            lesson.setQuestionAnswerIndices(-1, 1);
        });
        Assert.assertThrows(IllegalArgumentException.class, "Question index: 2 out of bounds", () -> {
            lesson.setQuestionAnswerIndices(2, 1);
        });
    }

    @Test
    public void setQuestionAnswerIndices_invalidAnswerIndex_throwsIllegalArgumentException() {
        Lesson lesson = new Lesson(NAME_DEFAULT, CORE_COUNT_DEFAULT, FIELDS_DEFAULT);
        Assert.assertThrows(IllegalArgumentException.class, "Answer index: -1 out of bounds", () -> {
            lesson.setQuestionAnswerIndices(0, -1);
        });
        Assert.assertThrows(IllegalArgumentException.class, "Answer index: 2 out of bounds", () -> {
            lesson.setQuestionAnswerIndices(0, 2);
        });
    }

    @Test
    public void setQuestionAnswerIndices() {
        Lesson lesson = new Lesson(NAME_DEFAULT, CORE_COUNT_DEFAULT, FIELDS_DEFAULT);
        assertEquals(lesson.getQuestionIndex(), 0);
        assertEquals(lesson.getAnswerIndex(), 0);
        lesson.setQuestionAnswerIndices(1, 1);
        assertEquals(lesson.getQuestionIndex(), 1);
        assertEquals(lesson.getAnswerIndex(), 1);

    }

    @Test
    public void setOptionalShown_newChange() {
        Lesson lesson = new Lesson(NAME_DEFAULT, CORE_COUNT_DEFAULT, FIELDS_OPTIONALS);
        lesson.addCard(CARD_STRINGS_OPTIONALS);

        assertFalse(lesson.getOptionals()[0]);
        assertTrue(lesson.setOptionalShown(0, true));
        assertTrue(lesson.getOptionals()[0]);
        assertTrue(lesson.setOptionalShown(0, false));
        assertFalse(lesson.getOptionals()[0]);
    }

    @Test
    public void setOptionalShown_noChange() {
        Lesson lesson = new Lesson(NAME_DEFAULT, CORE_COUNT_DEFAULT, FIELDS_OPTIONALS);
        lesson.addCard(CARD_STRINGS_OPTIONALS);

        assertFalse(lesson.getOptionals()[0]);
        assertTrue(lesson.setOptionalShown(0, false));
        assertFalse(lesson.getOptionals()[0]);
    }

    @Test
    public void setOptionalShown_invalidIndex() {
        Lesson lesson = new Lesson(NAME_DEFAULT, CORE_COUNT_DEFAULT, FIELDS_OPTIONALS);
        lesson.addCard(CARD_STRINGS_OPTIONALS);

        assertFalse(lesson.setOptionalShown(-1, true));
        assertFalse(lesson.setOptionalShown(-1, false));
        assertFalse(lesson.setOptionalShown(2, true));
        assertFalse(lesson.setOptionalShown(2, false));
    }

    @Test
    public void getCoreCount() {
        Lesson lesson = new Lesson(NAME_DEFAULT, CORE_COUNT_DEFAULT, FIELDS_DEFAULT);
        assertEquals(lesson.getCoreCount(), CORE_COUNT_DEFAULT);
    }

    @Test
    public void getCardFields() {
        Lesson lesson = new Lesson(NAME_DEFAULT, CORE_COUNT_DEFAULT, FIELDS_DEFAULT);
        assertEquals(lesson.getCardFields(), FIELDS_DEFAULT);

        lesson = new Lesson(NAME_DEFAULT, CORE_COUNT_DEFAULT, FIELDS_OPTIONALS);
        assertEquals(lesson.getCardFields(), FIELDS_OPTIONALS);
    }

    @Test
    public void getCards() {
        Lesson lesson = new Lesson(NAME_DEFAULT, CORE_COUNT_DEFAULT, FIELDS_OPTIONALS);
        assertTrue(lesson.isInitalized());
        assertTrue(lesson.getCards().size() == 0);

        lesson.addCard(CARD_STRINGS_OPTIONALS);
        assertTrue(lesson.getCards().size() == 1);
        assertEquals(lesson.getCards().get(0), CARD_JAPAN);
    }
}
