package seedu.edrecord.model.person;

// TODO: Use a `Module` class instead of String

import java.util.function.Predicate;

import seedu.edrecord.model.tag.Tag;

/**
 * Tests if a {@code Person} is part of a module.
 */
public class PartOfModulePredicate implements Predicate<Person> {
    public static final PartOfModulePredicate PREDICATE_SHOW_ALL_MODULES = new PartOfModulePredicate("unused") {
        public boolean test(Person person) {
            return true;
        }
    };

    private final Tag moduleTag;

    public PartOfModulePredicate(String module) {
        this.moduleTag = new Tag(module);
    }

    @Override
    public boolean test(Person person) {
        return person.getTags().contains(moduleTag);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PartOfModulePredicate // instanceof handles nulls
                && moduleTag.equals(((PartOfModulePredicate) other).moduleTag)); // state check
    }
}
