package com.Controller;

import com.CallPathient.SmsCsender;
import com.base.Const;
import com.base.DbConnect;
import com.base.SqlQuery;
import com.med218.Pathient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.awt.*;
import java.io.*;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

public class serviceController {

    @FXML
    private TableView<Pathient> tableServicesView;

    @FXML
    private TableColumn<Pathient, Integer> idColumnView;

    @FXML
    private TableColumn<Pathient, Date> dateServiceColumnView;

    @FXML
    private TableColumn<Pathient, String> profColumnView;

    @FXML
    private TableColumn<Pathient, String> specColumnView;

    @FXML
    private TableColumn<Pathient, String> diagnozColumnView;

    @FXML
    private TableColumn<Pathient, String> serviceColumnView;

    @FXML
    private TableColumn<Pathient, String> kratnostColumnView;

    @FXML
    private Text polisLabel;

    @FXML
    private Text pathientLabel;

    @FXML
    private Text birthdayLabel;

    @FXML
    private Text attachLabel;

    @FXML
    private Text addressLabel;

    @FXML
    void initialize() {
        idColumnView.setCellValueFactory(new PropertyValueFactory<Pathient, Integer>("id"));
        dateServiceColumnView.setCellValueFactory(new PropertyValueFactory<Pathient, Date>("dateService"));
        profColumnView.setCellValueFactory(new PropertyValueFactory<Pathient, String>("prof"));
        specColumnView.setCellValueFactory(new PropertyValueFactory<Pathient, String>("spec"));
        diagnozColumnView.setCellValueFactory(new PropertyValueFactory<Pathient, String>("diagnoz"));
        serviceColumnView.setCellValueFactory(new PropertyValueFactory<Pathient, String>("service"));
        kratnostColumnView.setCellValueFactory(new PropertyValueFactory<Pathient, String>("kratnost"));
    }

    private ObservableList<Pathient> pathient = FXCollections.observableArrayList();

    @FXML
    void searchServicesClick(ActionEvent event) throws SQLException {
        tableServicesView.getItems().clear();

        int id = 0;
        Date dateService;
        String prof;
        String spec;
        String diagnoz;
        int service;
        String kratnost;

        DbConnect conn = new DbConnect();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = conn.getConnection().prepareStatement(SqlQuery.SEARCHSERVICEPATHIENT.getQuery());

            String searchPolis = polisLabel.getText();
            preparedStatement.setString(1, searchPolis);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                id++;

                dateService = resultSet.getDate(Const.SERVICE_DATE);
                prof = resultSet.getString(Const.SERVICE_PROF);
                spec = resultSet.getString(Const.SERVICE_SPECIALIST);
                diagnoz = resultSet.getString(Const.SERVICE_DIAGNOZ);
                service = resultSet.getInt(Const.SERVICE_SERVICE);
                kratnost = resultSet.getString(Const.SERVICE_KRATNOST);

                pathient.add(new Pathient(id, dateService, prof, spec, diagnoz, service, kratnost));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultSet.close();
            preparedStatement.close();
            conn.closeConnection();
        }

        tableServicesView.setItems(pathient);

        System.out.println(tableServicesView.getItems().get(1).getSpec());
        System.out.println(tableServicesView.getItems().get(1).getDiagnoz());
        System.out.println(polisLabel.getText());
    }

    @FXML
    void exportExcelClick(ActionEvent event) throws IOException {
        // Examples:

		//SmsCsender sd= new Smsc();
		// or
        System.out.println(Calendar.getInstance().getTime());
        //SmsCsender sd= new SmsCsender("gp218", "987132");

		//sd.send_sms("79262368766", "пройти флюрографию", 0, "", "", 0, "Gp 218 DZM", "");
		//sd.get_sms_cost("79262368766", "Вы успешно зарегистрированы!", 0, 0, "", "");
		//sd.get_status(2, "79999999999");
       // System.out.println(sd.get_balance());


        if(!tableServicesView.getItems().isEmpty()) {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Employees sheet");

            Row row = sheet.createRow(1);
            Cell cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Пациент");
            sheet.setColumnWidth(1, 8000);

            row = sheet.createRow(3);
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Медицинская организация:");
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("ГБУЗ ГП 218 ДЗМ");

            row = sheet.createRow(4);
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("ФИО:");
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue(pathientLabel.getText());

            row = sheet.createRow(5);
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Адрес постоянной регистрации: ");
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue(addressLabel.getText());

            FileOutputStream outFile = new FileOutputStream("C:/Отчеты/Сортировка по цвету_" + 1 + ".xls");
            workbook.write(outFile);
            System.out.println("Created file: ");

            workbook.close();
            outFile.close();

            Desktop.getDesktop().open(new File("C:/Отчеты/Сортировка по цвету_" + 1 + ".xls"));
        }
    }

    public void setPathientLabel (String polis, String fio, Date birthday, String attach, String address) {
        polisLabel.setText(polis);
        pathientLabel.setText(fio);
        birthdayLabel.setText(birthday.toString());
        attachLabel.setText(attach);
        addressLabel.setText(address);
    }
}
