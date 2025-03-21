package com.example;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String implication; // Champ pour l'implication

    private String name;
    private String email;

    @ElementCollection
    private List<String> skills;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project; // Assurez-vous que cette propriété existe

    @Enumerated(EnumType.STRING)
    private Post post;

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getImplication() {
        return implication;
    }

    public void setImplication(String implication) {
        this.implication = implication;
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

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public Project getProject() { // Getter pour project
        return project;
    }

    public void setProject(Project project) { // Setter pour project
        this.project = project;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}