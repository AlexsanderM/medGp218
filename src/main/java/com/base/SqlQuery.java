package com.base;

public enum SqlQuery {
    IMPORTSERVICE("INSERT INTO Services(dateService, moEntity, address, attach, dataBirday, serPolis, sex, fioPathient, codDocter, depDocter, " +
            "fioDocter, profDoctor, codMeds, depMeds, fioMeds, profMeds, sum, kratnost, service, oc_sl, delet, diagnoz, source, obrash, zabolev) " +
            "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"),
    IMPORTSERVICE_INSURANCE("INSERT INTO Service_insurance(dateService, codDocter, fioDocter, address, attach, dataBirday, fioPathient, " +
            "serPolis, insuranceName, codMeds, fioMeds, sum, sumError, service, diagnoz, typeDiagnoz, numberService, nameService, kratnost, " +
            "source, operator, error) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"),
    IMPORTSERVICE_NAPR("INSERT INTO Service_napr(dateService, diagnoz, service, service_q, special_case, visit_result, " +
            "illness_outcome, codDocter, codMeds, forwardMO, forwardDate, serPolis, idServices) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)"),
    ALLUSERS("SELECT * FROM " + Const.USERTABLE),
    SIGNIN("SELECT * FROM " + Const.USERTABLE + " WHERE " + Const.USER_NAME + " =? AND " + Const.USER_PASS + " =?"),
    SEARCHPOLIC("SELECT DISTINCT serPolis, fioPathient, dataBirday, attach, address FROM " + Const.SERVICESTABLE + " WHERE " + Const.SERVICE_SERPOLIC + " like ? LIMIT 20"),
    SEARCHSERVICEPATHIENT("SELECT serPolis, dateService, coalesce(nullif(profDoctor,''), profMeds)  as " + Const.SERVICE_PROF +
            ", coalesce(nullif(fioDocter,''), fioMeds) as " + Const.SERVICE_SPECIALIST + "," +
            Const.SERVICE_DIAGNOZ + ", " + Const.SERVICE_SERVICE + "," + Const.SERVICE_KRATNOST + " FROM " + Const.SERVICESTABLE + " WHERE " + Const.SERVICE_SERPOLIC + " =?"),
    EARCH_ID_IN_SERVICES("SELECT idServices, dateService, diagnoz, service, serPolis FROM " + Const.SERVICESTABLE + " WHERE " + Const.SERVICE_DATE + " =? AND " +
            Const.SERVICE_SERPOLIC  + "=? AND " + Const.SERVICE_DIAGNOZ + "=? AND " + Const.SERVICE_SERVICE + "=? LIMIT 1");


    private String query;

    SqlQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}

