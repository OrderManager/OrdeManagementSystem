package team.kirohuji.OrderManagerSystem.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class JdbcUtil {
	private SqlSessionFactory sqlSessionFactory;
	//private Properties pt = new Properties();
	private static JdbcUtil jdbcUtil = null;

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public JdbcUtil() throws ClassNotFoundException, IOException {
		openConnection();
	}

	public void openConnection() throws IOException, ClassNotFoundException {
		String name = "mybatis.xml";
		InputStream input = JdbcUtil.class.getClassLoader().getResourceAsStream(name);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(input);
		input.close();
	}
	public void closeConnection(){
		
	}
	public static synchronized JdbcUtil getInstance() throws ClassNotFoundException, IOException {
		if (jdbcUtil == null) {
			jdbcUtil = new JdbcUtil();
		}
		return jdbcUtil;
	}
}
