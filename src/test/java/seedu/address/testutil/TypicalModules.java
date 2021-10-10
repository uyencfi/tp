package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.ModuleSystem;
import seedu.address.model.module.Module;

/**
 * A utility class containing a list of {@code Module} objects to be used in tests.
 */
public class TypicalModules {

    public static final Module CS2103 = new Module("CS2103");
    public static final Module CS2103T = new Module("CS2103T");
    public static final Module CS3230 = new Module("CS3230");

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

    public static void setTypicalModuleSystem() { Module.MODULE_SYSTEM.setModules(getTypicalModules()); }
}
