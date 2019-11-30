package br.ufpb.dcx.appalpha.model.bean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Theme {
    private Long id;
    private String name;
    private User creator;
    private String imageUrl;
    private String soundUrl;
    private String videoUrl;
    private List<Challenge>  challenges = new ArrayList<>();

    public Theme(Long id, String name, User creator, String imageUrl, String soundUrl, String videoUrl, List<Challenge>  challenges) {
        this.id = id;
        this.name = name;
        this.creator = creator;
        this.imageUrl = imageUrl;
        this.soundUrl = soundUrl;
        this.videoUrl = videoUrl;
        this.challenges = challenges;
    }

    public Theme(String name, String imageUrl, String soundUrl, String videoUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.soundUrl = soundUrl;
        this.videoUrl = videoUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public List<Challenge> getChallenges() {
        return challenges;
    }

    public void setChallenges(List<Challenge> challenges) {
        this.challenges = challenges;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Theme theme = (Theme) o;
        return id.equals(theme.id);
    }

    @Override
    public String toString() {
        return "Theme{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creator=" + creator +
                ", imageUrl='" + imageUrl + '\'' +
                ", soundUrl='" + soundUrl + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                '}';
    }
}
