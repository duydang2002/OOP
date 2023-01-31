// Bui Trong Dung 20207594
package model;

import java.sql.*;

public class BangTamGiac extends Bang {
    public static double phiTang;

    public BangTamGiac(double dienTich) {
        super(dienTich);
        setPhiTang();
    }

    @Override
    public double getChiPhi() {
        return super.getDienTich()*super.getPhiMotMet()+phiTang;
    }

    @Override
    public String getNameBang() {
        return "Triangle";
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
                phiTang = rs.getDouble("AdditionalFeeTriangle");
            }
            rs.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
   }
