// Dang Nhat Duy 20207596
package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.*;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class SuaDonHangController
{
    @FXML
    private TextField Area;
    @FXML
    private TextField CustomerAddress;

    @FXML
    private TextField CustomerName;
    @FXML
    private Button EditCommit;
    @FXML
    private DatePicker Time;
    @FXML
    private ComboBox<String> Type;
    private ObservableList<String> SelectList = FXCollections.observableArrayList("Normal","Triangle","Circle");
    private int ID;
    private DonHang a;
    private Bang b;
    @FXML
    void EditButton(ActionEvent ActionEvent) throws IOException {
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
            b = new Bang(Double.parseDouble(Area.getText()));
        }
        else if (Type.getValue().equals("Triangle")){
            // UpCasting
            b = new BangTamGiac(Double.parseDouble(Area.getText()));

        }
        else {
            // UpCasting
            b = new BangTron(Double.parseDouble(Area.getText()));

        }
        a = new DonHang(CustomerName.getText(),CustomerAddress.getText(),b,Time.getValue().toString());
        try (Connection conn = DriverManager.getConnection(url,username,pass)){
            String Insert = "UPDATE `receipttable` SET`CustomerName`=?," +
                    "`TimeAdd`=?,`Address`=?,`Area`=?," +
                    "`Cost`=?,`Type`= ? WHERE `ID` = "+this.ID;
            PreparedStatement ps = conn.prepareStatement(Insert);
            ps.setString(1,a.getTen());
            ps.setString(2,a.getThoiGianThem());
            ps.setString(3,a.getDiaChi());
            ps.setDouble(4,a.getBang().getDienTich());
            ps.setDouble(5,a.getBang().getChiPhi());
            ps.setString(6,a.getBang().getNameBang());
            ps.execute();
        }

        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        JOptionPane.showMessageDialog(null,"Edit successfully","Success",JOptionPane.PLAIN_MESSAGE);
        SceneController sceneController=new SceneController();
        sceneController.BackToMain(ActionEvent);
    }
    public void  setEdit(DonHangDataBase donHangDataBase){

        CustomerName.setText(donHangDataBase.getTen());
        this.Area.setText(String.valueOf(donHangDataBase.getDienTich()));
        this.CustomerAddress.setText(donHangDataBase.getDiaChi());
        this.Time.setValue(LocalDate.parse(donHangDataBase.getThoiGianThem()));
        Type.setItems(SelectList);
        this.Type.setValue(donHangDataBase.getTenBang());
        ID = donHangDataBase.getID();
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
