
package entityClasses;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages a collection of LessonLearned objects.
 */
public class LessonsLearned {

    private List<LessonLearned> lessons;

    public LessonsLearned() {
        lessons = new ArrayList<>();
    }

    public void addLesson(LessonLearned lesson) {
        lessons.add(lesson);
    }

    public List<LessonLearned> getLessons() {
        return new ArrayList<>(lessons);
    }

    public int size() {
        return lessons.size();
    }

    public boolean isEmpty() {
        return lessons.isEmpty();
    }
}
