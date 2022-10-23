import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CalisanIslemler {
	
	private Connection con = null;
	
	private Statement statement = null;
	
	private PreparedStatement preparedStatement = null;
	
	public boolean girisYap(String kullanici_adi, String parola) {
		
		String query = "SELECT * FROM customers";
		
		try {
			preparedStatement = con.prepareStatement(query);
			
			preparedStatement.setString(1, kullanici_adi);
			preparedStatement.setString(2, parola);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			return rs.next();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
	}

	public CalisanIslemler() {
		
		String url = "jdbc:postgresql://localhost:5432/deneme";
		
		try {
			
			Class.forName("org.postgresql.Driver");
			System.out.println("Driver check has been passed..");
			
		} catch (Exception e) {
			System.out.println("Driver is not found...");
		}
		
		try {
			
			con = DriverManager.getConnection(url, Database.username, Database.password);
			
			System.out.println("connection Success!");
			
		} catch (SQLException e) {
			
			System.out.println("Connection failed...");
			
		}
		
	}
	
	
	
	

}
