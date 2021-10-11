package seedu.edrecord.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.edrecord.commons.exceptions.DataConversionException;
import seedu.edrecord.model.ReadOnlyAddressBook;
import seedu.edrecord.model.ReadOnlyModuleSystem;
import seedu.edrecord.model.ReadOnlyUserPrefs;
import seedu.edrecord.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends AddressBookStorage, ModuleSystemStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getAddressBookFilePath();

    @Override
    Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException;

    @Override
    void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException;

    @Override
    Path getModuleSystemFilePath();

    @Override
    Optional<ReadOnlyModuleSystem> readModuleSystem() throws DataConversionException, IOException;

    @Override
    void saveModuleSystem(ReadOnlyModuleSystem moduleSystem) throws IOException;

}
