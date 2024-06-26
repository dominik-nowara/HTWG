package data;

import gui.MainController;
import javafx.application.Platform;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import org.controlsfx.control.CheckComboBox;

import java.sql.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class SqlUtility {
    public static void setDropdowns(ComboBox<String> countryDropdown, CheckComboBox<String> equipmentDropdown) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;

        try {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver()); 				// Treiber laden
            String url = "jdbc:oracle:thin:@oracle19c.in.htwg-konstanz.de:1521:ora19c"; // String für DB-Connection
            conn = DriverManager.getConnection(url, "dbsys49", "dbsys49"); 						// Verbindung erstellen

            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE); 			// Transaction Isolations-Level setzen
            conn.setAutoCommit(false);													// Kein automatisches Commit

            stmt = conn.createStatement();

            String countrySelectQuery = "SELECT laendername FROM dbsys17.land";
            rset = stmt.executeQuery(countrySelectQuery);									// Query ausführen

            while(rset.next())
                countryDropdown.getItems().add(rset.getString("laendername"));
            countryDropdown.getSelectionModel().selectFirst();

            String equipSelectQuery = "SELECT ausstattungsname FROM dbsys17.ausstattung";
            rset = stmt.executeQuery(equipSelectQuery);									// Query ausführen

            while(rset.next())
                equipmentDropdown.getItems().add(rset.getString("ausstattungsname"));

            stmt.close();																// Verbindung trennen
            conn.commit();
            conn.close();
        } catch (SQLException se) {														// SQL-Fehler abfangen
            System.out.println();
            System.out
                    .println("SQL Exception occurred while establishing connection to DBS: "
                            + se.getMessage());
            System.out.println("- SQL state  : " + se.getSQLState());
            System.out.println("- Message    : " + se.getMessage());
            System.out.println("- Vendor code: " + se.getErrorCode());
            System.out.println();
            System.out.println("EXITING WITH FAILURE ... !!!");
            System.out.println();
            try {
                conn.rollback();														// Rollback durchführen
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void setApartments(VBox listContainer, String country, String fromMoney, String toMoney, String rooms, LocalDate fromDate, LocalDate toDate) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;

        try {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver()); 				// Treiber laden
            String url = "jdbc:oracle:thin:@oracle19c.in.htwg-konstanz.de:1521:ora19c"; // String für DB-Connection
            conn = DriverManager.getConnection(url, "dbsys49", "dbsys49"); 						// Verbindung erstellen

            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE); 			// Transaction Isolations-Level setzen
            conn.setAutoCommit(false);													// Kein automatisches Commit

            stmt = conn.createStatement();

            String selectQuery = "SELECT f.wohnungs_id, f.wohnungsname, f.preis, f.groesse, f.zimmer, COALESCE(AVG(b.bewertungssterne), 0) AS sterne, pic.bildlink" +
                    " FROM dbsys17.ferienwohnung f" +
                    " JOIN dbsys17.adresse a ON f.adress_id = a.adress_id" +
                    " JOIN dbsys17.bild pic ON f.wohnungs_id = pic.wohnungs_id" +
                    " LEFT JOIN dbsys17.buchung b ON f.wohnungs_id = b.wohnungs_id" +
                    " AND NOT (b.startdatum <= ? AND b.enddatum >= ?)" +
                    " AND NOT (b.startdatum <= ? AND b.startdatum >= ?)" +
                    " AND NOT (b.enddatum <= ? AND b.enddatum >= ?)" +
                    " AND NOT (b.startdatum <= ? AND b.enddatum >= ?)" +
                    " WHERE a.laendername = ? " +
                    " AND f.preis >= ? " +
                    " AND f.preis <= ?";

            if (rooms != null && !rooms.isEmpty())
                selectQuery += " AND f.zimmer = ?";

            selectQuery += " GROUP BY f.wohnungs_id, f.wohnungsname, f.preis, f.groesse, f.zimmer, pic.bildlink";

            PreparedStatement pstmt = conn.prepareStatement(selectQuery);
            pstmt.setDate(1, Date.valueOf(fromDate));
            pstmt.setDate(2, Date.valueOf(toDate));
            pstmt.setDate(3, Date.valueOf(fromDate));
            pstmt.setDate(4, Date.valueOf(toDate));
            pstmt.setDate(5, Date.valueOf(fromDate));
            pstmt.setDate(6, Date.valueOf(toDate));
            pstmt.setDate(7, Date.valueOf(fromDate));
            pstmt.setDate(8, Date.valueOf(toDate));

            pstmt.setString(9, country);
            pstmt.setFloat(10, (fromMoney != null && !fromMoney.isEmpty()) ? Float.parseFloat(fromMoney) : 0);
            pstmt.setFloat(11, (toMoney != null && !toMoney.isEmpty()) ? Float.parseFloat(toMoney) : (float) Integer.MAX_VALUE);

            if (rooms != null && !rooms.isEmpty())
                pstmt.setInt(12, Integer.parseInt(rooms));

            rset = pstmt.executeQuery();

            while(rset.next()) {
                int gotId = rset.getInt("wohnungs_id");
                String gotName = rset.getString("wohnungsname");
                String gotPreis = rset.getString("preis");
                String gotZimmer = rset.getString("zimmer");
                String gotSize = rset.getString("groesse");
                float gotSterne = rset.getFloat("sterne");
                gotSterne = (float) (Math.round(gotSterne * 2) / 2.0);
                String gotBildlink = rset.getString("bildlink");

                MainController.addApartmentItem(listContainer, gotId, gotName, gotPreis, gotZimmer, gotSize, gotSterne, gotBildlink, fromDate, toDate);
            }

            stmt.close();																// Verbindung trennen
            conn.commit();
            conn.close();
        } catch (SQLException se) {														// SQL-Fehler abfangen
            System.out.println();
            System.out
                    .println("SQL Exception occurred while establishing connection to DBS: "
                            + se.getMessage());
            System.out.println("- SQL state  : " + se.getSQLState());
            System.out.println("- Message    : " + se.getMessage());
            System.out.println("- Vendor code: " + se.getErrorCode());
            System.out.println();
            System.out.println("EXITING WITH FAILURE ... !!!");
            System.out.println();
            try {
                conn.rollback();														// Rollback durchführen
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void setApartmentsWithEquip(VBox listContainer, String country, String fromMoney, String toMoney, String rooms, LocalDate fromDate, LocalDate toDate, List<String> equips) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;

        try {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver()); 				// Treiber laden
            String url = "jdbc:oracle:thin:@oracle19c.in.htwg-konstanz.de:1521:ora19c"; // String für DB-Connection
            conn = DriverManager.getConnection(url, "dbsys49", "dbsys49"); 						// Verbindung erstellen

            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE); 			// Transaction Isolations-Level setzen
            conn.setAutoCommit(false);													// Kein automatisches Commit

            stmt = conn.createStatement();

            String placeholders = String.join(", ", Collections.nCopies(equips.size(), "?"));

            String selectQuery = "SELECT f.wohnungs_id, f.wohnungsname, f.preis, f.groesse, f.zimmer, COALESCE(AVG(b.bewertungssterne), 0) AS sterne, pic.bildlink" +
                    " FROM dbsys17.ferienwohnung f" +
                    " JOIN dbsys17.adresse a ON f.adress_id = a.adress_id" +
                    " JOIN dbsys17.bild pic ON f.wohnungs_id = pic.wohnungs_id" +
                    " LEFT JOIN dbsys17.beinhaltete_ausstattung ba ON f.wohnungs_id = ba.wohnungs_id" +
                    " LEFT JOIN dbsys17.buchung b ON f.wohnungs_id = b.wohnungs_id" +
                    " AND NOT (b.startdatum <= ? AND b.enddatum >= ?)" +
                    " AND NOT (b.startdatum <= ? AND b.startdatum >= ?)" +
                    " AND NOT (b.enddatum <= ? AND b.enddatum >= ?)" +
                    " AND NOT (b.startdatum <= ? AND b.enddatum >= ?)" +
                    " WHERE a.laendername = ? " +
                    " AND f.preis >= ? " +
                    " AND f.preis <= ?" +
                    " AND ba.ausstattungsname IN (" + placeholders + ")";

            if (rooms != null && !rooms.isEmpty())
                selectQuery += " AND f.zimmer = ?";

            selectQuery += " GROUP BY f.wohnungs_id, f.wohnungsname, f.preis, f.groesse, f.zimmer, pic.bildlink";

            System.out.println(selectQuery);

            PreparedStatement pstmt = conn.prepareStatement(selectQuery);
            pstmt.setDate(1, Date.valueOf(fromDate));
            pstmt.setDate(2, Date.valueOf(toDate));
            pstmt.setDate(3, Date.valueOf(fromDate));
            pstmt.setDate(4, Date.valueOf(toDate));
            pstmt.setDate(5, Date.valueOf(fromDate));
            pstmt.setDate(6, Date.valueOf(toDate));
            pstmt.setDate(7, Date.valueOf(fromDate));
            pstmt.setDate(8, Date.valueOf(toDate));

            pstmt.setString(9, country);
            pstmt.setFloat(10, (fromMoney != null && !fromMoney.isEmpty()) ? Float.parseFloat(fromMoney) : 0);
            pstmt.setFloat(11, (toMoney != null && !toMoney.isEmpty()) ? Float.parseFloat(toMoney) : (float) Integer.MAX_VALUE);

            for (int i = 12; i < equips.size() + 12; i++) {
                pstmt.setString(i, equips.get(i - 12));
            }

            if (rooms != null && !rooms.isEmpty())
                pstmt.setInt(13, Integer.parseInt(rooms));

            rset = pstmt.executeQuery();

            while(rset.next()) {
                int gotId = rset.getInt("wohnungs_id");
                String gotName = rset.getString("wohnungsname");
                String gotPreis = rset.getString("preis");
                String gotZimmer = rset.getString("zimmer");
                String gotSize = rset.getString("groesse");
                float gotSterne = rset.getFloat("sterne");
                gotSterne = (float) (Math.round(gotSterne * 2) / 2.0);
                String gotBildlink = rset.getString("bildlink");

                MainController.addApartmentItem(listContainer, gotId, gotName, gotPreis, gotZimmer, gotSize, gotSterne, gotBildlink, fromDate, toDate);
            }

            stmt.close();																// Verbindung trennen
            conn.commit();
            conn.close();
        } catch (SQLException se) {														// SQL-Fehler abfangen
            System.out.println();
            System.out
                    .println("SQL Exception occurred while establishing connection to DBS: "
                            + se.getMessage());
            System.out.println("- SQL state  : " + se.getSQLState());
            System.out.println("- Message    : " + se.getMessage());
            System.out.println("- Vendor code: " + se.getErrorCode());
            System.out.println();
            System.out.println("EXITING WITH FAILURE ... !!!");
            System.out.println();
            try {
                conn.rollback();														// Rollback durchführen
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static String bookApartment(int id, LocalDate fromDate, LocalDate toDate, String price) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;

        try {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver()); 				// Treiber laden
            String url = "jdbc:oracle:thin:@oracle19c.in.htwg-konstanz.de:1521:ora19c"; // String für DB-Connection
            conn = DriverManager.getConnection(url, "dbsys49", "dbsys49"); 						// Verbindung erstellen

            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE); 			// Transaction Isolations-Level setzen
            conn.setAutoCommit(false);													// Kein automatisches Commit

            stmt = conn.createStatement();

            String bookingInsert = "INSERT INTO dbsys17.buchung(startdatum, enddatum, betrag, wohnungs_id, mail) " +
                    "VALUES(?, ?, ?, ?, 'Adams@aol.com')";

            PreparedStatement pstmt = conn.prepareStatement(bookingInsert);
            pstmt.setDate(1, Date.valueOf(fromDate));
            pstmt.setDate(2, Date.valueOf(toDate));
            pstmt.setFloat(3, Float.parseFloat(price));
            pstmt.setInt(4, id);
            pstmt.executeQuery();

            stmt.close();																// Verbindung trennen
            conn.commit();
            conn.close();
            return "";
        } catch (SQLException se) {														// SQL-Fehler abfangen
            System.out.println();
            System.out
                    .println("SQL Exception occurred while establishing connection to DBS: "
                            + se.getMessage());
            System.out.println("- SQL state  : " + se.getSQLState());
            System.out.println("- Message    : " + se.getMessage());
            System.out.println("- Vendor code: " + se.getErrorCode());
            System.out.println();
            System.out.println("EXITING WITH FAILURE ... !!!");
            System.out.println();
            try {
                conn.rollback();														// Rollback durchführen
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return se.getMessage();
        }
    }
}
