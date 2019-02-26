package braintrain.model.lesson.exceptions;

/**
 * Signals that the operation is unable to find the specified person.
 */
public class MissingCoreValueException extends RuntimeException {
    public MissingCoreValueException(String message) {
        super(message);
    }
}
