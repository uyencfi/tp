package seedu.edrecord.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.edrecord.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.edrecord.logic.parser.AddCommandParser.arePrefixesPresent;
import static seedu.edrecord.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.edrecord.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.edrecord.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.edrecord.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.edrecord.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.edrecord.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.edrecord.logic.commands.AddCommand;
import seedu.edrecord.logic.commands.MakeGroupCommand;
import seedu.edrecord.logic.commands.MakeModuleCommand;
import seedu.edrecord.logic.parser.exceptions.ParseException;
import seedu.edrecord.model.group.Group;
import seedu.edrecord.model.module.Module;
import seedu.edrecord.model.person.Name;
import seedu.edrecord.model.person.Phone;

public class MakeGroupCommandParser implements Parser<MakeGroupCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code MakeGroupCommand}
     * and returns a {@code MakeGroupCommand} object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public MakeGroupCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MODULE, PREFIX_GROUP);

        if (!arePrefixesPresent(argMultimap, PREFIX_MODULE, PREFIX_GROUP)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MakeGroupCommand.MESSAGE_USAGE));
        }

        Module module = ParserUtil.parseModule(argMultimap.getValue(PREFIX_MODULE).get());
        Group group = ParserUtil.parseMakeGroup(argMultimap.getValue(PREFIX_GROUP).get());

        return new MakeGroupCommand(group, module);
    }

}
