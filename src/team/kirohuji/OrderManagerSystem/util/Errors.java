package team.kirohuji.OrderManagerSystem.util;

import java.io.IOException;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class Errors {
	public static boolean RenameProcessing(Object object,String tableName){
		
		return false;
	}
	/**
	 * 进行对象是否为空判断
	 * @param object
	 * @return true 代表类型为空
	 */
	public static boolean NullPointerProcessing(Object object){
		if(object==null){
			return false;
		}else{
			return true;
		}
	}
	/**
	 * @param number
	 * @return
	 */
	public static boolean NullPointerProcessing(int number){
		if(number<=0){
			return false;
		}else{
			return true;
		}
	}
}
