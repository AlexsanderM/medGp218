package com.med218;

import javafx.beans.property.SimpleStringProperty;

import java.sql.Date;
import java.time.LocalDate;

public class Pathient {

    private int id;
    private String fioPathient;
    private String polisPathient;
    private Date dateBirdayPathient;
    private String prof;
    private String spec;
    private String fioDocter;
    private String diagnoz;
    private int service;
    private String kratnost;
    private Date dateService;
    private String attach;
    private String address;
    private String sex;
    private String comment;

    public Pathient(int id, Date dateService, String fioDocter, String attach, String polisPathient, String fioPathient, String diagnoz, String sex,
                    int service, String kratnost, String comment) {
        this.id = id;
        this.dateService = dateService;
        this.fioDocter = fioDocter;
        this.attach = attach;
        this.polisPathient = polisPathient;
        this.fioPathient = fioPathient;
        this.diagnoz = diagnoz;
        this.sex = sex;
        this.service = service;
        this.kratnost = kratnost;
        this.comment = comment;
    }

    public Pathient(int id, String fioPathient, String polisPathient, Date dateBirdayPathient, String attach, String address) {
        this.id = id;
        this.fioPathient = fioPathient;
        this.polisPathient = polisPathient;
        this.dateBirdayPathient = dateBirdayPathient;
        this.attach = attach;
        this.address = address;
    }

    public Pathient(int id, Date dateService, String prof, String spec, String diagnoz, int service, String kratnost) {
        this.id = id;
        this.dateService = dateService;
        this.prof = prof;
        this.spec = spec;
        this.diagnoz = diagnoz;
        this.service = service;
        this.kratnost = kratnost;
    }

    public String getFioPathient() {
        return fioPathient;
    }

    public String getPolisPathient() {
        return polisPathient;
    }

    public int getId() {
        return id;
    }

    public Date getDateBirdayPathient() {
        return dateBirdayPathient;
    }

    public Date getDateService() {
        return dateService;
    }

    public String getProf() {
        return prof;
    }

    public String getSpec() {
        return spec;
    }

    public String getDiagnoz() {
        return diagnoz;
    }

    public Integer getService() {
        return service;
    }

    public String getKratnost() {
        return kratnost;
    }

    public String getAttach() {
        return attach;
    }

    public String getAddress() { return address; }

    public String getFioDocter() { return fioDocter; }

    public String getSex() {
        return sex;
    }

    public String getComment() {
        return comment;
    }
}
