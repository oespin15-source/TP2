
package inputValidation;

import entityClasses.LessonLearned;

public class LessonValidator {

    public static String validate(LessonLearned lesson) {

        if (lesson == null) {
            return "Lesson is required.";
        }

        if (lesson.getTitle() == null ||
            lesson.getTitle().trim().isEmpty()) {
            return "Title is required.";
        }

        if (lesson.getTitle().length() > 100) {
            return "Title must be 100 characters or fewer.";
        }

        if (lesson.getDescription() == null ||
            lesson.getDescription().trim().isEmpty()) {
            return "Description is required.";
        }

        if (lesson.getDescription().length() > 2000) {
            return "Description must be 2000 characters or fewer.";
        }

        if (lesson.getContributorUsername() == null ||
            lesson.getContributorUsername().trim().isEmpty()) {
            return "Contributor username is required.";
        }

        if (lesson.getContributorUsername().length() > 50) {
            return "Contributor username must be 50 characters or fewer.";
        }

        return "";
    }
}
