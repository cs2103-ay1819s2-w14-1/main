package braintrain.logic.parser;

import static java.util.Objects.requireNonNull;

import braintrain.logic.parser.exceptions.ParseException;
import braintrain.quiz.Quiz;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 * //TODO: exception
 */
public class ParserUtil {

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static String parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        return trimmedName;
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static int parseCount(String count) throws ParseException {
        requireNonNull(count);
        String trimmedCount = count.trim();
        return Integer.parseInt(trimmedCount);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Quiz.Mode parseMode(String mode) throws ParseException {
        requireNonNull(mode);
        String trimmedMode = mode.trim();
        return Quiz.Mode.valueOf(trimmedMode);
    }
}
