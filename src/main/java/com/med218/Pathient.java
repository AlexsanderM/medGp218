package com.med218;

import java.sql.Date;

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
    private String vdService;
    private String healthCenterService;
    private String poService;
    private String ekgService;
    private String flkService;
    private String mamService;
    private String smotrService;
    private String oakService;
    private String callasService;
    private String psaService;
    private String afterService;

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

    public Pathient(int id, Date dateService, String polisPathient, String address, String vdService,
                    String healthCenterService, String poService, String ekgService, String flkService,
                    String mamService, String smotrService, String oakService, String callasService,
                    String psaService, String afterService) {
        this.id = id;
        this.dateService = dateService;
        this.address = address;
        this.polisPathient = polisPathient;
        this.vdService = vdService;
        this.healthCenterService = healthCenterService;
        this.poService = poService;
        this.ekgService = ekgService;
        this.flkService = flkService;
        this.mamService = mamService;
        this.smotrService = smotrService;
        this.oakService = oakService;
        this.callasService = callasService;
        this.psaService = psaService;
        this.afterService = afterService;
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

    public String getAddress() {
        return address;
    }

    public String getFioDocter() {
        return fioDocter;
    }

    public String getSex() {
        return sex;
    }

    public String getComment() {
        return comment;
    }

    public String getVdService() {
        return vdService;
    }

    public String getHealthCenterService() {
        return healthCenterService;
    }

    public String getPoService() {
        return poService;
    }

    public String getEkgService() {
        return ekgService;
    }

    public String getFlkService() {
        return flkService;
    }

    public String getMamService() {
        return mamService;
    }

    public String getSmotrService() {
        return smotrService;
    }

    public String getOakService() {
        return oakService;
    }

    public String getCallasService() {
        return callasService;
    }

    public String getPsaService() {
        return psaService;
    }

    public String getAfterService() {
        return afterService;
    }
}
