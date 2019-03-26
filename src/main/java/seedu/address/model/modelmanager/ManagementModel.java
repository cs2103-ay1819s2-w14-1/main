package seedu.address.model.modelmanager;

import java.util.List;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.user.CardSrsData;
import seedu.address.model.user.User;

/**
 * The API of the ManagementModel component.
 */
public interface ManagementModel extends Model {
    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);


    /**
     * Gets the lesson by index.
     */
    Lesson getLesson(int index);

    /**
     * Opens the {@link Lesson} object at the specified index.
     *
     * @param index the index of the {@link Lesson} object to open
     * @return the name of the {@link Lesson} object
     */
    String openLesson(int index);

    /**
     * Returns the opened lesson. A lesson is opened by calling {@link #openLesson(int)} and
     * closed by calling {@link #closeLesson()}. If there is no lesson currently opened,
     * this returns null.
     *
     * @return the opened {@link Lesson}. Null if there is no opened lesson.
     */
    Lesson getOpenedLesson();

    /**
     * Closes the opened {@link Lesson} object.
     * @return the name of the closed {@link Lesson} object
     */
    String closeLesson();

    /**
     * Gets the entire list of lessons.
     */
    List<Lesson> getLessonList();

    /**
     * Adds the lesson.
     */
    void addLesson(Lesson lesson);

    /**
     * Updates the lesson at the given index.
     */
    void setLesson(int index, Lesson updatedLesson);

    /**
     * Removes the lesson at the given index from memory, and deletes its corresponding file.
     * @param index w
     */
    void deleteLesson(int index);

    User getUser();

    CardSrsData getCardSrsData(int hashCode);

    void addCardSrsData(CardSrsData card);

    void setCardSrsData(CardSrsData card);

    void deleteCardSrsData(CardSrsData card);
}