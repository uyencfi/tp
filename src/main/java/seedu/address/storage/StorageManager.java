package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyEdRecord;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of EdRecord data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private EdRecordStorage edRecordStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code EdRecordStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(EdRecordStorage edRecordStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.edRecordStorage = edRecordStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ EdRecord methods ==============================

    @Override
    public Path getEdRecordFilePath() {
        return edRecordStorage.getEdRecordFilePath();
    }

    @Override
    public Optional<ReadOnlyEdRecord> readEdRecord() throws DataConversionException, IOException {
        return readEdRecord(edRecordStorage.getEdRecordFilePath());
    }

    @Override
    public Optional<ReadOnlyEdRecord> readEdRecord(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return edRecordStorage.readEdRecord(filePath);
    }

    @Override
    public void saveEdRecord(ReadOnlyEdRecord edRecord) throws IOException {
        saveEdRecord(edRecord, edRecordStorage.getEdRecordFilePath());
    }

    @Override
    public void saveEdRecord(ReadOnlyEdRecord edRecord, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        edRecordStorage.saveEdRecord(edRecord, filePath);
    }

}
