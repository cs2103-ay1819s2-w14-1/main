package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.management.EditLessonCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@link EditLessonCommand} object.
 */
public class OpenLessonParser implements Parser<EditLessonCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the
     * {@link EditLessonCommand} and returns an {@link EditLessonCommand} object
     * for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditLessonCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new EditLessonCommand(index);
        } catch (ParseException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                            EditLessonCommand.MESSAGE_USAGE), e);
        }
    }
}
