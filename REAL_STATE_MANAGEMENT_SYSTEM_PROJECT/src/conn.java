
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class conn {

	private static String url = "jdbc:mysql://localhost:3306/java_rst_db";
	private static String username = "root";
	private static String password = "root";

	public static Connection getTheConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return con;
	}

}