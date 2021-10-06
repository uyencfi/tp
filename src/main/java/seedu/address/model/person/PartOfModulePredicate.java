package seedu.address.model.person;

// TODO: Use a `Module` class instead of String

import seedu.address.model.tag.Tag;

import java.util.function.Predicate;

/**
 * Tests if a {@code Person} is part of a module.
 */
public class PartOfModulePredicate implements Predicate<Person> {
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
