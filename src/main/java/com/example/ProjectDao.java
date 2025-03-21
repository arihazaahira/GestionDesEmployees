package com.example;

import jakarta.persistence.*;
import java.util.List;

public class ProjectDao {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("studentPU");

    public List<Project> getAllProjects() {
        EntityManager em = emf.createEntityManager();
        List<Project> projects = em.createQuery("SELECT p FROM Project p", Project.class).getResultList();
        em.close();
        return projects;
    }

    public Project getProjectById(long id) {
        EntityManager em = emf.createEntityManager();
        Project project = em.find(Project.class, id);
        em.close();
        return project;
    }
}