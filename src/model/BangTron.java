// Bui Trong Dung 20207594
package model;

import java.sql.*;

public class BangTron extends Bang {
    public static double phiTang;

    public BangTron(double dienTich) {
        super(dienTich);
        setPhiTang();
    }

    @Override
    public double getChiPhi() {
        return super.getDienTich()*super.getPhiMotMet()+phiTang;
    }

    @Override
    public String getNameBang() {
        return "Circle";
    }
    public static void setPhiTang() {
        String url = "jdbc:mysql://localhost:3306/oop";
        String pass = "";
        String username= "root";
        String query = "SELECT * FROM `fee` WHERE 1";
        try (Connection conn = DriverManager.getConnection(url,username,pass)){
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                phiTang = rs.getDouble("AdditionalFeeCircle");
            }
            rs.close();
        }
        catch (SQLException e){
        }
    }
   }
