package model;

import java.sql.*;

public class Bang {
    protected double dienTich;
    public static double phiMotMet;
    protected String tenBang;

    public Bang() {
    }

    public Bang(double dienTich) {
        this.dienTich = dienTich;
        setPhiMotMet();
    }

    public double getDienTich() {
        return dienTich;
    }

    public void setDienTich(double dienTich) {
        this.dienTich = dienTich;
    }

    public double getPhiMotMet() {
        return phiMotMet;
    }

    public String getNameBang(){
        return "Normal";
    }
    public double getChiPhi() {
        return phiMotMet * dienTich;
    }
    public static void setPhiMotMet() {
        String url = "jdbc:mysql://localhost:3306/oop";
        String pass = "";
        String username= "root";
        String query = "SELECT * FROM `fee` WHERE 1";
        try (Connection conn = DriverManager.getConnection(url,username,pass)){
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                phiMotMet = rs.getDouble("CostPerM2");
            }
        }
    catch (SQLException e){
        }
    }
}
