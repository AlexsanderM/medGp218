package com.fileOffice;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ExcelRead {

    private FileEx file;

    private String data;
    private String mo;
    private String address;
    private String attach;
    private String dataBirday;
    private String medPolis;
    private String sex;
    private String fioPathient;
    private String codDocter;
    private String depDocter;
    private String fioDocter;
    private String profDoctor;
    private String codMeds;
    private String depMeds;
    private String fioMeds;
    private String profMeds;
    private String sum;
    private String kratnost;
    private String service;
    private String oc_sl;
    private String delet;
    private String diagnoz;
    private String source;
    private String obrash;
    private String zabolev;
    private String identifier;
    private String insuranceName;
    private String sumError;
    private String typeDiagnoz;
    private String numberService;
    private String nameService;
    private String operator;
    private String error;

    public String getService_q() {
        return service_q;
    }

    public String getSpecial_case() {
        return special_case;
    }


    public String getVisit_result() {
        return visit_result;
    }

    public String getIllness_outcome() {
        return illness_outcome;
    }

    public String getForwardMO() {
        return forwardMO;
    }


    public String getForwardDate() {
        return forwardDate;
    }

    private String service_q;

    private String special_case;

    private String visit_result;

    private String illness_outcome;

    private String forwardMO;

    private String forwardDate;

    public String getSerNumberPolis() {
        return serNumberPolis;
    }

    private String serNumberPolis;

    public String getNumberPolis() {
        return NumberPolis;
    }

    private String NumberPolis;


    public ExcelRead(FileEx file) {
        this.file = file;
    }

    public ExcelRead(String data, String diagnoz, String service, String service_q, String special_case, String visit_result, String illness_outcome,
                     String codDocter, String codMeds, String forwardMO, String forwardDate, String serNumberPolis, String NumberPolis ) {

        this.data = data;
        this.diagnoz = diagnoz;
        this.service = service;
        this.service_q = service_q;
        this.special_case = special_case;
        this.visit_result = visit_result;
        this.illness_outcome = illness_outcome;
        this.codDocter = codDocter;
        this.codMeds = codMeds;
        this.forwardMO = forwardMO;
        this.forwardDate = forwardDate;
        this.serNumberPolis = serNumberPolis;
        this.NumberPolis = NumberPolis;
    }

    public ExcelRead(String identifier, String data, String codDocter, String fioDocter, String addres, String attach, String dataBirday,
                     String fioPathient, String medPolis, String insuranceName, String codMeds, String fioMeds, String sum, String sumError,
                     String service, String diagnoz, String typeDiagnoz, String numberService, String nameService, String kratnost,
                     String source, String operator, String error) {

        this.identifier = identifier;
        this.data = data;
        this.codDocter = codDocter;
        this.fioDocter = fioDocter;
        this.address = addres;
        this.attach = attach;
        this.dataBirday = dataBirday;
        this.fioPathient = fioPathient;
        this.medPolis = medPolis;
        this.insuranceName = insuranceName;
        this.codMeds = codMeds;
        this.fioMeds = fioMeds;
        this.sum = sum;
        this.sumError = sumError;
        this.service = service;
        this.diagnoz = diagnoz;
        this.typeDiagnoz = typeDiagnoz;
        this.numberService = numberService;
        this.nameService = nameService;
        this.kratnost = kratnost;
        this.source = source;
        this.operator = operator;
        this.error = error;
    }

    public ExcelRead(String data, String mo, String addres, String attach, String dataBirday, String medPolis, String sex,
                     String fioPathient, String codDocter, String depDocter, String fioDocter, String profDoctor, String codMeds,
                     String depMeds, String fioMeds, String profMeds, String sum, String kratnost, String service, String oc_sl,
                     String delet, String diagnoz, String source, String obrash, String zabolev) {

        this.data = data;
        this.mo = mo;
        this.address = addres;
        this.attach = attach;
        this.dataBirday = dataBirday;
        this.medPolis = medPolis;
        this.sex = sex;
        this.fioPathient = fioPathient;
        this.codDocter = codDocter;
        this.depDocter = depDocter;
        this.fioDocter = fioDocter;
        this.profDoctor = profDoctor;
        this.codMeds = codMeds;
        this.depMeds = depMeds;
        this.fioMeds = fioMeds;
        this.profMeds = profMeds;
        this.sum = sum;
        this.kratnost = kratnost;
        this.service = service;
        this.oc_sl = oc_sl;
        this.delet = delet;
        this.diagnoz = diagnoz;
        this.source = source;
        this.obrash = obrash;
        this.zabolev = zabolev;
    }

    public ArrayList<String> getNameColumn(String...names) {

        ArrayList<String> nameColumn = new ArrayList<>();

        for (String name : names) {
            nameColumn.add(name);
        }

        return nameColumn;
    }

    public ArrayList<String> getStandNameColumn(TableView<ExcelRead> t, int i) {

        ArrayList<String> nameColumn = new ArrayList<>();

        nameColumn.add(t.getItems().get(i).data);
        nameColumn.add(t.getItems().get(i).codDocter);
        nameColumn.add(t.getItems().get(i).fioDocter);
        nameColumn.add(t.getItems().get(i).dataBirday);
        nameColumn.add(t.getItems().get(i).medPolis);
        nameColumn.add(t.getItems().get(i).fioPathient);
        nameColumn.add(t.getItems().get(i).sex);
        nameColumn.add(t.getItems().get(i).diagnoz);
        nameColumn.add(t.getItems().get(i).service);
        nameColumn.add(t.getItems().get(i).sum);

        return nameColumn;
    }

    public  TableView<ExcelRead> getTableAll(ObservableList<ExcelRead> list, int countSheet) throws IOException{
        Iterator<Row> rowIterator = null;
        rowIterator = file.getSheet(countSheet).iterator();

        rowIterator.next();
        rowIterator.next();

        String c0, c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21, c22, c23, c24;

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            c0 = getCell(0, row.getCell(0), row);
            c1 = getCell(1, row.getCell(1), row);
            c2 = getCell(2, row.getCell(2), row);
            c3 = getCell(3, row.getCell(3), row);
            c4 = getCell(4, row.getCell(4), row);
            c5 = getCell(5, row.getCell(5), row);
            c6 = getCell(6, row.getCell(6), row);
            c7 = getCell(7, row.getCell(7), row);
            c8 = getCell(8, row.getCell(8), row);
            c9 = getCell(9, row.getCell(9), row);
            c10 = getCell(10, row.getCell(10), row);
            c11 = getCell(11, row.getCell(11), row);
            c12 = getCell(12, row.getCell(12), row);
            c13 = getCell(13, row.getCell(13), row);
            c14 = getCell(14, row.getCell(14), row);
            c15 = getCell(15, row.getCell(15), row);
            c16 = getCell(16, row.getCell(16), row);
            c17 = getCell(17, row.getCell(17), row);
            c18 = getCell(18, row.getCell(18), row);
            c19 = getCell(19, row.getCell(19), row);
            c20 = getCell(20, row.getCell(20), row);
            c21 = getCell(21, row.getCell(21), row);
            c22 = getCell(22, row.getCell(22), row);
            c23 = getCell(23, row.getCell(23), row);
            c24 = getCell(24, row.getCell(24), row);

            list.add(new ExcelRead( c0, c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21, c22, c23, c24));

        }
        TableView<ExcelRead> table = new TableView<>(list);

        table.setPrefWidth(700);
        table.setPrefHeight(450);

        getColumn("Дата", "data", table);
        getColumn("МО", "mo", table);
        getColumn("Адрес", "address", table);
        getColumn("Прикрепление", "attach", table);
        getColumn("Дата Рождение", "dataBirday", table);
        getColumn("Полис", "medPolis", table);
        getColumn("Пол", "sex", table);
        getColumn("ФИО Пациента", "fioPathient", table);
        getColumn("код Врача", "codDocter", table);
        getColumn("отдел Врача", "depDocter" , table);
        getColumn("ФИО Врача", "fioDocter" , table);
        getColumn("Должность", "profDoctor" , table);
        getColumn("код Медсестры", "codMeds" , table);
        getColumn("отдел Медсестры", "depMeds" , table);
        getColumn("ФИО Медсестры", "fioMeds" , table);
        getColumn("Должность Медсестры", "profMeds" , table);
        getColumn("Сумма", "sum" , table);
        getColumn("кратность", "kratnost" , table);
        getColumn("Услуги", "service" , table);
        getColumn("особый слачай", "oc_sl" , table);
        getColumn("Иск.", "delet" , table);
        getColumn("Диагноз", "diagnoz" , table);
        getColumn("Источник", "source" , table);
        getColumn("Обращ.", "obrash" , table);
        getColumn("Заболев.", "zabolev" , table);

        return table;
    }

    public  TableView<ExcelRead> getTableAllInsurance(ObservableList<ExcelRead> list, int countSheet) throws IOException{
        Iterator<Row> rowIterator = null;
        rowIterator = file.getSheet(countSheet).iterator();

        rowIterator.next();
        rowIterator.next();

        String c0, c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21, c22;

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            c0 = getCell(0, row.getCell(0), row);
            c1 = getCell(1, row.getCell(1), row);
            c2 = getCell(2, row.getCell(2), row);
            c3 = getCell(3, row.getCell(3), row);
            c4 = getCell(4, row.getCell(4), row);
            c5 = getCell(5, row.getCell(5), row);
            c6 = getCell(6, row.getCell(6), row);
            c7 = getCell(7, row.getCell(7), row);
            c8 = getCell(8, row.getCell(8), row);
            c9 = getCell(9, row.getCell(9), row);
            c10 = getCell(10, row.getCell(10), row);
            c11 = getCell(11, row.getCell(11), row);
            c12 = getCell(12, row.getCell(12), row);
            c13 = getCell(13, row.getCell(13), row);
            c14 = getCell(14, row.getCell(14), row);
            c15 = getCell(15, row.getCell(15), row);
            c16 = getCell(16, row.getCell(16), row);
            c17 = getCell(17, row.getCell(17), row);
            c18 = getCell(18, row.getCell(18), row);
            c19 = getCell(19, row.getCell(19), row);
            c20 = getCell(20, row.getCell(20), row);
            c21 = getCell(21, row.getCell(21), row);
            c22 = getCell(22, row.getCell(22), row);

            list.add(new ExcelRead( c0, c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21, c22));

        }
        TableView<ExcelRead> table = new TableView<>(list);

        table.setPrefWidth(700);
        table.setPrefHeight(450);

        getColumn("Индефикатор", "identifier", table);
        getColumn("Дата", "data", table);
        getColumn("код Врача", "codDocter", table);
        getColumn("ФИО Врача", "fioDocter" , table);
        getColumn("Адрес", "address", table);
        getColumn("Прикрепление", "attach", table);
        getColumn("Дата Рождение", "dataBirday", table);
        getColumn("ФИО Пациента", "fioPathient", table);
        getColumn("Полис", "medPolis", table);
        getColumn("Страховая", "insuranceName", table);
        getColumn("код Медсестры", "codMeds" , table);
        getColumn("ФИО Медсестры", "fioMeds" , table);
        getColumn("Сумма", "sum" , table);
        getColumn("Сумма Ошибок", "sumError" , table);
        getColumn("Услуги", "service" , table);
        getColumn("Диагноз", "diagnoz" , table);
        getColumn("Тип Диагноза", "typeDiagnoz" , table);
        getColumn("id Услуги", "numberService" , table);
        getColumn("Назавание услуги", "nameService" , table);
        getColumn("Кратность", "kratnost" , table);
        getColumn("Источник", "source" , table);
        getColumn("Оператор", "operator" , table);
        getColumn("Ошибка", "error" , table);

        return table;
    }

    public  TableView<ExcelRead> getTableAllNapr(ObservableList<ExcelRead> list, int countSheet) throws IOException{
        Iterator<Row> rowIterator = null;
        rowIterator = file.getSheet(countSheet).iterator();

        rowIterator.next();
        rowIterator.next();

        String c0, c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21, c22;

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            c0 = getCell(0, row.getCell(0), row);
            c1 = getCell(1, row.getCell(1), row);
            c2 = getCell(2, row.getCell(2), row);
            c3 = getCell(3, row.getCell(3), row);
            c4 = getCell(4, row.getCell(4), row);
            c5 = getCell(5, row.getCell(5), row);
            c6 = getCell(6, row.getCell(6), row);
            c7 = getCell(7, row.getCell(7), row);
            c8 = getCell(8, row.getCell(8), row);
            c9 = getCell(9, row.getCell(9), row);
            c10 = getCell(10, row.getCell(10), row);
            c11 = getCell(11, row.getCell(11), row);
            c12 = getCell(12, row.getCell(12), row);


            list.add(new ExcelRead( c0, c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12));

        }
        TableView<ExcelRead> table = new TableView<>(list);

        table.setPrefWidth(700);
        table.setPrefHeight(450);

        getColumn("Дата", "data", table);
        getColumn("Диагноз", "diagnoz", table);
        getColumn("услуга", "serviceString", table);
        getColumn("service_q", "service_q" , table);
        getColumn("special_case", "special_case", table);
        getColumn("visit_result", "visit_result", table);
        getColumn("illness_outcome", "illness_outcome", table);
        getColumn("codDocter", "codDocter", table);
        getColumn("codMeds", "codMeds", table);
        getColumn("forwardMO", "forwardMO", table);
        getColumn("forwardDate", "forwardDate" , table);
        getColumn("serNumberPolis", "serNumberPolis" , table);
        getColumn("NumberPolis", "NumberPolis" , table);


        return table;
    }


    private void getColumn(String nameColum, String nameFactory, TableView<ExcelRead> table) {
        TableColumn<ExcelRead, String> dataColumn = new TableColumn<>(nameColum);
        dataColumn.setCellValueFactory(new PropertyValueFactory<>(nameFactory));
        table.getColumns().add(dataColumn);
    }

    private String getCell(int index, Cell c, Row row) {
        String str;

        if (c == null) {
            str = "";
        } else {
            str =   row.getCell(index, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL).toString();
        }

        return str;
    }

    public String getData (){ return data;}

    public String getMo (){ return mo;}

    public String getAddress(){ return address;}

    public String getAttach (){ return attach;}

    public String getDataBirday (){ return dataBirday;}

    public String getMedPolis (){ return medPolis;}

    public String getSex() { return sex; }

    public String getFioPathient() { return fioPathient; }

    public String getCodDocter() { return codDocter; }

    public String getDepDocter() { return depDocter; }

    public String getFioDocter() { return fioDocter; }

    public String getProfDoctor() { return profDoctor; }

    public String getCodMeds() { return codMeds; }

    public String getDepMeds() { return depMeds; }

    public String getFioMeds() { return fioMeds; }

    public String getProfMeds() { return profMeds; }

    public String getSum() { return sum; }

    public String getKratnost() { return kratnost; }

    public String getService() { return service; }

    public String getOc_sl() { return oc_sl; }

    public String getDelet() { return delet; }

    public String getDiagnoz() { return diagnoz; }

    public String getSource() { return source; }

    public String getObrash() { return obrash; }

    public String getZabolev() { return zabolev; }

    public String getInsuranceName() { return insuranceName; }

    public String getSumError() { return sumError; }

    public String getTypeDiagnoz() { return typeDiagnoz; }

    public String getNumberService() { return numberService; }

    public String getNameService() { return nameService; }

    public String getOperator() { return operator; }

    public String getError() { return error; }
}
