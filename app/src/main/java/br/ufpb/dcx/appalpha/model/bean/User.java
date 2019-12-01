package br.ufpb.dcx.appalpha.model.bean;

import java.util.HashSet;
import java.util.Set;

public class User {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Set<Challenge> challenges = new HashSet<>();
    private Set<Theme> contexts = new HashSet<>();

    public User(Long id, String name, String email, String password, Set<Challenge> challenges, Set<Theme> contexts) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.challenges = challenges;
        this.contexts = contexts;
    }

    public User(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Challenge> getChallenges() {
        return challenges;
    }

    public void setChallenges(Set<Challenge> challenges) {
        this.challenges = challenges;
    }

    public Set<Theme> getContexts() {
        return contexts;
    }

    public void setContexts(Set<Theme> contexts) {
        this.contexts = contexts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", challenges=" + challenges +
                ", contexts=" + contexts +
                '}';
    }
}
