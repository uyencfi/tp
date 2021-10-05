package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalEdRecord;

import org.junit.jupiter.api.Test;

import seedu.address.model.EdRecord;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyEdRecord_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyEdRecord_success() {
        Model model = new ModelManager(getTypicalEdRecord(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalEdRecord(), new UserPrefs());
        expectedModel.setEdRecord(new EdRecord());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
