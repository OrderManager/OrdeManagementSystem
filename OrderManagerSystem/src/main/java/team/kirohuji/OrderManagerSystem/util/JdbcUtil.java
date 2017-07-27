package team.kirohuji.OrderManagerSystem.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtil {
	private Connection conn = null;
	private Properties pt = new Properties();
	private static JdbcUtil jdbcUtil = null;

	public JdbcUtil() throws ClassNotFoundException, IOException {
		openConnection();
	}

	public void openConnection() throws IOException, ClassNotFoundException {
		Properties prop = new Properties();
		InputStream in = JdbcUtil.class.getClassLoader().getResourceAsStream("conn.properties");
		prop.load(in);
		Class.forName(prop.getProperty("jdbc.driver"));
		try {
			conn = (Connection) DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"),
					prop.getProperty("password"));
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
	}
	public Connection getConnection(){
		return conn;
	}
	public static synchronized JdbcUtil getInstance() throws ClassNotFoundException, IOException {
		if (jdbcUtil == null) {
			jdbcUtil = new JdbcUtil();
		}
		return jdbcUtil;
	}
	public void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
