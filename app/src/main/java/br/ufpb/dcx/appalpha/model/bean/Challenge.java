package br.ufpb.dcx.appalpha.model.bean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Challenge {
    private Long id;
    private String word;
    private User creator;
    private String soundUrl;
    private String videoUrl;
    private String imageUrl;
    private List<Theme> contexts = new ArrayList<Theme>();

    public Challenge(Long id, String word, User creator, String soundUrl, String videoUrl, String imageUrl, List<Theme> contexts) {
        this.id = id;
        this.word = word;
        this.creator = creator;
        this.soundUrl = soundUrl;
        this.videoUrl = videoUrl;
        this.imageUrl = imageUrl;
        this.contexts = contexts;
    }

    public Challenge(Long id, String word, User creator, String soundUrl, String videoUrl, String imageUrl) {
        this.id = id;
        this.word = word;
        this.creator = creator;
        this.soundUrl = soundUrl;
        this.videoUrl = videoUrl;
        this.imageUrl = imageUrl;
    }

    public Challenge(String word, User creator, String soundUrl, String videoUrl, String imageUrl) {
        this.word = word;
        this.creator = creator;
        this.soundUrl = soundUrl;
        this.videoUrl = videoUrl;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public String getSoundUrl() {
        return soundUrl;
    }

    public void setSoundUrl(String soundUrl) {
        this.soundUrl = soundUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Theme> getContexts() {
        return contexts;
    }

    public void setContexts(List<Theme> contexts) {
        this.contexts = contexts;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Challenge challenge = (Challenge) o;
//        return id.equals(challenge.id);
//    }

    @Override
    public String toString() {
        return "Challenge{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", creator=" + creator +
                ", soundUrl='" + soundUrl + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", contexts=" + contexts +
                '}';
    }
}
