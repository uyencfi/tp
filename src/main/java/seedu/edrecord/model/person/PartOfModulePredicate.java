package seedu.edrecord.model.person;

import java.util.function.Predicate;

import seedu.edrecord.logic.commands.CdCommand;
import seedu.edrecord.model.module.Module;

/**
 * Tests if a {@code Person} is part of a module.
 */
public class PartOfModulePredicate implements Predicate<Person> {
    public static final PartOfModulePredicate PREDICATE_SHOW_ALL_MODULES =
            new PartOfModulePredicate(CdCommand.WILDCARD_MODULE_CODE) {
                public boolean test(Person person) {
                    return true;
                }
            };

    private final Module module;

    public PartOfModulePredicate(String moduleCode) {
        this.module = new Module(moduleCode);
    }

    public Module getModule() {
        return module;
    }

    @Override
    public boolean test(Person person) {
        return person.getModule().isSameModule(module);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PartOfModulePredicate // instanceof handles nulls
                && module.equals(((PartOfModulePredicate) other).module)); // state check
    }
}
