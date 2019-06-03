package Database;
import java.sql.*;
import java.util.ArrayList;

public class Db {
	private Connection con;
	public Db() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://databases.aii.avans.nl:3306/tjpmsalt_db2","bverheij4","Ab12345");
			
		} catch (Exception e){
			System.out.println("error"+ e);
		}
	}
	
	/**
	 * Voert een select query uit op de datbase.
	 *<br>De plek (key) van de collumn waarde is gebaseerd op de hoeveelste rij het is
	 * van de select.
	 * 
	 * @param De query die je wilt uitvoeren
	 * @return Geeft een 2D-ArrayList van type Object terug
	 */
	public ArrayList<ArrayList<Object>> select(String Query) {
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(Query);

			ArrayList<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();
			while(rs.next()) {
				ArrayList<Object> row = new ArrayList<Object>();
				for(int i = 1; i < rs.getMetaData().getColumnCount() + 1; i++) {
					row.add(rs.getObject(i));
				}
				data.add(row);
			}
			stmt.close();
			return data;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	/**
	 * Insert, Delete, Update
	 * <br>
	 * @param De query die je wilt uitvoeren
	 * @return Geen return waarde
	 */
	
	public void cud(String Query) {
		try {
			PreparedStatement prdstmt = con.prepareStatement(Query);
			prdstmt.execute();
			prdstmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
	

}

