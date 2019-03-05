package braintrain.logic.parser;

import static braintrain.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static braintrain.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import braintrain.logic.commands.ExitCommand;
import braintrain.logic.commands.HelpCommand;
import braintrain.logic.commands.HistoryCommand;
import braintrain.logic.commands.StartCommand;
import braintrain.logic.parser.exceptions.ParseException;

public class BrainTrainParserTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final BrainTrainParser parser = new BrainTrainParser();

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_history() throws Exception {
        assertTrue(parser.parseCommand(HistoryCommand.COMMAND_WORD) instanceof HistoryCommand);
        assertTrue(parser.parseCommand(HistoryCommand.COMMAND_WORD + " 3") instanceof HistoryCommand);

        try {
            parser.parseCommand("histories");
            throw new AssertionError("The expected ParseException was not thrown.");
        } catch (ParseException pe) {
            assertEquals(MESSAGE_UNKNOWN_COMMAND, pe.getMessage());
        }
    }

    //    @Test
    //    public void parseCommand_redoCommandWord_returnsRedoCommand() throws Exception {
    //        assertTrue(parser.parseCommand(RedoCommand.COMMAND_WORD) instanceof RedoCommand);
    //        assertTrue(parser.parseCommand("redo 1") instanceof RedoCommand);
    //    }
    //
    //    @Test
    //    public void parseCommand_undoCommandWord_returnsUndoCommand() throws Exception {
    //        assertTrue(parser.parseCommand(UndoCommand.COMMAND_WORD) instanceof UndoCommand);
    //        assertTrue(parser.parseCommand("undo 3") instanceof UndoCommand);
    //    }
    @Test
    public void parseCommand_start() throws Exception {
        String input = StartCommand.COMMAND_WORD + " n/02-03-learn c/15 m/LEARN";
        assertTrue(parser.parseCommand(input) instanceof StartCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() throws Exception {
        thrown.expect(ParseException.class);
        thrown.expectMessage(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        parser.parseCommand("");
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() throws Exception {
        thrown.expect(ParseException.class);
        thrown.expectMessage(MESSAGE_UNKNOWN_COMMAND);
        parser.parseCommand("unknownCommand");
    }
}
