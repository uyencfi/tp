package seedu.edrecord.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.edrecord.testutil.TypicalAssignments.IP;
import static seedu.edrecord.testutil.TypicalAssignments.TP;
import static seedu.edrecord.testutil.TypicalModules.CS2103;
import static seedu.edrecord.testutil.TypicalModules.CS3230;

import org.junit.jupiter.api.Test;

import seedu.edrecord.testutil.ModuleBuilder;

public class ModuleTest {

    @Test
    public void isSameModule() {
        // same object -> returns true
        assertTrue(CS2103.isSameModule(CS2103));

        // null -> returns false
        assertFalse(CS2103.isSameModule(null));

        // same module code -> returns true
        assertTrue(CS2103.isSameModule(new Module("CS2103")));

        // different module code -> returns false
        assertFalse(CS3230.isSameModule(new Module("CS2103")));
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(CS2103.equals(CS2103));

        // null -> returns false
        assertFalse(CS2103.equals(null));

        // different module code -> returns false
        assertFalse(CS3230.equals(new Module("CS2103")));

        // same module code, different assignments -> returns false
        assertFalse(CS2103.equals(new Module("CS2103")));

        // same values -> returns true
        assertTrue(CS2103.equals(new ModuleBuilder("CS2103").withAssignment(IP).withAssignment(TP).build()));
    }
}
