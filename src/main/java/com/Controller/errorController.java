package com.Controller;

import com.base.Const;
import com.base.DbConnect;
import com.base.SqlQuery;
import com.med218.Pathient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class errorController {

    @FXML
    private TableView<Pathient> tableServicesView;

    @FXML
    private TableColumn<Pathient, Integer> idColumnView;

    @FXML
    private TableColumn<Pathient, Date> dateServiceColumnView;

    @FXML
    private TableColumn<Pathient, String> docterColumnView;

    @FXML
    private TableColumn<Pathient, String> attachColumnView;

    @FXML
    private TableColumn<Pathient, String> serPolisColumnView1;

    @FXML
    private TableColumn<Pathient, String> fioPathientColumnView;

    @FXML
    private TableColumn<Pathient, String> diagnozColumnView;

    @FXML
    private TableColumn<Pathient, String> serviceColumnView;

    @FXML
    private TableColumn<Pathient, String> kratnostColumnView;

    @FXML
    private TableColumn<Pathient, String> sexColumnView;
    @FXML
    private TableColumn<Pathient, String> commentColumnView;

    @FXML
    private Button buttonOverService;

    @FXML
    private Button buttonExportExcel;

    @FXML
    private Button buttonVDErrorYear;

    @FXML
    private DatePicker withDatePicker;

    @FXML
    private DatePicker toDatePicker;

    @FXML
    void initialize() {
        idColumnView.setCellValueFactory(new PropertyValueFactory<Pathient, Integer>("id"));
        dateServiceColumnView.setCellValueFactory(new PropertyValueFactory<Pathient, Date>("dateService"));
        docterColumnView.setCellValueFactory(new PropertyValueFactory<Pathient, String>("fioDocter"));
        attachColumnView.setCellValueFactory(new PropertyValueFactory<Pathient, String>("attach"));
        serPolisColumnView1.setCellValueFactory(new PropertyValueFactory<Pathient, String>("polisPathient"));
        fioPathientColumnView.setCellValueFactory(new PropertyValueFactory<Pathient, String>("fioPathient"));
        diagnozColumnView.setCellValueFactory(new PropertyValueFactory<Pathient, String>("diagnoz"));
        sexColumnView.setCellValueFactory(new PropertyValueFactory<Pathient, String>("sex"));
        kratnostColumnView.setCellValueFactory(new PropertyValueFactory<Pathient, String>("kratnost"));
        serviceColumnView.setCellValueFactory(new PropertyValueFactory<Pathient, String>("service"));
        commentColumnView.setCellValueFactory(new PropertyValueFactory<Pathient, String>("comment"));
    }

    private ObservableList<Pathient> pathient = FXCollections.observableArrayList();

    @FXML
    void clickOverService(ActionEvent event) throws SQLException {
        tableServicesView.getItems().clear();

        int id = 0;

        ArrayList idList = new ArrayList<Integer>();
        ArrayList<Date> dateServiceList = new ArrayList<Date>();
        ArrayList<String> docterList = new ArrayList<String>();
        ArrayList<String> moEntity = new ArrayList<String>();
        ArrayList<String> serPolisList = new ArrayList<String>();
        ArrayList<String> fioPathientList = new ArrayList<String>();
        ArrayList<String> diagnozList = new ArrayList<String>();
        ArrayList<String> sexList = new ArrayList<String>();
        ArrayList<Integer> serviceList = new ArrayList<Integer>();
        ArrayList<String> kratnostList = new ArrayList<String>();
        ArrayList<String> commentList = new ArrayList<String>();
        ArrayList<Boolean> flagList = new ArrayList<Boolean>();

        DbConnect conn = new DbConnect();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = conn.getConnection().prepareStatement(Const.SEARCH_ERROR_OVER_IN_SERVICES);

            String withDate = withDatePicker.getValue().toString();
            String toDate = toDatePicker.getValue().toString();

            preparedStatement.setString(1, withDate);
            preparedStatement.setString(2, toDate);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                id++;
                //idList.add(id);

                dateServiceList.add(resultSet.getDate(Const.SERVICE_DATE));
                docterList.add(resultSet.getString(Const.SERVICE_FIO_DOCTOR));
                moEntity.add(resultSet.getString(Const.SERVICE_MOENTITY));
                serPolisList.add(resultSet.getString(Const.SERVICE_SERPOLIC));
                fioPathientList.add(resultSet.getString(Const.SERVICE_FIOPATHIENT));
                diagnozList.add(resultSet.getString(Const.SERVICE_DIAGNOZ));
                sexList.add(resultSet.getString(Const.SERVICE_SEX));
                serviceList.add(resultSet.getInt(Const.SERVICE_SERVICE));
                kratnostList.add(resultSet.getString(Const.SERVICE_KRATNOST));
                flagList.add(true);
                commentList.add("");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultSet.close();
            preparedStatement.close();
            conn.closeConnection();
        }

        int lengthRow = dateServiceList.size();

        for (int i = 0; i < lengthRow; i++) {
            for (int y = i + 1; y < lengthRow; y++) {
                if (y != lengthRow) {
                    if (serviceList.get(i).equals(serviceList.get(y)) && serPolisList.get(i).equals(serPolisList.get(y)) && flagList.get(i)) {//

                        if (dateServiceList.get(i).equals(dateServiceList.get(y))) {
                            pathient.add(new Pathient(id, dateServiceList.get(i), docterList.get(i), moEntity.get(i), serPolisList.get(i),
                                    fioPathientList.get(i), diagnozList.get(i), sexList.get(i), serviceList.get(i), kratnostList.get(i), ""));

                            pathient.add(new Pathient(id, dateServiceList.get(y), docterList.get(y), moEntity.get(y), serPolisList.get(y),
                                    fioPathientList.get(y), diagnozList.get(y), sexList.get(y), serviceList.get(y), kratnostList.get(y), "Исключить"));

                            flagList.set(y, false);
                        } else {
                            if (serviceList.get(i).intValue() % 2 != 0 && diagnozList.get(i).equals(diagnozList.get(y))) {
                                if (serviceList.get(i).intValue() == 1001 || serviceList.get(i).intValue() == 1011 || serviceList.get(i).intValue() == 1071 ||
                                        serviceList.get(i).intValue() == 1081 || serviceList.get(i).intValue() == 1141 || serviceList.get(i).intValue() == 1161 ||
                                        serviceList.get(i).intValue() == 1191 || serviceList.get(i).intValue() == 1261 || serviceList.get(i).intValue() == 1271 ||
                                        serviceList.get(i).intValue() == 1301 || serviceList.get(i).intValue() == 1371 || serviceList.get(i).intValue() == 1801 ||
                                        serviceList.get(i).intValue() == 1013) {

                                    pathient.add(new Pathient(id, dateServiceList.get(i), docterList.get(i), moEntity.get(i), serPolisList.get(i),
                                            fioPathientList.get(i), diagnozList.get(i), sexList.get(i), serviceList.get(i), kratnostList.get(i), ""));

                                    pathient.add(new Pathient(id, dateServiceList.get(y), docterList.get(y), moEntity.get(y), serPolisList.get(y),
                                            fioPathientList.get(y), diagnozList.get(y), sexList.get(y), serviceList.get(y), kratnostList.get(y), Integer.toString(serviceList.get(i).intValue() + 1)));
                                }
                            }
                        }
                    }
                }
            }
        }

        tableServicesView.setItems(pathient);
        System.out.println("Ready");
    }

    @FXML
    void clickVDErrorYear(ActionEvent event) throws SQLException {
        tableServicesView.getItems().clear();

        int id = 0;

        ArrayList idList = new ArrayList<Integer>();
        ArrayList<Date> dateServiceList = new ArrayList<Date>();
        ArrayList<String> docterList = new ArrayList<String>();
        ArrayList<String> attachList = new ArrayList<String>();
        ArrayList<String> serPolisList = new ArrayList<String>();
        ArrayList<String> fioPathientList = new ArrayList<String>();
        ArrayList<Date> dataBirPathientList = new ArrayList<Date>();
        ArrayList<String> diagnozList = new ArrayList<String>();
        ArrayList<String> sexList = new ArrayList<String>();
        ArrayList<Integer> serviceList = new ArrayList<Integer>();
        ArrayList<String> kratnostList = new ArrayList<String>();
        ArrayList<String> commentList = new ArrayList<String>();
        ArrayList<Boolean> flagList = new ArrayList<Boolean>();

        int vdYearArray[] = new int[27];
        int poYearArray[] = new int[27];
        int c3YearArray[] = new int[27];

        int vd = 1998;
        int po = 1997;
        int c3 = 1996;

        for (int i = 0; i < vdYearArray.length; i++) {
            vdYearArray[i] = vd;
            poYearArray[i] = po;
            c3YearArray[i] = c3;

            vd -= 3;
            po -= 3;
            c3 -= 3;
        }

        DbConnect conn = new DbConnect();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = conn.getConnection().prepareStatement(Const.SEARCH_ERROR_VD_AND_YEAR);

            String withDate = withDatePicker.getValue().toString();
            String toDate = toDatePicker.getValue().toString();

            preparedStatement.setString(1, withDate);
            preparedStatement.setString(2, toDate);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                id++;
                //idList.add(id);

                dateServiceList.add(resultSet.getDate(Const.SERVICE_DATE));
                docterList.add(resultSet.getString(Const.SERVICE_FIO_DOCTOR));
                attachList.add(resultSet.getString(Const.SERVICE_ATTACH));
                serPolisList.add(resultSet.getString(Const.SERVICE_SERPOLIC));
                fioPathientList.add(resultSet.getString(Const.SERVICE_FIOPATHIENT));
                dataBirPathientList.add(resultSet.getDate(Const.SERVICE_BIRTHPATHIENT));
                diagnozList.add(resultSet.getString(Const.SERVICE_DIAGNOZ));
                sexList.add(resultSet.getString(Const.SERVICE_SEX));
                serviceList.add(resultSet.getInt(Const.SERVICE_SERVICE));
                kratnostList.add(resultSet.getString(Const.SERVICE_KRATNOST));
                flagList.add(true);
                commentList.add("");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultSet.close();
            preparedStatement.close();
            conn.closeConnection();
        }

        int lengthRows = dateServiceList.size();

        for (int i = 0; i < lengthRows; i++) {
            int yearBir = dataBirPathientList.get(i).toLocalDate().getYear();
            boolean flag = false;

            //boolean flag = isFlag(serviceList.get(i).intValue(), 1906, sexList, "Ж", pathient, "1906");

            if (serviceList.get(i).intValue() != 1906 && sexList.get(i).equals("Ж")) {
                for (int j = 0; j <= 5; j++) {
                    if (yearBir == poYearArray[j]) {
                        flag = true;
                    }
                }
                if (flag) {
                    pathient.add(new Pathient(id, dateServiceList.get(i), docterList.get(i), attachList.get(i), serPolisList.get(i),
                            fioPathientList.get(i), diagnozList.get(i), sexList.get(i), serviceList.get(i), kratnostList.get(i), "1906"));
                    flag = false;
                }
            }

            if (serviceList.get(i).intValue() != 1907 && sexList.get(i).equals("Ж")) {
                for (int j = 6; j < poYearArray.length; j++) {
                    if (yearBir == poYearArray[j]) {
                        flag = true;
                    }
                }
                if (flag) {
                    pathient.add(new Pathient(id, dateServiceList.get(i), docterList.get(i), attachList.get(i), serPolisList.get(i),
                            fioPathientList.get(i), diagnozList.get(i), sexList.get(i), serviceList.get(i), kratnostList.get(i), "1907"));
                    flag = false;
                }
            }


            if (serviceList.get(i).intValue() != 1909 && sexList.get(i).equals("М")) {
                for (int j = 0; j < 7; j++) {
                    if (yearBir == poYearArray[j]) {
                        flag = true;
                    }
                }
                if (flag) {
                    pathient.add(new Pathient(id, dateServiceList.get(i), docterList.get(i), attachList.get(i), serPolisList.get(i),
                            fioPathientList.get(i), diagnozList.get(i), sexList.get(i), serviceList.get(i), kratnostList.get(i), "1909"));
                    flag = false;
                }
            }

            if (serviceList.get(i).intValue() != 1908 && sexList.get(i).equals("М")) {
                for (int j = 8; j < poYearArray.length; j++) {
                    if (yearBir == poYearArray[j]) {
                        flag = true;
                    }
                }
                if (flag) {
                    pathient.add(new Pathient(id, dateServiceList.get(i), docterList.get(i), attachList.get(i), serPolisList.get(i),
                            fioPathientList.get(i), diagnozList.get(i), sexList.get(i), serviceList.get(i), kratnostList.get(i), "1908"));
                    flag = false;
                }
            }




            if (serviceList.get(i).intValue() == 1908) {
                for (int j = 8; j < poYearArray.length; j++) {
                    if (poYearArray[j] == yearBir) {
                        break;
                    }
                }
                //System.out.println(serviceList.get(i).intValue() + " " + yearBir);
            } else {

            }
        }

        tableServicesView.setItems(pathient);
        System.out.println("Ready");
    }

    @FXML
    void clickExportEcel(ActionEvent event) throws IOException {
        if (!tableServicesView.getItems().isEmpty()) {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Employees sheet");

            int rowSize = 0;

            Row row = sheet.createRow(rowSize);
            Cell cell;
            sheet.setColumnWidth(0, 4000);

            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("id:");
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Date:");
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Docter");
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Attach");
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("SerPolis");
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("FioPathient");
            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("Diagnoz");
            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue("Sex");
            cell = row.createCell(8, CellType.STRING);
            cell.setCellValue("Kratnost");
            cell = row.createCell(9, CellType.STRING);
            cell.setCellValue("Service");
            cell = row.createCell(10, CellType.STRING);
            cell.setCellValue("Comment");

            for (int i = 0; i < tableServicesView.getItems().size(); i++) {
                rowSize++;
                row = sheet.createRow(rowSize);

                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue(tableServicesView.getItems().get(i).getId());
                cell = row.createCell(1, CellType.NUMERIC);
                cell.setCellValue(tableServicesView.getItems().get(i).getDateService());
                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue(tableServicesView.getItems().get(i).getFioDocter());
                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue(tableServicesView.getItems().get(i).getAttach());
                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue(tableServicesView.getItems().get(i).getPolisPathient());
                cell = row.createCell(5, CellType.STRING);
                cell.setCellValue(tableServicesView.getItems().get(i).getFioPathient());
                cell = row.createCell(6, CellType.STRING);
                cell.setCellValue(tableServicesView.getItems().get(i).getDiagnoz());
                cell = row.createCell(7, CellType.STRING);
                cell.setCellValue(tableServicesView.getItems().get(i).getSex());
                cell = row.createCell(8, CellType.STRING);
                cell.setCellValue(tableServicesView.getItems().get(i).getKratnost());
                cell = row.createCell(9, CellType.STRING);
                cell.setCellValue(tableServicesView.getItems().get(i).getService());
                cell = row.createCell(10, CellType.STRING);
                cell.setCellValue(tableServicesView.getItems().get(i).getComment());
            }

            FileOutputStream outFile = new FileOutputStream("C:/Отчеты/превышение кратности_" + 1 + ".xls");
            workbook.write(outFile);
            System.out.println("Created file: ");

            workbook.close();
            outFile.close();

            Desktop.getDesktop().open(new File("C:/Отчеты/превышение кратности_" + 1 + ".xls"));
        }
    }
}
