package seedu.edrecord.storage.module;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.edrecord.commons.exceptions.IllegalValueException;
import seedu.edrecord.model.group.GroupSystem;
import seedu.edrecord.model.module.Module;
import seedu.edrecord.model.person.Name;
import seedu.edrecord.storage.group.JsonAdaptedGroup;

/**
 * Jackson-friendly version of {@link Module}.
 */
class JsonAdaptedModule {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Module's %s field is missing!";

    private final String code;
    private final List<JsonAdaptedGroup> groups = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedModule} with the given {@code mod} and list of groups.
     */
    @JsonCreator
    public JsonAdaptedModule(@JsonProperty("code") String code, @JsonProperty("groups") List<JsonAdaptedGroup> groups) {
        this.code = code;
        if (groups != null) {
            this.groups.addAll(groups);
        }
    }

    /**
     * Converts a given {@code Module} into this class for Jackson use.
     */
    public JsonAdaptedModule(Module source) {
        code = source.getCode();
        groups.addAll(source.getGroupSystem().getGroupList().stream().map(JsonAdaptedGroup::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted module object into the model's {@code Module} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted module.
     */
    public Module toModelType() throws IllegalValueException {
        if (code == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "module code"));
        }
        if (!Module.isValidNewModule(code)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        if (Module.MODULE_SYSTEM.hasModule(code)) {
            throw new IllegalValueException(Module.MESSAGE_DUPLICATE);
        }

        final GroupSystem groupSystem = new GroupSystem();
        for (JsonAdaptedGroup grp : groups) {
            groupSystem.addGroup(grp.toModelType());
        }

        return new Module(code, groupSystem);
    }

}
