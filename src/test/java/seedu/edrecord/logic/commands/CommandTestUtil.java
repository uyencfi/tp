package seedu.edrecord.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.edrecord.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.edrecord.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.edrecord.logic.parser.CliSyntax.PREFIX_INFO;
import static seedu.edrecord.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.edrecord.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.edrecord.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.edrecord.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.edrecord.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.edrecord.commons.core.index.Index;
import seedu.edrecord.logic.commands.exceptions.CommandException;
import seedu.edrecord.model.EdRecord;
import seedu.edrecord.model.Model;
import seedu.edrecord.model.person.NameContainsKeywordsPredicate;
import seedu.edrecord.model.person.Person;
import seedu.edrecord.testutil.EditPersonDescriptorBuilder;


/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_INFO_AMY = "Has trouble understanding database principles";
    public static final String VALID_INFO_BOB = "Has trouble understanding software engineering principles";
    public static final String VALID_MODULE_AMY = "CS2103";
    public static final String VALID_MODULE_BOB = "CS2103";
    public static final String VALID_GROUP_AMY = "T03";
    public static final String VALID_GROUP_BOB = "T03";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String INFO_DESC_AMY = " " + PREFIX_INFO + VALID_INFO_AMY;
    public static final String INFO_DESC_BOB = " " + PREFIX_INFO + VALID_INFO_BOB;
    public static final String MODULE_DESC_AMY = " " + PREFIX_MODULE + VALID_MODULE_AMY;
    public static final String MODULE_DESC_BOB = " " + PREFIX_MODULE + VALID_MODULE_BOB;
    public static final String GROUP_DESC_AMY = " " + PREFIX_GROUP + VALID_GROUP_AMY;
    public static final String GROUP_DESC_BOB = " " + PREFIX_GROUP + VALID_GROUP_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_INFO_DESC = " " + PREFIX_INFO; // empty string not allowed for info
    public static final String INVALID_MODULE_DESC = " " + PREFIX_MODULE + "CS 2103"; // whitespace not allowed
    public static final String INVALID_GROUP_DESC = " " + PREFIX_GROUP + "T 99"; // whitespace not allowed
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withInfo(VALID_INFO_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withInfo(VALID_INFO_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - edrecord, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        EdRecord expectedEdRecord = new EdRecord(actualModel.getEdRecord());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedEdRecord, actualModel.getEdRecord());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s edrecord.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().name.split("\\s+");
        model.setSearchFilter(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

}
