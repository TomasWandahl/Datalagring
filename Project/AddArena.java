import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by tomas on 2017-01-04.
 */
public class AddArena {
    public void addArena() {
        int res = 0;
        Connection con = null;
        PreparedStatement pst = null;
        String url = "jdbc:mysql://localhost:3306/datalagringprojekt";
        String user = "root";
        String password = "";

        Scanner keyboard = new Scanner(System.in);
        System.out.println("Specify arena name:");
        String arenaName = keyboard.nextLine();
        System.out.println("Specify city:");
        String arenaCity = keyboard.nextLine();

        try {
            con = DriverManager.getConnection(url, user, password);

            pst = con.prepareStatement("INSERT INTO `Arena`(`Name`, `City`) VALUES ('" + arenaName + "','" + arenaCity + "')");
            res = pst.executeUpdate();

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Main.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } finally {
            if (res == 0) {
                System.out.println("Something went wrong!\n");
            } else {
                System.out.println("Arena added successfully!\n");
            }
            try {
                if (pst != null) {
                    pst.close();
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
