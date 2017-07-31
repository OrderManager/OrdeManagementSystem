package team.kirohuji.OrderManagerSystem.util;

import java.io.IOException;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class Errors {
	private JdbcUtil jdbc = null;
	private SqlSession sqlSession;
	private static Errors errors;
	public Errors() {
		try {
			jdbc = JdbcUtil.getInstance();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Errors getInstance(){
		if(errors==null){
			return errors=new Errors();
		}else{
			return errors;
		}
	}
	public boolean RenameProcessing(Object object,String tableName){
		
		return false;
	}
}
