
package entityClasses;

/**
 * Represents a lesson learned submitted by a contributor.
 */
public class LessonLearned {

    private int id;
    private String title;
    private String description;
    private String contributorUsername;

    public LessonLearned(int id, String title,
                         String description, String contributorUsername) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.contributorUsername = contributorUsername;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getContributorUsername() {
        return contributorUsername;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
