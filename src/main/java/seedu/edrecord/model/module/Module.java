package seedu.edrecord.model.module;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.edrecord.model.group.Group;
import seedu.edrecord.model.group.GroupSystem;
import seedu.edrecord.model.group.ReadOnlyGroupSystem;

/**
 * Represents a module in EdRecord.
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
    public final GroupSystem groupSystem;

    /**
     * Constructs a {@code Module}.
     *
     * @param code A valid module code.
     * @param groupSystem A valid group system.
     */
    public Module(String code, GroupSystem groupSystem) {
        requireNonNull(code);
        this.code = code;

        requireNonNull(groupSystem);
        this.groupSystem = groupSystem;
    }

    /**
     * Constructs a {@code Module}.
     *
     * @param code A valid module code.
     */
    public Module(String code) {
        requireNonNull(code);
        this.code = code;
        this.groupSystem = new GroupSystem();
    }

    public String getCode() {
        return code;
    }

    public GroupSystem getGroupSystem() {
        return groupSystem;
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
        return this.code.equals(toCheck.getCode());
    }

    //=========== GroupSystem ================================================================================

    public void setGroupSystem(ReadOnlyGroupSystem groupSystem) {
        this.groupSystem.resetData(groupSystem);
    }

    public boolean hasGroup(Group grp) {
        requireNonNull(grp);
        return groupSystem.hasGroup(grp);
    }

    public void deleteGroup(Group target) {
        groupSystem.removeGroup(target);
    }

    public void addGroup(Group toAdd) {
        groupSystem.addGroup(toAdd);
    }

    public Group getGroup(String groupCode) {
        return groupSystem.getGroup(groupCode);
    }

    @Override
    public String toString() {
        return code;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Module // instanceof handles nulls
                && code.equals(((Module) other).code)
                && groupSystem.equals(((Module) other).groupSystem)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, groupSystem);
    }
}
