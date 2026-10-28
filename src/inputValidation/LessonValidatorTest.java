
package inputValidation;

import entityClasses.LessonLearned;

public class LessonValidatorTest {

    public static void main(String[] args) {

        testValidLesson();
        testBlankTitle();
        testBlankDescription();

        System.out.println("Validation tests finished.");
    }

    private static void testValidLesson() {
        LessonLearned lesson = new LessonLearned(
            0, "Study early", "Start assignments early.", "obed"
        );

        check("Valid lesson",
              LessonValidator.validate(lesson).isEmpty());
    }

    private static void testBlankTitle() {
        LessonLearned lesson = new LessonLearned(
            0, "", "Start assignments early.", "obed"
        );

        check("Blank title",
              LessonValidator.validate(lesson)
                  .equals("Title is required."));
    }

    private static void testBlankDescription() {
        LessonLearned lesson = new LessonLearned(
            0, "Study early", "", "obed"
        );

        check("Blank description",
              LessonValidator.validate(lesson)
                  .equals("Description is required."));
    }

    private static void check(String name, boolean passed) {
        System.out.println(
            name + ": " + (passed ? "PASS" : "FAIL")
        );
    }
}
