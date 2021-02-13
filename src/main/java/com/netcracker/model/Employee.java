package com.netcracker.model;

import java.util.Date;

public class Employee {

    private String firstName;
    private String middleName;
    private String lastName;
    private int age;
    private int salary;
    private String email;
    private String telephone;
    private String department;
    private String userAgent;
    private Date time;

    public Employee() {
    }

    public Employee(String firstName, String middleName, String lastName, int age, int salary, String email, String telephone, String department) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.age = age;
        this.salary = salary;
        this.email = email;
        this.telephone = telephone;
        this.department = department;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }


    @Override
    public String toString() {
        return firstName + "|" +
                middleName + "|" +
                lastName + "|" +
                age + "|" +
                salary + "|" +
                email + "|" +
                telephone + "|" +
                department;
    }


    public void setEmployee(Employee employee){

    }

}
