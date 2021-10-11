package seedu.edrecord.model;

import java.nio.file.Path;

import seedu.edrecord.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getAddressBookFilePath();

    Path getModuleSystemFilePath();
}
