package com.ukv.attendance;

public class User {

    public String id;
    public String user;
    public String subject;
    public String grade;
    public String semester;
    public String one_one;
    public String one_two;

    public String getOne_one() {
        return one_one;
    }

    public void setOne_one(String one_one) {
        this.one_one = one_one;
    }

    public String getOne_two() {
        return one_two;
    }

    public void setOne_two(String one_two) {
        this.one_two = one_two;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
