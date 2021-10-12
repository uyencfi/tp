package seedu.edrecord.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.edrecord.commons.core.GuiSettings;
import seedu.edrecord.logic.commands.CommandResult;
import seedu.edrecord.logic.commands.exceptions.CommandException;
import seedu.edrecord.logic.parser.exceptions.ParseException;
import seedu.edrecord.model.ReadOnlyEdRecord;
import seedu.edrecord.model.person.Person;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns EdRecord.
     *
<<<<<<< HEAD:src/main/java/seedu/edrecord/logic/Logic.java
     * @see seedu.edrecord.model.Model#getEdRecord()
=======
     * @see seedu.edrecord.model.Model#getAddressBook()
>>>>>>> 0dd5fee0d6b2321f7fa22797eaa524d349a0f607:src/main/java/seedu/address/logic/Logic.java
     */
    ReadOnlyEdRecord getEdRecord();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Returns the user prefs' edrecord file path.
     */
    Path getEdRecordFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
