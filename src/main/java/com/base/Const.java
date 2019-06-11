package com.base;

public class Const {
    public final static String USERTABLE = "Users";
    public final static String USER_NAME = "Users.nameUser";
    public final static String USER_PASS = "Users.passUser";

    public final static String SERVICESTABLE = "Services";
    public final static String SERVICE_ID = "Services.idServices";
    public final static String SERVICE_SERPOLIC = "Services.serPolis";
    public final static String SERVICE_FIOPATHIENT = "Services.fioPathient";
    public final static String SERVICE_BIRTHPATHIENT = "Services.dataBirday";
    public final static String SERVICE_DATE = "Services.dateService";
    public final static String SERVICE_SEX = "Services.sex";
    public final static String SERVICE_FIO_DOCTOR = "Services.fioDocter";
    public final static String SERVICE_PROF_DOCTOR = "Services.profDoctor";
    public final static String SERVICE_ATTACH = "Services.attach";
    public final static String SERVICE_MOENTITY = "Services.moEntity";
    public final static String SERVICE_ADDRESS = "Services.address";
    public final static String SERVICE_PROF = "prof";
    public final static String SERVICE_SPECIALIST = "spec";
    public final static String SERVICE_DIAGNOZ = "Services.diagnoz";
    public final static String SERVICE_SERVICE = "Services.service";
    public final static String SERVICE_KRATNOST = "Services.kratnost";

    public final static String SERVICES_NAPR_TABLE = "Service_napr";
    public final static String SERVICE_NAPR_ID = SERVICES_NAPR_TABLE + ".id";
    public final static String SERVICE_NAPR_DATE = SERVICES_NAPR_TABLE + ".dateService";
    public final static String SERVICE_NAPR_SERPOLIC =SERVICES_NAPR_TABLE + ".serPolis";
    public final static String SERVICE_NAPR_DIAGNOZ = SERVICES_NAPR_TABLE + ".diagnoz";
    public final static String SERVICE_NAPR_SERVICE = SERVICES_NAPR_TABLE + ".service";

    public final static String SEARCH_IN_SERVICES_NAPR = "SELECT id, dateService, diagnoz, service, serPolis FROM " + Const.SERVICES_NAPR_TABLE + " WHERE " + Const.SERVICE_NAPR_DATE + " >= ?";

    public final static String SEARCH_ERROR_OVER_IN_SERVICES = "SELECT idServices, dateService, diagnoz, serPolis, service, kratnost, fioDocter, moEntity, fioPathient, sex  FROM " + Const.SERVICESTABLE + " WHERE " + Const.SERVICE_DATE + " >=? AND " +
            Const.SERVICE_DATE + "<=?" + " AND (" + Const.SERVICE_SERVICE + "< 25001 OR " + Const.SERVICE_SERVICE + "> 30111) AND " + Const.SERVICE_SERVICE + "<> 1571 AND " + Const.SERVICE_SERVICE + "<> 1581 AND " + Const.SERVICE_SERVICE + "< 125001"  + " ORDER BY dateService";

    public final static String SEARCH_ERROR_VD_AND_YEAR = "SELECT idServices, dateService, diagnoz, serPolis, service, kratnost, fioDocter, attach, fioPathient, sex, dataBirday  FROM " + Const.SERVICESTABLE + " WHERE " + Const.SERVICE_DATE + " >=? AND " +
            Const.SERVICE_DATE + "<=?" + " AND " + Const.SERVICE_SERVICE + "<= 1920 AND " + Const.SERVICE_SERVICE + "> 1900";

}
