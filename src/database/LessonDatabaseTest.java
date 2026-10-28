
package database;

import entityClasses.LessonLearned;

public class LessonDatabaseTest {

    public static void main(String[] args) {
        try {
            Database db = new Database();
            db.connectToDatabase();

            String username = "hw2_test_user";

            // CREATE
            LessonLearned lesson = new LessonLearned(
                0, "Study Early", "Start homework early.", username
            );

            int id = db.createLesson(lesson);
            check("Create", id > 0);

            // READ
            LessonLearned saved = db.getLesson(id);
            check("Read", saved != null &&
                    saved.getTitle().equals("Study Early"));

            // LIST
            check("List", db.getLessonsByContributor(username)
                    .size() > 0);

            // UPDATE
            lesson.setTitle("Plan Ahead");
            check("Update", db.updateLesson(lesson) &&
                    db.getLesson(id).getTitle().equals("Plan Ahead"));

            // DELETE
            check("Delete", db.deleteLesson(id, username) &&
                    db.getLesson(id) == null);

            // NOT FOUND
            check("Not found", db.getLesson(id) == null);
            

            // EMPTY LIST
            check("Empty list",
                db.getLessonsByContributor("no_such_user_123").isEmpty());

            // UNKNOWN DELETE
            check("Unknown delete",
                !db.deleteLesson(id, username));

            // INVALID UPDATE
            LessonLearned invalid = new LessonLearned(
                id, "", "Description", username
            );

            boolean rejected = false;

            try {
                db.updateLesson(invalid);
            } catch (IllegalArgumentException e) {
                rejected = e.getMessage().equals("Title is required.");
            }

            check("Invalid update", rejected);

            // PERSISTENCE
            LessonLearned persistent = new LessonLearned(
                0, "Persistent Lesson", "Saved in H2.", username
            );

            int persistentId = db.createLesson(persistent);

            Database secondConnection = new Database();
            secondConnection.connectToDatabase();

            check("Persistence",
                secondConnection.getLesson(persistentId) != null);

            // CLEANUP
            secondConnection.deleteLesson(persistentId, username);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void check(String name, boolean passed) {
        System.out.println(name + ": " +
                (passed ? "PASS" : "FAIL"));
    }
}
