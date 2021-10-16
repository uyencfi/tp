package seedu.edrecord.testutil;

import static seedu.edrecord.testutil.TypicalAssignments.FINAL;
import static seedu.edrecord.testutil.TypicalAssignments.IP;
import static seedu.edrecord.testutil.TypicalAssignments.MIDTERM;
import static seedu.edrecord.testutil.TypicalAssignments.PE;
import static seedu.edrecord.testutil.TypicalAssignments.QUIZ;
import static seedu.edrecord.testutil.TypicalAssignments.TP;
import static seedu.edrecord.testutil.TypicalAssignments.TUTORIAL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.edrecord.model.ModuleSystem;
import seedu.edrecord.model.module.Module;

/**
 * A utility class containing a list of {@code Module} objects to be used in tests.
 */
public class TypicalModules {

    public static final Module CS2103 = new ModuleBuilder("CS2103")
            .withAssignment(IP).withAssignment(TP).build();
    public static final Module CS2103T = new ModuleBuilder("CS2103T")
            .withAssignment(PE).build();
    public static final Module CS3230 = new ModuleBuilder("CS3230")
            .build();
    public static final Module CS2100 = new ModuleBuilder("CS2100")
            .withAssignment(QUIZ).withAssignment(MIDTERM).withAssignment(FINAL).build();
    public static final Module CS2102 = new ModuleBuilder("CS2102")
            .withAssignment(TUTORIAL).build();

    private TypicalModules() {} // prevents instantiation

    /**
     * Returns an {@code ModuleSystem} with all the typical modules.
     */
    public static ModuleSystem getTypicalModuleSystem() {
        ModuleSystem ms = new ModuleSystem();
        ms.setModules(getTypicalModules());
        return ms;
    }

    public static List<Module> getTypicalModules() {
        return new ArrayList<>(Arrays.asList(CS2103, CS2103T, CS3230));
    }

    public static void setTypicalModuleSystem() {
        Module.MODULE_SYSTEM.setModules(getTypicalModules());
    }
}
