package com.Controller;

import com.base.Const;
import com.base.DbConnect;
import com.base.SqlQuery;
import com.fileOffice.ExcelRead;
import com.fileOffice.FileEx;
import com.fileOffice.FileExcelXls;
import com.med218.Pathient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class mainController {

    @FXML
    private TableView<Pathient> tablePathientsView;

    @FXML
    private TableColumn<Pathient, Integer> idColumnView;

    @FXML
    private TableColumn<Pathient, String> fioColumnView;

    @FXML
    private TableColumn<Pathient, String> polisColumnView;

    @FXML
    private TableColumn<Pathient, Date> dataBirthColumnView;

    @FXML
    private TableColumn<Pathient, String> attachColumnView;

    @FXML
    private TableColumn<Pathient, String> addressColumnView;

    @FXML
    private Button buttonImport;

    @FXML
    private Button buttonImportInsurance;

    @FXML
    private Button buttonImportNapr;

    @FXML
    private Button buttonSearch;

    @FXML
    private Button buttonError;

   @FXML
    private ComboBox<String> comBoxMedOrgan;

    @FXML
    private TextField textPathFile;

    @FXML
    private TextField textSearchPolis;

    @FXML
    private Text userLabel;

    private ObservableList<Pathient> pathient = FXCollections.observableArrayList();

    public void initialize() {
        idColumnView.setCellValueFactory(new PropertyValueFactory<Pathient, Integer>("id"));
        polisColumnView.setCellValueFactory(new PropertyValueFactory<Pathient, String>("polisPathient"));
        fioColumnView.setCellValueFactory(new PropertyValueFactory<Pathient, String>("fioPathient"));
        dataBirthColumnView.setCellValueFactory(new PropertyValueFactory<Pathient, Date>("dateBirdayPathient"));
        attachColumnView.setCellValueFactory(new PropertyValueFactory<Pathient, String>("attach"));
        addressColumnView.setCellValueFactory(new PropertyValueFactory<Pathient, String>("address"));

        comBoxMedOrgan.getItems().addAll("ГП 218", "филиал 1", "филиал 2", "филиал 3", "филиал 4", "филиал 5");

        textSearchPolis.addEventHandler(KeyEvent.KEY_PRESSED,new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    try {
                        searchPolisClick();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }


    public void importClick(ActionEvent actionEvent) throws SQLException, IOException, ParseException, InterruptedException {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open");
        File selectedFile = chooser.showOpenDialog(new Stage());

        System.out.println(selectedFile.getAbsolutePath());
        textPathFile.setText(selectedFile.getAbsolutePath());

        Calendar calendarService;
        Calendar calendarBirthday;
        Calendar calendarDateNapr;
        SimpleDateFormat formatDate = new SimpleDateFormat("dd.MM.yyyy");

        PreparedStatement preparedStatement = null;
        DbConnect conn = new DbConnect();
        ResultSet resultSet = null;

        int countSheet = new FileExcelXls(selectedFile.getAbsolutePath()).getSheetCount();

        for (int numberSheet = 0; numberSheet < countSheet; numberSheet++) {
            FileEx file = new FileExcelXls(selectedFile.getAbsolutePath());
            ExcelRead excelRead = new ExcelRead(file);

            ObservableList<ExcelRead> list = FXCollections.observableArrayList();
            TableView<ExcelRead> table = excelRead.getTableAll(list, numberSheet);

            for (int i = 0; i < table.getItems().size(); i++) {
                String date = table.getItems().get(i).getData();
                String mo = comBoxMedOrgan.getValue(); // "филиал 1";//table.getItems().get(i).getMo(); // ГП 218   филиал 5
                String address = table.getItems().get(i).getAddress();
                String attach = table.getItems().get(i).getAttach();
                String dataBirday = table.getItems().get(i).getDataBirday();
                String itemPolis = table.getItems().get(i).getMedPolis();
                String sex = table.getItems().get(i).getSex();
                String fioPathient = table.getItems().get(i).getFioPathient();
                String codDocter = table.getItems().get(i).getCodDocter();
                String depDocter = table.getItems().get(i).getDepDocter();
                String fioDocter = table.getItems().get(i).getFioDocter();
                String codMeds = table.getItems().get(i).getCodMeds();
                String depMeds = table.getItems().get(i).getDepMeds();
                String fioMeds = table.getItems().get(i).getFioMeds();
                String profDoctor = table.getItems().get(i).getProfDoctor();
                String profMeds = table.getItems().get(i).getProfMeds();
                Double sum = Double.parseDouble(table.getItems().get(i).getSum());
                String kratnost = table.getItems().get(i).getKratnost();
                int service = Integer.parseInt(table.getItems().get(i).getService());
                int oc_sl = Integer.parseInt(table.getItems().get(i).getOc_sl());
                String delet = table.getItems().get(i).getDelet();
                String diagnoz = table.getItems().get(i).getDiagnoz();
                String source = table.getItems().get(i).getSource();
                String obrash = table.getItems().get(i).getObrash();
                String zabolev = table.getItems().get(i).getZabolev();

                calendarService = Calendar.getInstance();
                calendarService.setTime(formatDate.parse(date));
                calendarService.add(Calendar.HOUR, 5);
                calendarBirthday = Calendar.getInstance();
                calendarBirthday.setTime(formatDate.parse(dataBirday));
                calendarBirthday.add(Calendar.HOUR, 5);

                Date dateService = new Date(calendarService.getTimeInMillis());
                Date dateBirthday = new Date(calendarBirthday.getTimeInMillis());

                if (numberSheet == 0 && i == 0) {
                    ArrayList<Integer> idList = new ArrayList();
                    ArrayList<Date> dateList = new ArrayList();
                    ArrayList<String> serpolisList = new ArrayList();
                    ArrayList<String> diagnozList = new ArrayList();
                    ArrayList<Integer> serviceList = new ArrayList();

                    try {
                        preparedStatement = conn.getConnection().prepareStatement(Const.SEARCH_IN_SERVICES_NAPR);

                        preparedStatement.setDate(1, dateService);

                        resultSet = preparedStatement.executeQuery();

                        for(int k = 0 ;resultSet.next(); k++) {
                            int id = resultSet.getInt(Const.SERVICE_NAPR_ID);
                            Date dateNapr = resultSet.getDate(Const.SERVICE_NAPR_DATE);
                            String serpolisNapr = resultSet.getString(Const.SERVICE_NAPR_SERPOLIC);
                            String diagnozNapr = resultSet.getString(Const.SERVICE_NAPR_DIAGNOZ);
                            int serviceNapr = resultSet.getInt(Const.SERVICE_NAPR_SERVICE);

                            idList.add(id);
                            dateList.add(dateNapr);
                            serpolisList.add(serpolisNapr);
                            diagnozList.add(diagnozNapr);
                            serviceList.add(serviceNapr);
                           // System.out.println(idList.get(k) + " " + dateList.get(k) + " " + serpolisList.get(k) + " " + diagnozList.get(k) + " " + serviceList.get(k));
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    } finally {
                        resultSet.close();
                        preparedStatement.close();
                        conn.closeConnection();
                    }
                }

                preparedStatement = null;
                conn = new DbConnect();

                try {
                    preparedStatement = conn.getConnection().prepareStatement(SqlQuery.IMPORTSERVICE.getQuery());

                    preparedStatement.setDate(1, dateService);
                    preparedStatement.setString(2, mo);
                    preparedStatement.setString(3, address);
                    preparedStatement.setString(4, attach);
                    preparedStatement.setDate(5, dateBirthday);
                    preparedStatement.setString(6, itemPolis);
                    preparedStatement.setString(7, sex);
                    preparedStatement.setString(8, fioPathient);
                    preparedStatement.setString(9, codDocter);
                    preparedStatement.setString(10, depDocter);
                    preparedStatement.setString(11, fioDocter);
                    preparedStatement.setString(12, profDoctor);
                    preparedStatement.setString(13, codMeds);
                    preparedStatement.setString(14, depMeds);
                    preparedStatement.setString(15, fioMeds);
                    preparedStatement.setString(16, profMeds);
                    preparedStatement.setDouble(17, sum);
                    preparedStatement.setString(18, kratnost);
                    preparedStatement.setInt(19, service);
                    preparedStatement.setInt(20, oc_sl);
                    preparedStatement.setString(21, delet);
                    preparedStatement.setString(22, diagnoz);
                    preparedStatement.setString(23, source);
                    preparedStatement.setString(24, obrash);
                    preparedStatement.setString(25, zabolev);

                    preparedStatement.executeUpdate();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                finally {
                    conn.closeConnection();
                }
            }
        }

        System.out.println("Base ready");
    }

    public void importInsurance(ActionEvent actionEvent) throws SQLException, IOException, ParseException, InterruptedException {

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open");
        File selectedFile = chooser.showOpenDialog(new Stage());
        System.out.println(selectedFile.getAbsolutePath());
        textPathFile.setText(selectedFile.getAbsolutePath());

        Calendar calendarService;
        Calendar calendarBirthday;
        SimpleDateFormat formatDate = new SimpleDateFormat("dd-MMM-yyyy");
        SimpleDateFormat formatDateBirday = new SimpleDateFormat("dd.MM.yyyy");

        int countSheet = new FileExcelXls(selectedFile.getAbsolutePath()).getSheetCount();

        for (int numberSheet = 0; numberSheet < countSheet; numberSheet++) {
            FileEx file = new FileExcelXls(selectedFile.getAbsolutePath());
            ExcelRead excelRead = new ExcelRead(file);

            ObservableList<ExcelRead> list = FXCollections.observableArrayList();
            TableView<ExcelRead> table = excelRead.getTableAllInsurance(list, numberSheet);

            for (int i = 0; i < table.getItems().size(); i++) {

                String serviceString = table.getItems().get(i).getService();
                if (serviceString == "")
                    continue;

                int index = i;
                String date = table.getItems().get(index).getData();

                if (date == "") {
                    index = returnNotNullIndex(table, i);
                }

                date = table.getItems().get(index).getData();
                String codDocter = table.getItems().get(index).getCodDocter();
                String fioDocter = table.getItems().get(index).getFioDocter();
                String address = table.getItems().get(index).getAddress();
                String attach = table.getItems().get(index).getAttach();
                String dataBirday = table.getItems().get(index).getDataBirday();
                String fioPathient = table.getItems().get(index).getFioPathient();
                String itemPolis = table.getItems().get(index).getMedPolis();
                String insurance = table.getItems().get(index).getInsuranceName();
                String codMeds = table.getItems().get(index).getCodMeds();
                String fioMeds = table.getItems().get(index).getFioMeds();
                Double sum = Double.parseDouble(table.getItems().get(index).getSum());
                Double sumError;

                try {
                    sumError = Double.parseDouble(table.getItems().get(index).getSumError());
                } catch (NumberFormatException e) {
                    sumError = 0.0;
                }

                int service = Integer.parseInt(serviceString);
                String diagnoz = table.getItems().get(i).getDiagnoz();
                String typeDiagnoz = table.getItems().get(i).getTypeDiagnoz();
                String numberService = table.getItems().get(i).getNumberService();
                String nameService = table.getItems().get(i).getNameService();
                String kratnost = table.getItems().get(i).getKratnost();

                String source = table.getItems().get(index).getSource();
                String operator = table.getItems().get(index).getOperator();
                String error = table.getItems().get(index).getError();

                calendarService = Calendar.getInstance();
                calendarService.setTime(formatDate.parse(date));
                calendarService.add(Calendar.HOUR, 5);
                calendarBirthday = Calendar.getInstance();
                calendarBirthday.setTime(formatDateBirday.parse(dataBirday));
                calendarBirthday.add(Calendar.HOUR, 5);

                Date dateService = new Date(calendarService.getTimeInMillis());
                Date dateBirthday = new Date(calendarBirthday.getTimeInMillis());

                PreparedStatement preparedStatement = null;

                DbConnect conn = new DbConnect();

                try {
                    preparedStatement = conn.getConnection().prepareStatement(SqlQuery.IMPORTSERVICE_INSURANCE.getQuery());

                    preparedStatement.setDate(1, dateService);
                    preparedStatement.setString(2, codDocter);
                    preparedStatement.setString(3, fioDocter);
                    preparedStatement.setString(4, address);
                    preparedStatement.setString(5, attach);
                    preparedStatement.setDate(6, dateBirthday);
                    preparedStatement.setString(7, fioPathient);
                    preparedStatement.setString(8, itemPolis);
                    preparedStatement.setString(9, insurance);
                    preparedStatement.setString(10, codMeds);
                    preparedStatement.setString(11, fioMeds);
                    preparedStatement.setDouble(12, sum);
                    preparedStatement.setDouble(13, sumError);
                    preparedStatement.setInt(14, service);
                    preparedStatement.setString(15, diagnoz);
                    preparedStatement.setString(16, typeDiagnoz);
                    preparedStatement.setString(17, numberService);
                    preparedStatement.setString(18, nameService);
                    preparedStatement.setString(19, kratnost);
                    preparedStatement.setString(20, source);
                    preparedStatement.setString(21, operator);
                    preparedStatement.setString(22, error);

                    preparedStatement.executeUpdate();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                finally {
                    conn.closeConnection();
                }
            }
        }

        System.out.println("Base ready");
    }

    public void importNapr(ActionEvent actionEvent) throws SQLException, IOException, ParseException,ArrayIndexOutOfBoundsException, InterruptedException {

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open");
        File selectedFile = chooser.showOpenDialog(new Stage());
        System.out.println(selectedFile.getAbsolutePath());
        textPathFile.setText(selectedFile.getAbsolutePath());

        Calendar calendarService;
        Calendar calendarForwardDate;
        SimpleDateFormat formatDate = new SimpleDateFormat("dd.MM.yyyy");

        PreparedStatement preparedStatement = null;
        DbConnect conn = new DbConnect();
        ResultSet resultSet = null;

        Scanner scanner = new Scanner(new File(selectedFile.getAbsolutePath()));
        scanner.next();

        for (int m = 0; scanner.hasNext(); m++) {
            String[] row = scanner.next().split(",", 13);
            String date = row[0];
            String diagnoz;
            String serviceString;
            int service;
            String service_q;
            String special_case;
            String visit_result;
            String illness_outcome;
            String codDocter;
            String codMeds;
            int idServices = 0;

            String serPolis = "";
            String forwardMO = "";
            String forwardDate = "";

            try {
                diagnoz = row[1];
            } catch (Exception e) {
                System.out.println(m);
                continue;
            }
            serviceString = row[2];
            service = Integer.parseInt(serviceString);
            service_q = row[3];
            special_case = row[4];
            special_case = row[4];
            visit_result = row[5];
            illness_outcome = row[6];
            codDocter = row[7];
            codMeds = row[8];

            if (row.length == 9) {
                System.out.println(row.length + " " + row[0] + " " + row[1] + " " + row[2] + " " + row[3]);
            } else {
                forwardMO = row[9];
                forwardDate = row[10];
            }

            if (row.length == 11 || row.length == 9) {
                System.out.println(row.length + " " + row[0] + " " + row[1] + " " + row[2] + " " + row[3]);
            } else {
                System.out.println(row.length + " " + row[0] + " " + row[1] + " " + row[2] + " " + row[3]);

                String joinPolis = row[11] + " " + row[12];
                serPolis = joinPolis.trim();
            }

            calendarService = Calendar.getInstance();
            calendarService.setTime(formatDate.parse(date));
            calendarService.add(Calendar.HOUR, 5);

            Date dateForward;

            if (forwardDate.equals("")) {
                dateForward = null;
            } else {
                calendarForwardDate = Calendar.getInstance();
                calendarForwardDate.setTime(formatDate.parse(forwardDate));
                calendarForwardDate.add(Calendar.HOUR, 5);

                dateForward = new Date(calendarForwardDate.getTimeInMillis());
            }

            Date dateService = new Date(calendarService.getTimeInMillis());


            preparedStatement = null;
            conn = new DbConnect();

            try {
                preparedStatement = conn.getConnection().prepareStatement(SqlQuery.IMPORTSERVICE_NAPR.getQuery());

                preparedStatement.setDate(1, dateService);
                preparedStatement.setString(2, diagnoz);
                preparedStatement.setInt(3, service);
                preparedStatement.setString(4, service_q);
                preparedStatement.setString(5, special_case);
                preparedStatement.setString(6, visit_result);
                preparedStatement.setString(7, illness_outcome);
                preparedStatement.setString(8, codDocter);
                preparedStatement.setString(9, codMeds);
                preparedStatement.setString(10, forwardMO);
                preparedStatement.setDate(11, dateForward);
                preparedStatement.setString(12, serPolis);
                // preparedStatement.setInt(13, idServices);

                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                conn.closeConnection();
            }
        }
        scanner.close();

        System.out.println("Base ready");
    }

    @FXML
    void searchPolisClick() throws SQLException {
        tablePathientsView.getItems().clear();

        int id = 0;
        String polis;
        String fioPathient;
        Date sqlBirthDate;
        String attach;
        String address;

        DbConnect conn = new DbConnect();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = conn.getConnection().prepareStatement(SqlQuery.SEARCHPOLIC.getQuery());

            String searchPolis = "%" + textSearchPolis.getText() + "%";
            preparedStatement.setString(1, searchPolis);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                id++;

                fioPathient = resultSet.getString(Const.SERVICE_FIOPATHIENT);
                polis = resultSet.getString(Const.SERVICE_SERPOLIC);
                sqlBirthDate = resultSet.getDate(Const.SERVICE_BIRTHPATHIENT);
                attach = resultSet.getString(Const.SERVICE_ATTACH);
                address = resultSet.getString(Const.SERVICE_ADDRESS);

                pathient.add(new Pathient(id, fioPathient, polis, sqlBirthDate, attach, address));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultSet.close();
            preparedStatement.close();
            conn.closeConnection();
        }

        tablePathientsView.setItems(pathient);
    }

    @FXML
    void errorClick(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/error.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        stage.showAndWait();
    }

    @FXML
    void serviceClick(ActionEvent event) {
        Pathient pathient = tablePathientsView.getSelectionModel().getSelectedItem();

        if (pathient != null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/service.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            serviceController serviceController = loader.getController();
            serviceController.setPathientLabel(pathient.getPolisPathient(), pathient.getFioPathient(), pathient.getDateBirdayPathient(),
                    pathient.getAttach(), pathient.getAddress());

            stage.showAndWait();
        }
    }

    public void setDisableImport() {
        buttonImport.disableProperty().setValue(false);
        buttonImportInsurance.disableProperty().setValue(false);
        buttonImportNapr.disableProperty().setValue(false);
    }

    private Integer returnNotNullIndex(TableView<ExcelRead> table, int i) {

        int index = i - 1;
        String date = table.getItems().get(index).getData();

        while (date == "") {
            index--;
            date = table.getItems().get(index).getData();
        }

        return index;
    }
}
