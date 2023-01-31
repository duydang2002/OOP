// Bui Trong Dung 20207594
package Controller;

import com.sun.javafx.fxml.builder.web.WebEngineBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import model.Bang;
import model.BangTamGiac;
import model.BangTron;
import model.DonHang;

import javafx.scene.control.TextField;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ThemDonHangController {
    @FXML
    private TextField CustomerName;
    @FXML
    private TextField CustomerAddress;
    @FXML
    private TextField Area;
    @FXML
    private DatePicker Time;
    @FXML
    private ComboBox<String> Type;
    private ObservableList<String> SelectList = FXCollections.observableArrayList("Normal","Triangle","Circle");
    private DonHang donHang;
    private Bang bang;
    public void initialize() {
        Type.setValue("Normal");
        Type.setItems(SelectList);
    }
    @FXML
    public void ThemButton(ActionEvent ActionEvent) throws IOException {

        String url = "jdbc:mysql://localhost:3306/oop";
        String pass = "";
        String username= "root";
        if (CustomerAddress.getText().isEmpty() | CustomerName.getText().isEmpty()| Area.getText().isEmpty() | Time.getValue()== null) {
            JOptionPane.showMessageDialog(null,"Please enter all the field", "Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!isNumeric(Area.getText())){
            JOptionPane.showMessageDialog(null,"Please enter an number in Area field", "Error",JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (Type.getValue().equals("Normal")) {
            bang = new Bang(Double.parseDouble(Area.getText()));
        }
        else if (Type.getValue().equals("Triangle")){
            // UpCasting
            bang = new BangTamGiac(Double.parseDouble(Area.getText()));

        }
        else {
            // UpCasting
            bang = new BangTron(Double.parseDouble(Area.getText()));
        }

        donHang = new DonHang(CustomerName.getText(),CustomerAddress.getText(),bang,Time.getValue().toString());

        try (Connection conn = DriverManager.getConnection(url,username,pass)){
            String Insert = "INSERT INTO `receipttable`(`CustomerName`, `TimeAdd`, `Address`, `Area`,`Cost`,`Type`) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(Insert);
            ps.setString(1,donHang.getTen());
            ps.setString(2,donHang.getThoiGianThem());
            ps.setString(3,donHang.getDiaChi());
            ps.setDouble(4,donHang.getBang().getDienTich());
            ps.setDouble(5,donHang.getBang().getChiPhi());
            ps.setString(6,donHang.getBang().getNameBang());
            ps.execute();
        }

        catch (SQLException throwables) {
//            throwables.printStackTrace();

        }
        JOptionPane.showMessageDialog(null,"Adding successfully","Success",JOptionPane.PLAIN_MESSAGE);
        SceneController sceneController=new SceneController();
        sceneController.BackToMain(ActionEvent);
    }
    public boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
