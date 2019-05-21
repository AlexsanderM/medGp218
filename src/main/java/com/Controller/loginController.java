package com.Controller;

import com.base.Const;
import com.base.DbConnect;
import com.base.SqlQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class loginController {

    @FXML
    private TextField loginText;

    @FXML
    private PasswordField passText;

    @FXML
    private Button loginBut;

    @FXML
    void initialize() {}

    private String userName;

    public void loginClick(ActionEvent actionEvent) throws SQLException {
        ResultSet resultSet = null;
        DbConnect db = new DbConnect();

        String select = SqlQuery.SIGNIN.getQuery();

        PreparedStatement prSt = null;
        try {
            prSt = db.getConnection().prepareStatement(select);

            prSt.setString(1, loginText.getText());
            prSt.setString(2, passText.getText());

            resultSet = prSt.executeQuery();

            while (resultSet.next()) {
                Stage stage = (Stage) loginBut.getScene().getWindow();
                stage.close();

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/main.fxml"));

                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Parent root = loader.getRoot();
                stage = new Stage();
                stage.setScene(new Scene(root));

                userName = resultSet.getString(Const.USER_NAME);

                if (userName.equals("admin"))
                    getAccessAdmin(loader);

                stage.show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultSet.close();
            prSt.close();
            db.closeConnection();
        }
    }

    private void getAccessAdmin(FXMLLoader loader) {
        mainController m = loader.getController();
        m.setDisableImport();
    }
}
