package org.educa.entity;

public class SuperHero {
    private String name;
    private String company;
    private String creator;
    private String firstAppearance;
    private String dateAppereance;

    public SuperHero(String name, String company, String creator, String firstAppearance, String dateAppereance) {
        this.name = name;
        this.company = company;
        this.creator = creator;
        this.firstAppearance = firstAppearance;
        this.dateAppereance = dateAppereance;
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

    public String getFirstAppearance() {
        return firstAppearance;
    }

    public void setFirstAppearance(String firstAppearance) {
        this.firstAppearance = firstAppearance;
    }

    public String getDateAppereance() {
        return dateAppereance;
    }

    public void setDateAppereance(String dateAppereance) {
        this.dateAppereance = dateAppereance;
    }
}
