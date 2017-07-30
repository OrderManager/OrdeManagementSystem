package com.orderManagement.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class InsertDao {
	public static Connection conn = null;
	public static java.sql.PreparedStatement stmt = null;

	public static int signup(String username, String password, String phone, String address, double money, int ruleid) {

		try {
			conn = MyConnection.getConnection();
			stmt = conn.prepareStatement(
					"insert into user (name,password,phone,address,money,rule_id,id) values(?,?,?,?,?,?,?)");
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.setString(3, phone);
			stmt.setString(4, address);
			stmt.setDouble(5, money);
			stmt.setInt(6, ruleid);
			stmt.setInt(7, (int) System.currentTimeMillis());

			return stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			MyConnection.clean(conn, stmt, null);
		}

		return 0;
	}

	public static int addGoods(double price, String name, String content, int inventory, int shopid) {

		try {
			conn = MyConnection.getConnection();
			stmt = conn.prepareStatement(
					"insert into goods (price,name,content,inventory,id,shop_id) values(?,?,?,?,?,?)");
			stmt.setDouble(1, price);
			stmt.setString(2, name);
			stmt.setString(3, content);
			stmt.setInt(4, inventory);
			stmt.setInt(5, (int) System.currentTimeMillis());
			stmt.setInt(6, shopid);

			return stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			MyConnection.clean(conn, stmt, null);
		}

		return 0;
	}

	public static int addShop(String name, String content, int userid) {

		try {
			conn = MyConnection.getConnection();
			stmt = conn.prepareStatement("insert into shop (id,is_open,name,content,user_id) values(?,?,?,?,?)");
			stmt.setInt(1, (int) System.currentTimeMillis());
			stmt.setInt(2, 1);
			stmt.setString(3, name);
			stmt.setString(4, content);
			stmt.setInt(5, userid);

			return stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			MyConnection.clean(conn, stmt, null);
		}

		return 0;
	}

	public static int addToOrderCart(int goodsid, int number, int userid) {

		try {
			conn = MyConnection.getConnection();
			stmt = conn.prepareStatement("insert into order_cart (id,number,goods_id,user_id) values(?,?,?,?)");
			stmt.setInt(1, (int) System.currentTimeMillis());
			stmt.setInt(2, number);
			stmt.setInt(3, goodsid);
			stmt.setInt(4, userid);

			return stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			MyConnection.clean(conn, stmt, null);
		}

		return 0;
	}

	public static int addOrder(double totalAmount, int userid, String receiver, String address, String phone) {

		try {
			conn = MyConnection.getConnection();
			stmt = conn.prepareStatement(
					"insert into `order` (id,status,totalamount,user_id,receiver,address,phone) values(?,?,?,?,?,?,?)");
			stmt.setInt(1, (int) System.currentTimeMillis());
			stmt.setInt(2, 0);
			stmt.setDouble(3, totalAmount);
			stmt.setInt(4, userid);
			stmt.setString(5, receiver);
			stmt.setString(6, address);
			stmt.setString(7, phone);

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
