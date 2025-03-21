package com.example;

import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.SessionScoped;
import jakarta.persistence.EntityManager;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;



@ManagedBean
@SessionScoped
public class EmployeeBean {
    private Employee employee = new Employee();
    private EmployeeDao employeeDAO = new EmployeeDao();
    private Employee selectedEmployee;
    private List<Post> posts = Arrays.asList(Post.values());
    private String skillsString;

    public Employee getEmployee() {
        return employee;
    }

    public void addEmployee() {
        if (skillsString != null && !skillsString.isEmpty()) {
            String[] skillsArray = skillsString.split(",");
            List<String> skillsList = new ArrayList<>();
            for (String skill : skillsArray) {
                skillsList.add(skill.trim());
            }
            employee.setSkills(skillsList);
        }
        employeeDAO.addEmployee(employee);
        employee = new Employee();
        skillsString = null;
    }

    public List<Employee> getEmployees() {
        return employeeDAO.getAllEmployees();
    }

    public Employee getSelectedEmployee() {
        return selectedEmployee;
    }

    public void setSelectedEmployee(Employee selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
    }

    public void loadEmployee(Employee employee) {
        this.selectedEmployee = employee;
    }

    public void updateEmployee() {
        if (selectedEmployee != null) {
            employeeDAO.updateEmployee(selectedEmployee);
            selectedEmployee = null;
        }
    }

    public void deleteEmployee(long id) {
        employeeDAO.deleteEmployee(id);
    }

    public List<Post> getPosts() {
        return posts;
    }

    public String getSkillsString() {
        return skillsString;
    }

    public void setSkillsString(String skillsString) {
        this.skillsString = skillsString;
    }
    public String getEmployeeSkills(Long employeeId) {
        return employeeDAO.getEmployeeSkills(employeeId);
    }
}