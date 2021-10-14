package seedu.edrecord.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.edrecord.model.EdRecord;
import seedu.edrecord.model.ReadOnlyEdRecord;
import seedu.edrecord.model.group.Group;
import seedu.edrecord.model.group.GroupSystem;
import seedu.edrecord.model.module.Module;
import seedu.edrecord.model.module.ModuleSystem;
import seedu.edrecord.model.module.ReadOnlyModuleSystem;
import seedu.edrecord.model.name.Name;
import seedu.edrecord.model.person.Address;
import seedu.edrecord.model.person.Email;
import seedu.edrecord.model.person.Person;
import seedu.edrecord.model.person.Phone;
import seedu.edrecord.model.tag.Tag;

/**
 * Contains utility methods for populating {@code EdRecord} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Address("Blk 30 Geylang Street 29, #06-40"), new Module("CS2103"),
                    new Group("T03"), getTagSet("weak")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new Module("CS2103T"),
                    new Group("T07"), getTagSet("strong", "rude")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), new Module("CS2103"),
                    new Group("T03"), getTagSet("average")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), new Module("CS2103T"),
                    new Group("T07"), getTagSet("strong")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Address("Blk 47 Tampines Street 20, #17-35"), new Module("CS2103"),
                    new Group("T03"), getTagSet("weak", "shy")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"), new Module("CS2103T"),
                    new Group("T07"), getTagSet("average"))
        };
    }

    public static Group[] getSampleGroups() {
        return new Group[] {
            new Group("T03"),
            new Group("T07")
        };
    }

    public static GroupSystem getSampleGroupSystem() {
        GroupSystem sampleGs = new GroupSystem();
        for (Group sampleGroup : getSampleGroups()) {
            sampleGs.addGroup(sampleGroup);
        }
        return sampleGs;
    }

    public static Module[] getSampleModules() {
        return new Module[] {
            new Module("CS2103", getSampleGroupSystem()),
            new Module("CS2103T", getSampleGroupSystem())
        };
    }

    public static ReadOnlyModuleSystem getSampleModuleSystem() {
        ModuleSystem sampleMs = new ModuleSystem();
        for (Module sampleModule : getSampleModules()) {
            sampleMs.addModule(sampleModule);
        }
        return sampleMs;
    }

    public static ReadOnlyEdRecord getSampleEdRecord() {
        EdRecord sampleEr = new EdRecord();
        for (Person samplePerson : getSamplePersons()) {
            sampleEr.addPerson(samplePerson);
        }
        return sampleEr;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
