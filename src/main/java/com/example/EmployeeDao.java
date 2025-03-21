package com.example;

import jakarta.persistence.*;
import java.util.List;

public class EmployeeDao {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("studentPU");

    public void addEmployee(Employee employee) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(employee);
        em.getTransaction().commit();
        em.close();
    }

    public List<Employee> getAllEmployees() {
        EntityManager em = emf.createEntityManager();
        List<Employee> employees = em.createQuery(
                        "SELECT e FROM Employee e LEFT JOIN FETCH e.project", Employee.class)
                .getResultList();
        em.close();
        return employees;
    }

    public void deleteEmployee(long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Employee employee = em.find(Employee.class, id);
        if (employee != null) {
            em.remove(employee);
        }
        em.getTransaction().commit();
        em.close();
    }
    public String getEmployeeSkills(Long employeeId) {
        EntityManager em = emf.createEntityManager();
        try {
            List<String> skills = em.createNativeQuery(
                            "SELECT skills FROM employee_skills WHERE employee_id = ?")
                    .setParameter(1, employeeId) // Utilisation d'un paramètre positionnel
                    .getResultList();
            return String.join(", ", skills); // Convertir la liste en une chaîne séparée par des virgules
        } finally {
            em.close();
        }
    }
    public void updateEmployee(Employee employee) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Employee existingEmployee = em.find(Employee.class, employee.getId());
        if (existingEmployee != null) {
            existingEmployee.setName(employee.getName());
            existingEmployee.setEmail(employee.getEmail());
            existingEmployee.setSkills(employee.getSkills());
            existingEmployee.setProject(employee.getProject());
            existingEmployee.setImplication(employee.getImplication()); // Mettre à jour l'implication
            existingEmployee.setPost(employee.getPost());
            em.merge(existingEmployee);
        }
        em.getTransaction().commit();
        em.close();
    }
}