package seedu.edrecord.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.edrecord.commons.core.Messages;
import seedu.edrecord.model.person.PartOfModulePredicate;
import seedu.edrecord.model.Model;

// TODO: Consider updating UI to show the current selected module
// TODO: Update UG/DG

/**
 * Changes the selected module.
 */
public class CdCommand extends Command {

    public static final String COMMAND_WORD = "cd";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Changes the currently selected module. Use '*' to show all modules.\n"
            + "Parameters: NAME\n"
            + "Example: " + COMMAND_WORD + " CS1101S";

    private final PartOfModulePredicate predicate;

    public CdCommand(PartOfModulePredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setModuleFilter(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CdCommand // instanceof handles nulls
                && predicate.equals(((CdCommand) other).predicate)); // state check
    }

}
