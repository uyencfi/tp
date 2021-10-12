package seedu.edrecord.model;

import static java.util.Objects.requireNonNull;
import static seedu.edrecord.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.edrecord.commons.core.GuiSettings;
import seedu.edrecord.commons.core.LogsCenter;
import seedu.edrecord.model.person.Person;

/**
 * Represents the in-memory model of edrecord data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final EdRecord edRecord;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;

    private Predicate<Person> selectedModulePredicate;

    /**
     * Initializes a ModelManager with the given edRecord and userPrefs.
     */
    public ModelManager(ReadOnlyEdRecord edRecord, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(edRecord, userPrefs);

        logger.fine("Initializing with edrecord: " + edRecord + " and user prefs " + userPrefs);

        this.edRecord = new EdRecord(edRecord);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.edRecord.getPersonList());
    }

    public ModelManager() {
        this(new EdRecord(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getEdRecordFilePath() {
        return userPrefs.getEdRecordFilePath();
    }

    @Override
    public void setEdRecordFilePath(Path edRecordFilePath) {
        requireNonNull(edRecordFilePath);
        userPrefs.setEdRecordFilePath(edRecordFilePath);
    }

    //=========== EdRecord ================================================================================

    @Override
    public void setEdRecord(ReadOnlyEdRecord edRecord) {
        this.edRecord.resetData(edRecord);
    }

    @Override
    public ReadOnlyEdRecord getEdRecord() {
        return edRecord;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return edRecord.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        edRecord.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        edRecord.addPerson(person);
        setSearchFilter(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        edRecord.setPerson(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedEdRecord}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void setModuleFilter(Predicate<Person> modulePredicate) {
        requireNonNull(modulePredicate);
        this.selectedModulePredicate = modulePredicate;
        filteredPersons.setPredicate(modulePredicate);
    }

    @Override
    public void setSearchFilter(Predicate<Person> searchPredicate) {
        requireNonNull(searchPredicate);
        Predicate<Person> modulePredicate =
                Optional.ofNullable(this.selectedModulePredicate).orElse(PREDICATE_SHOW_ALL_PERSONS);
        filteredPersons.setPredicate(modulePredicate.and(searchPredicate));
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return edRecord.equals(other.edRecord)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

}
