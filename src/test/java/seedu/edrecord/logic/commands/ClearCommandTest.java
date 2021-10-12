package seedu.edrecord.logic.commands;

import static seedu.edrecord.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.edrecord.testutil.TypicalPersons.getTypicalEdRecord;

import org.junit.jupiter.api.Test;

import seedu.edrecord.model.EdRecord;
import seedu.edrecord.model.Model;
import seedu.edrecord.model.ModelManager;
import seedu.edrecord.model.UserPrefs;

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
