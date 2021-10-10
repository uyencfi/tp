package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyModuleSystem;

/**
 * A class to access Module data stored as a json file on the hard disk.
 */
public class JsonModuleSystemStorage implements ModuleSystemStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonModuleSystemStorage.class);

    private Path filePath;

    public JsonModuleSystemStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getModuleSystemFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyModuleSystem> readModuleSystem() throws DataConversionException {
        return readModuleSystem(filePath);
    }

    /**
     * Similar to {@link #readModuleSystem()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyModuleSystem> readModuleSystem(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableModuleSystem> jsonModuleSystem = JsonUtil.readJsonFile(
                filePath, JsonSerializableModuleSystem.class);
        if (!jsonModuleSystem.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonModuleSystem.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveModuleSystem(ReadOnlyModuleSystem moduleList) throws IOException {
        saveModuleSystem(moduleList, filePath);
    }

    /**
     * Similar to {@link #saveModuleSystem(ReadOnlyModuleSystem)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveModuleSystem(ReadOnlyModuleSystem moduleList, Path filePath) throws IOException {
        requireNonNull(moduleList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableModuleSystem(moduleList), filePath);
    }

}
