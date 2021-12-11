package WildfireAnalysisAndPredictionSystem.test2;

import android.media.Image;
import android.widget.ImageButton;

public class Friend {
    private Image avatar;
    private String name;
    private ImageButton added_or_not;

    public Friend(Image avatar, String name, ImageButton added_or_not) {
        this.avatar = avatar;
        this.name = name;
        this.added_or_not = added_or_not;
    }

    public Image getAvatar() {
        return avatar;
    }

    public void setAvatar(Image avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ImageButton getAdded_or_not() {
        return added_or_not;
    }

    public void setAdded_or_not(ImageButton added_or_not) {
        this.added_or_not = added_or_not;
    }
}
