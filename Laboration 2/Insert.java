/**
 * Created by Tomas Wändahl on 2016-12-22.
 * All rights reserved.
 * Yeah.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Insert {

    public void input() {
        Connection con = null;
        PreparedStatement pst = null;
        String url = "jdbc:mysql://localhost:3306/laboration";
        String user = "root";
        String password = "";

        try {
            con = DriverManager.getConnection(url, user, password);

            Scanner keyboard = new Scanner(System.in);
            System.out.println("Specify car and new color");
            String userInput = keyboard.nextLine();
            String[] userInputs = userInput.split(" ");

            pst = con.prepareStatement("UPDATE bil SET farg = ? WHERE regnr = ?");
            pst.setString(1, userInputs[1]);
            pst.setString(2, userInputs[0]);
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Main.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } finally {
            try {
                if (pst.executeUpdate() == 0) {
                    System.out.println("Couldnt find car in database");
                    pst.close();
                } else {
                    System.out.println("Car color succesfully changed");
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(Main.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
    }
}