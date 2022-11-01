import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class WorkerTransactions {
	
	private Connection con = null;
	
	private Statement statement = null;
	
	private PreparedStatement preparedStatement = null;
	
	public WorkerTransactions() {
		
		String url = "jdbc:mysql://" + Database.host + ":" + Database.port + "/" + Database.db_name + "?useUnicode=true&characterEncodingutf=8";
		
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Connector Driver is not found....");
        }
        
        try {
            con = DriverManager.getConnection(url, Database.username, Database.password);
            System.out.println("Connection Success");           
            
        } catch (SQLException ex) {
            System.out.println("Connection Failed...");

        }
	}
	
	public ArrayList<Employee> getEmployeeList() {
		
		ArrayList<Employee> output = new ArrayList<Employee>();
		
		try {
			statement = con.createStatement();
			
			String query = "SELECT * FROM calisanlar";//DB den gelen her calisani yeni bir calisan yaratip arraylist e attik.
			
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("ad");
				String surname = rs.getString("soyad");
				String department = rs.getString("departman");
				String salary = rs.getString("maas");
				
				output.add(new Employee(id, name, surname, department, salary));
			}
			
			return output;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public boolean getLoggedIn(String username, String password) {
		
		String query = "Select * from adminler where username = ? and password = ?";
		
		try {
			preparedStatement = con.prepareStatement(query);
			
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			return rs.next();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
}
