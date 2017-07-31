package com.orderManagement.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class UpdateDao {
	public static Connection conn = null;
	public static java.sql.PreparedStatement stmt = null;

	public static int deleteGoods(int goodsid) {

		try {
			conn = MyConnection.getConnection();
			stmt = conn.prepareStatement("update goods set is_delete=1 where id=?");
			stmt.setInt(1, goodsid);

			return stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			MyConnection.clean(conn, stmt, null);
		}

		return 0;
	}

	public static int deleteOrder(int orderid) {

		try {
			conn = MyConnection.getConnection();
			stmt = conn.prepareStatement("update `order` set status=1 where id=?");
			stmt.setInt(1, orderid);

			return stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			MyConnection.clean(conn, stmt, null);
		}

		return 0;
	}

	public static int deleteShop(int shopid) {

		try {
			conn = MyConnection.getConnection();
			stmt = conn.prepareStatement("update shop set is_open=0 where id=?");
			stmt.setInt(1, shopid);

			return stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			MyConnection.clean(conn, stmt, null);
		}

		return 0;
	}

	public static int setOrderid(int orderid, int userid){

		try {
			conn = MyConnection.getConnection();
			stmt = conn.prepareStatement("update order_cart set order_id=? where order_id=0 and user_id=?");
			stmt.setInt(1, orderid);
			stmt.setInt(2, userid);

			return stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			MyConnection.clean(conn, stmt, null);
		}

		return 0;
	}

	public static int charge(int userid, double orderAmount) {

		try {
			conn = MyConnection.getConnection();
			stmt = conn.prepareStatement("update user set money=money-? where id=?");
			stmt.setDouble(1, orderAmount);
			stmt.setInt(2, userid);

			return stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			MyConnection.clean(conn, stmt, null);
		}

		return 0;
	}

	public static int receipt(int userid, double goodsAmount) {

		try {
			conn = MyConnection.getConnection();
			stmt = conn.prepareStatement("update user set money=money+? where id=?");
			stmt.setDouble(1, goodsAmount);
			stmt.setInt(2, userid);

			return stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			MyConnection.clean(conn, stmt, null);
		}

		return 0;
	}

	public static int reduceInventory(int goodsid, int number) {

		try {
			conn = MyConnection.getConnection();
			stmt = conn.prepareStatement("update goods set inventory=inventory-? where id=?");
			stmt.setInt(1, number);
			stmt.setInt(2, goodsid);

			return stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			MyConnection.clean(conn, stmt, null);
		}

		return 0;
	}

}
