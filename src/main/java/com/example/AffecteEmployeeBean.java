package com.example;

import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean
@SessionScoped
public class AffecteEmployeeBean {
    private long employeeId;
    private Employee employee;
    private EmployeeDao employeeDao = new EmployeeDao();
    private ProjectDao projectDao = new ProjectDao();
    private long selectedProjectId;
    private int implicationPercentage;
    private List<Project> projects;

    public void loadEmployee() {
        employee = employeeDao.getAllEmployees().stream()
                .filter(e -> e.getId() == employeeId)
                .findFirst()
                .orElse(null);
        loadProjects();
    }

    public void affectProject() {
        if (employee != null) {
            // Récupérer le projet sélectionné
            Project project = projectDao.getProjectById(selectedProjectId);

            // Concaténer le nom du projet et le pourcentage d'implication
            String implication = project.getName() + " - " + implicationPercentage + "%";

            // Mettre à jour l'employé
            employee.setProject(project);
            employee.setImplication(implication); // Mettre à jour l'implication

            // Mettre à jour l'employé dans la base de données
            employeeDao.updateEmployee(employee);
        }
    }

    public void loadProjects() {
        projects = projectDao.getAllProjects();
    }

    // Getters et setters
    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public long getSelectedProjectId() {
        return selectedProjectId;
    }

    public void setSelectedProjectId(long selectedProjectId) {
        this.selectedProjectId = selectedProjectId;
    }

    public int getImplicationPercentage() {
        return implicationPercentage;
    }

    public void setImplicationPercentage(int implicationPercentage) {
        this.implicationPercentage = implicationPercentage;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}