package seedu.edrecord.model.person;

// TODO: Use a `Module` class instead of String

import java.util.function.Predicate;

import seedu.edrecord.model.module.Module;

/**
 * Tests if a {@code Person} is part of a module.
 */
public class PartOfModulePredicate implements Predicate<Person> {
    public static final PartOfModulePredicate PREDICATE_SHOW_ALL_MODULES = new PartOfModulePredicate("unused") {
        public boolean test(Person person) {
            return true;
        }
    };

    private final Module moduleCode;

    public PartOfModulePredicate(String moduleCode) {
        this.moduleCode = new Module(moduleCode);
    }

    @Override
    public boolean test(Person person) {
        return person.getModule().isSameModule(moduleCode);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PartOfModulePredicate // instanceof handles nulls
                && moduleCode.equals(((PartOfModulePredicate) other).moduleCode)); // state check
    }
}
