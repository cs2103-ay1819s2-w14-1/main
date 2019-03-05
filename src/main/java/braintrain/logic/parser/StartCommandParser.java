package braintrain.logic.parser;

import static braintrain.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static braintrain.logic.parser.CliSyntax.PREFIX_COUNT;
import static braintrain.logic.parser.CliSyntax.PREFIX_MODE;
import static braintrain.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.List;
import java.util.stream.Stream;

import braintrain.logic.commands.StartCommand;
import braintrain.logic.parser.exceptions.ParseException;
import braintrain.model.card.exceptions.MissingCoreException;
import braintrain.model.lesson.Lesson;
import braintrain.model.session.Session;
import braintrain.model.session.SrsCardsManager;
import braintrain.model.user.CardData;
import braintrain.quiz.Quiz;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Prefix;

/**
 * Parses user input when start a session quiz.
 */
public class StartCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the StartCommand
     * and returns an StartCommand object for execution.
     */
    public StartCommand parse(String args) throws ParseException, MissingCoreException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_COUNT, PREFIX_MODE);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_COUNT, PREFIX_MODE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, StartCommand.MESSAGE_USAGE));
        }

        String name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        int count = ParserUtil.parseCount(argMultimap.getValue(PREFIX_COUNT).get());
        Quiz.Mode mode = ParserUtil.parseMode(argMultimap.getValue(PREFIX_MODE).get());

        //TODO: generate current list of cards.
        Lesson currentLesson = null;
        List<CardData> currentCardData = null;
        SrsCardsManager manager = new SrsCardsManager(currentLesson, currentCardData);
        Session session = new Session(name, count, mode, manager.sort());

        return new StartCommand(session.generateSession());
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
