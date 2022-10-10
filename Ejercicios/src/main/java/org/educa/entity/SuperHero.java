package org.educa.entity;

public class SuperHero {
    private String name;
    private String company;
    private String creator;
    private String firstApparition;
    private String dateApparition;

    public SuperHero(String name, String company, String creator, String firstApparition, String dateApparition) {
        this.name = name;
        this.company = company;
        this.creator = creator;
        this.firstApparition = firstApparition;
        this.dateApparition = dateApparition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getFirstApparition() {
        return firstApparition;
    }

    public void setFirstApparition(String firstApparition) {
        this.firstApparition = firstApparition;
    }

    public String getDateApparition() {
        return dateApparition;
    }

    public void setDateApparition(String dateApparition) {
        this.dateApparition = dateApparition;
    }
}
