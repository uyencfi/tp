package seedu.edrecord.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.edrecord.model.EdRecord;
import seedu.edrecord.model.Model;

/**
 * Clears edrecord.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "EdRecord has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setEdRecord(new EdRecord());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
