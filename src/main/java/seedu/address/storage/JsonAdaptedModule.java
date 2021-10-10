package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.Module;

/**
 * Jackson-friendly version of {@link Module}.
 */
class JsonAdaptedModule {

    private final String code;

    /**
     * Constructs a {@code JsonAdaptedModule} with the given {@code mod}.
     */
    @JsonCreator
    public JsonAdaptedModule(String code) {
        this.code = code;
    }

    /**
     * Converts a given {@code Module} into this class for Jackson use.
     */
    public JsonAdaptedModule(Module source) {
        code = source.getCode();
    }

    @JsonValue
    public String getCode() {
        return code;
    }

    /**
     * Converts this Jackson-friendly adapted module object into the model's {@code Module} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Module toModelType() throws IllegalValueException {
        if (Module.MODULE_SYSTEM.hasModule(code)) {
            throw new IllegalValueException(Module.MESSAGE_DUPLICATE);
        }
        return new Module(code);
    }

}
