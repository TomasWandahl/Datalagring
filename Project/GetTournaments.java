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
import java.util.logging.Level;
import java.util.logging.Logger;

public class GetTournaments {

    public void getTournaments() {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        String url = "jdbc:mysql://localhost:3306/datalagringprojekt";
        String user = "root";
        String password = "";

        try {
            con = DriverManager.getConnection(url, user, password);
            pst = con.prepareStatement("SELECT `Name`, `StartDate`, `EndDate` FROM `tournament` WHERE 1");
            rs = pst.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    System.out.println("[Turneringsnamn: " + rs.getString(1) +
                            " | Startdatum: " + rs.getString(2) + "| Slutdatum: " + rs.getString(3) + "]");
                }
            }

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(GetTournaments.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            System.out.println("\n");
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
                Logger lgr = Logger.getLogger(GetTournaments.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
    }
}