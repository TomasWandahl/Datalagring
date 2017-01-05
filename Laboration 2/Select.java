/**
 * Created by Tomas Wändahl on 2016-12-22.
 * All rights reserved.
 * Yeah.
 */


import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Select {

    public void retrieve() {

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ResultSet ownerID = null;


        String url = "jdbc:mysql://localhost:3306/laboration";
        String user = "root";
        String password = "";

        System.out.println("Show all brands OR all cars in a specific city?");
        Scanner keyboard = new Scanner(System.in);
        String op = keyboard.nextLine();

        try {
            con = DriverManager.getConnection(url, user, password);

            switch (op.toUpperCase()) {
                case "BRANDS":
                    pst = con.prepareStatement("SELECT DISTINCT marke FROM bil");
                    rs = pst.executeQuery();
                    System.out.println("All brands currently in database:");
                    break;

                case "CITY":
                    System.out.println("Enter a city:");
                    String city = keyboard.nextLine();
                    pst = con.prepareStatement("SELECT regnr FROM bil WHERE agare in (SELECT id FROM person WHERE stad = ?)");
                    pst.setString(1, city);
                    rs = pst.executeQuery();
                    System.out.println("Cars in " + city);
                    break;
                default:
                    System.out.println("Invalid op!");
                    break;
            }

            if (rs != null) {
                while (rs.next()) {
                    System.out.println(rs.getString(1));
                }
            }

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Select.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(Select.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
    }
}