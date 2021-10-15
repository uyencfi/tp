package seedu.edrecord.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.edrecord.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's info in edrecord.
 * Guarantees: immutable; is valid as declared in {@link #isValidInfo(String)}
 */
public class Info {

    public static final String MESSAGE_CONSTRAINTS = "Info can take any values, and it should not be blank";

    /*
     * The first character of info must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code Info}.
     *
     * @param info A valid info.
     */
    public Info(String info) {
        requireNonNull(info);
        checkArgument(isValidInfo(info), MESSAGE_CONSTRAINTS);
        value = info;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidInfo(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Info // instanceof handles nulls
                && value.equals(((Info) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
