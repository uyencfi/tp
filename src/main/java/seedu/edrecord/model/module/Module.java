package seedu.edrecord.model.module;

import static java.util.Objects.requireNonNull;

import seedu.edrecord.model.ModuleSystem;

/**
 * Represents a student's module in EdRecord.
 * Guarantees: immutable; is always valid
 */
public class Module {

    public static final String MESSAGE_CONSTRAINTS = "Module code cannot have whitespaces.";
    public static final String MESSAGE_DOES_NOT_EXIST = "Module with that code has yet to be created.";
    public static final String MESSAGE_DUPLICATE = "Module with that code has already been created.";

    /*
     * The module code must not have any whitespace characters.
     */
    public static final String VALIDATION_REGEX = "[^\\s]+";

    public static final ModuleSystem MODULE_SYSTEM = new ModuleSystem();

    public final String code;

    /**
     * Constructs a {@code Module}.
     *
     * @param code A valid module code.
     */
    public Module(String code) {
        requireNonNull(code);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    /**
     * Returns true if a given string is a valid module code.
     */
    public static boolean isValidNewModule(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if module given has the same module code.
     */
    public boolean isSameModule(Module toCheck) {
        return this.equals(toCheck);
    }

    @Override
    public String toString() {
        return code;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Module // instanceof handles nulls
                && code.equals(((Module) other).code)); // state check
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }
}
