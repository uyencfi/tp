package seedu.address.logic.parser;

import seedu.address.logic.commands.MakeModuleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class MakeModCommandParser implements Parser<MakeModuleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code MakeModuleCommand}
     * and returns a {@code MakeModuleCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MakeModuleCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);

        Module mod;
        try {
            mod = ParserUtil.parseMakeModule(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MakeModuleCommand.MESSAGE_USAGE), pe);
        }

        return new MakeModuleCommand(mod);
    }

}
