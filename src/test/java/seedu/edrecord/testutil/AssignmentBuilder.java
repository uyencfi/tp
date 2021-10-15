package seedu.edrecord.testutil;

import seedu.edrecord.model.assignment.Assignment;
import seedu.edrecord.model.assignment.MaxScore;
import seedu.edrecord.model.assignment.Weightage;
import seedu.edrecord.model.name.Name;

/**
 * A utility class to help with building Assignment objects.
 */
public class AssignmentBuilder {

    public static final String DEFAULT_NAME = "Assignment 1";
    public static final String DEFAULT_WEIGHTAGE = "2.5";
    public static final String DEFAULT_MAX_SCORE = "20";

    private Name name;
    private Weightage weightage;
    private MaxScore maxScore;

    /**
     * Creates an {@code AssignmentBuilder} with the default details.
     */
    public AssignmentBuilder() {
        name = new Name(DEFAULT_NAME);
        weightage = new Weightage(DEFAULT_WEIGHTAGE);
        maxScore = new MaxScore(DEFAULT_MAX_SCORE);
    }

    /**
     * Initializes the AssignmentBuilder with the data from {@code toCopy}.
     */
    public AssignmentBuilder(Assignment toCopy) {
        name = toCopy.getName();
        weightage = toCopy.getWeightage();
        maxScore = toCopy.getMaxScore();
    }

    /**
     * Sets the {@code Name} of the {@code Assignment} that we are building.
     */
    public AssignmentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Weightage} of the {@code Assignment} that we are building.
     */
    public AssignmentBuilder withWeightage(String weightage) {
        this.weightage = new Weightage(weightage);
        return this;
    }

    /**
     * Sets the {@code MaxScore} of the {@code Assignment} that we are building.
     */
    public AssignmentBuilder withMaxScore(String maxScore) {
        this.maxScore = new MaxScore(maxScore);
        return this;
    }

    /**
     * Builds and returns the Assignment.
     */
    public Assignment build() {
        return new Assignment(name, weightage, maxScore);
    }
}
