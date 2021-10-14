package seedu.edrecord.logic.parser;

import static seedu.edrecord.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.edrecord.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.edrecord.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.edrecord.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.edrecord.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.edrecord.logic.commands.CommandTestUtil.GROUP_DESC_AMY;
import static seedu.edrecord.logic.commands.CommandTestUtil.GROUP_DESC_BOB;
import static seedu.edrecord.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.edrecord.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.edrecord.logic.commands.CommandTestUtil.INVALID_GROUP_DESC;
import static seedu.edrecord.logic.commands.CommandTestUtil.INVALID_MODULE_DESC;
import static seedu.edrecord.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.edrecord.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.edrecord.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.edrecord.logic.commands.CommandTestUtil.MODULE_DESC_AMY;
import static seedu.edrecord.logic.commands.CommandTestUtil.MODULE_DESC_BOB;
import static seedu.edrecord.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.edrecord.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.edrecord.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.edrecord.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.edrecord.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.edrecord.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.edrecord.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.edrecord.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.edrecord.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.edrecord.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.edrecord.logic.commands.CommandTestUtil.VALID_GROUP_BOB;
import static seedu.edrecord.logic.commands.CommandTestUtil.VALID_MODULE_BOB;
import static seedu.edrecord.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.edrecord.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.edrecord.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.edrecord.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.edrecord.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.edrecord.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.edrecord.testutil.TypicalModules.setTypicalModuleSystem;
import static seedu.edrecord.testutil.TypicalPersons.AMY;
import static seedu.edrecord.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.edrecord.logic.commands.AddCommand;
import seedu.edrecord.model.group.Group;
import seedu.edrecord.model.module.Module;
import seedu.edrecord.model.name.Name;
import seedu.edrecord.model.person.Address;
import seedu.edrecord.model.person.Email;
import seedu.edrecord.model.person.Person;
import seedu.edrecord.model.person.Phone;
import seedu.edrecord.model.tag.Tag;
import seedu.edrecord.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND).build();
        setTypicalModuleSystem();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + MODULE_DESC_BOB + GROUP_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + MODULE_DESC_BOB + GROUP_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + MODULE_DESC_BOB + GROUP_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + MODULE_DESC_BOB + GROUP_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedPerson));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB + MODULE_DESC_BOB + GROUP_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedPerson));

        // multiple tags - all accepted
        Person expectedPersonMultipleTags = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + MODULE_DESC_BOB + GROUP_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                new AddCommand(expectedPersonMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Person expectedPerson = new PersonBuilder(AMY).withTags().build();
        setTypicalModuleSystem();

        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY
                + MODULE_DESC_AMY + GROUP_DESC_AMY, new AddCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);
        setTypicalModuleSystem();

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + MODULE_DESC_BOB + GROUP_DESC_BOB, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + MODULE_DESC_BOB + GROUP_DESC_BOB, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + ADDRESS_DESC_BOB
                + MODULE_DESC_BOB + GROUP_DESC_BOB, expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB
                + MODULE_DESC_BOB + GROUP_DESC_BOB, expectedMessage);

        // missing module prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + VALID_MODULE_BOB + GROUP_DESC_BOB, expectedMessage);

        // missing group prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + MODULE_DESC_BOB + VALID_GROUP_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB
                + VALID_MODULE_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        setTypicalModuleSystem();

        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + MODULE_DESC_BOB + GROUP_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + MODULE_DESC_BOB + GROUP_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
                + MODULE_DESC_BOB + GROUP_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + MODULE_DESC_BOB + GROUP_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Address.MESSAGE_CONSTRAINTS);

        // invalid module
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INVALID_MODULE_DESC + GROUP_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                Module.MESSAGE_CONSTRAINTS);

        // invalid group
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + MODULE_DESC_BOB + INVALID_GROUP_DESC + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                Group.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + MODULE_DESC_BOB + GROUP_DESC_BOB + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + INVALID_MODULE_DESC + GROUP_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + MODULE_DESC_BOB + GROUP_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
