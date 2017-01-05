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

public class GetArenas {
    public void getArenas() {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ResultSet ownerID = null;

        String url = "jdbc:mysql://localhost:3306/datalagringprojekt";
        String user = "root";
        String password = "";

        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter a tournament:");
        String tournament = keyboard.nextLine();

        try {
            con = DriverManager.getConnection(url, user, password);

            pst = con.prepareStatement("SELECT `Name`, `City` FROM `arena` WHERE ArenaID in (SELECT ArenaID FROM tournamentarena WHERE TournamentID in (SELECT TournamentID FROM tournament WHERE name = ?))");
            pst.setString(1, tournament);
            rs = pst.executeQuery();
            System.out.println("Arenas used in tournament " + tournament + ": ");

            if (rs != null) {
                while (rs.next()) {
                    System.out.println("[Name: " + rs.getString(1) + " | City: " + rs.getString(2) + "]");
                }
            }

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(GetArenas.class.getName());
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
                Logger lgr = Logger.getLogger(GetArenas.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
    }
}