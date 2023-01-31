// Dang Nhat Duy 20207596
package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Bang;
import model.BangTamGiac;
import model.BangTron;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;

public class SuaGiaController {
    @FXML
    private TextField AdditionalCostTriangle;
    @FXML
    private TextField AdditionalCostCircle;

    @FXML
    private TextField CostPerm2;

    @FXML
    private Button EditCommit;
    @FXML
    public void initialize(){
        CostPerm2.setText(String.valueOf(Bang.phiMotMet));
        AdditionalCostTriangle.setText(String.valueOf(BangTamGiac.phiTang));
        AdditionalCostCircle.setText(String.valueOf(BangTron.phiTang));
    }

    @FXML
    void EditButton(ActionEvent ActionEvent) throws IOException {
        UpdatePhiMotMet(Double.parseDouble(CostPerm2.getText()));
        Bang.setPhiMotMet();
        UpdatePhiTangBangTron(Double.parseDouble(AdditionalCostCircle.getText()));
        BangTron.setPhiTang();
        UpdatePhiTangBangTamGiac(Double.parseDouble(AdditionalCostTriangle.getText()));
        BangTamGiac.setPhiTang();
        JOptionPane.showMessageDialog(null,"Edit successfully","Success",JOptionPane.PLAIN_MESSAGE);
        SceneController sceneController=new SceneController();
        sceneController.BackToMain(ActionEvent);

    }
    public void UpdatePhiMotMet(double phi) {
        String url = "jdbc:mysql://localhost:3306/oop";
        String pass = "";
        String username= "root";
        String query = "UPDATE `fee` SET `CostPerM2`=? Where 1";
        try (Connection conn = DriverManager.getConnection(url,username,pass)){
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setDouble(1,phi);
            ps.execute();
            ps.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void UpdatePhiTangBangTron(Double phiTang) {
        String url = "jdbc:mysql://localhost:3306/oop";
        String pass = "";
        String username= "root";
        String query = "UPDATE `fee` SET `AdditionalFeeCircle`=? WHERE 1;";
        try (Connection conn = DriverManager.getConnection(url,username,pass)){
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setDouble(1,phiTang);
            ps.execute();
            ps.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void UpdatePhiTangBangTamGiac(Double phiTang) {
        String url = "jdbc:mysql://localhost:3306/oop";
        String pass = "";
        String username= "root";
        String query = "UPDATE `fee` SET `AdditionalFeeTriangle`=? WHERE 1;";
        try (Connection conn = DriverManager.getConnection(url,username,pass)){
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setDouble(1,phiTang);
            ps.execute();
            ps.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
