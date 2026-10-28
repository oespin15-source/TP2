
package guiRole1;

import database.Database;
import entityClasses.LessonLearned;
import entityClasses.User;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewLessonsLearned {

    public static void display(Stage stage, User user) {

        Database db = applicationMain.FoundationsMain.database;
        String username = user.getUserName();

        TextField title = new TextField();
        title.setPromptText("Lesson title");

        TextArea description = new TextArea();
        description.setPromptText("Lesson description");
        description.setPrefRowCount(3);

        ListView<String> list = new ListView<>();
        Label status = new Label();

        final int[] selectedId = {-1};

        // Refresh the list of lessons
        Runnable refresh = () -> {
            try {
                list.getItems().clear();

                for (LessonLearned lesson :
                        db.getLessonsByContributor(username).getLessons()) {
                    list.getItems().add(
                        lesson.getId() + ": " + lesson.getTitle()
                    );
                }
            } catch (Exception e) {
                status.setText(e.getMessage());
            }
        };

        // Select a lesson
        list.setOnMouseClicked(e -> {
            String selected = list.getSelectionModel().getSelectedItem();
            if (selected == null) return;

            try {
                int id = Integer.parseInt(selected.split(":")[0]);
                LessonLearned lesson = db.getLesson(id);

                if (lesson != null &&
                    username.equals(lesson.getContributorUsername())) {
                    selectedId[0] = id;
                    title.setText(lesson.getTitle());
                    description.setText(lesson.getDescription());
                    status.setText("Lesson selected.");
                }
            } catch (Exception ex) {
                status.setText(ex.getMessage());
            }
        });

        Button create = new Button("Create");
        create.setOnAction(e -> {
            try {
                LessonLearned lesson = new LessonLearned(
                    0, title.getText(), description.getText(), username
                );

                db.createLesson(lesson);
                refresh.run();
                status.setText("Lesson created.");
            } catch (Exception ex) {
                status.setText(ex.getMessage());
            }
        });

        Button update = new Button("Update");
        update.setOnAction(e -> {
            try {
                if (selectedId[0] == -1) {
                    status.setText("Select a lesson first.");
                    return;
                }

                LessonLearned lesson = new LessonLearned(
                    selectedId[0], title.getText(),
                    description.getText(), username
                );

                boolean updated = db.updateLesson(lesson);
                refresh.run();
                status.setText(updated ?
                    "Lesson updated." : "Lesson not found.");
            } catch (Exception ex) {
                status.setText(ex.getMessage());
            }
        });

        Button delete = new Button("Delete");
        delete.setOnAction(e -> {
            try {
                if (selectedId[0] == -1) {
                    status.setText("Select a lesson first.");
                    return;
                }

                boolean deleted = db.deleteLesson(
                    selectedId[0], username
                );

                selectedId[0] = -1;
                title.clear();
                description.clear();
                refresh.run();

                status.setText(deleted ?
                    "Lesson deleted." : "Lesson not found.");
            } catch (Exception ex) {
                status.setText(ex.getMessage());
            }
        });

        Button back = new Button("Back");
        back.setOnAction(e ->
            ViewRole1Home.displayRole1Home(stage, user)
        );

        VBox root = new VBox(10,
            new Label("Lessons Learned"),
            new Label("Title"), title,
            new Label("Description"), description,
            create, update, delete,
            new Label("My Lessons"), list,
            status, back
        );

        root.setPadding(new Insets(20));

        refresh.run();

        stage.setTitle("Lessons Learned");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }
}
