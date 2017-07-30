package com.orderManagement.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectDao {
	public static Connection conn = null;
	public static java.sql.PreparedStatement stmt = null;
	public static ResultSet rs = null;

	public static ResultSet login(String name, String password) {

		try {
			conn = MyConnection.getConnection();
			stmt = conn.prepareStatement("select * from user where name=? and password=?");
			stmt.setString(1, name);
			stmt.setString(2, password);

			rs = stmt.executeQuery();
			if (rs != null) {
				return rs;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public static boolean isHavingUser(String username) {

		try {
			conn = MyConnection.getConnection();
			stmt = conn.prepareStatement("select * from user where name=?");
			stmt.setString(1, username);

			rs = stmt.executeQuery();
			while (rs.next()) {
				if (rs.getString(2) != null) {
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public static double selectMoney(int userid) {

		try {
			conn = MyConnection.getConnection();
			stmt = conn.prepareStatement("select money from user where id=?");
			stmt.setInt(1, userid);

			rs = stmt.executeQuery();
			while (rs.next()) {
				return rs.getDouble(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	public static int selectUseridByGoodsid(int goodsid) {

		try {
			conn = MyConnection.getConnection();
			stmt = conn.prepareStatement("select user_id from shop where id=(select shop_id from goods where id=?)");
			stmt.setInt(1, goodsid);

			rs = stmt.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	public static ResultSet selectRulemsg() {

		try {
			conn = MyConnection.getConnection();
			stmt = conn.prepareStatement("select * from rule");

			rs = stmt.executeQuery();
			if (rs != null) {
				return rs;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public static ResultSet selectGoods() {

		try {
			conn = MyConnection.getConnection();
			stmt = conn.prepareStatement("select * from goods where is_delete=0");

			rs = stmt.executeQuery();
			if (rs != null) {
				return rs;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public static ResultSet selectGoodsByShopid(int userid) {

		try {
			conn = MyConnection.getConnection();
			stmt = conn.prepareStatement(
					"select * from goods where is_delete=0 and shop_id in (select id from shop where user_id=?)");
			stmt.setInt(1, userid);

			rs = stmt.executeQuery();
			if (rs != null) {
				return rs;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public static int selectGoodsInventory(int orderid) {

		try {
			conn = MyConnection.getConnection();
			stmt = conn.prepareStatement(
					"select inventory from goods where is_delete=0 and id=(select goods_id from order_cart where order_id=?)");
			stmt.setInt(1, orderid);

			rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					return rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	public static ResultSet selectOrdercartByUserid(int userid) {

		try {
			conn = MyConnection.getConnection();
			stmt = conn.prepareStatement("select * from order_cart where user_id=? and order_id=0");
			stmt.setInt(1, userid);

			rs = stmt.executeQuery();
			if (rs != null) {
				return rs;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public static ResultSet selectOrdercartByOrderid(int orderid) {

		try {
			conn = MyConnection.getConnection();
			stmt = conn.prepareStatement("select * from order_cart where order_id=?");
			stmt.setInt(1, orderid);

			rs = stmt.executeQuery();
			if (rs != null) {
				return rs;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public static int selectGoodsNumber(int orderid) {

		try {
			conn = MyConnection.getConnection();
			stmt = conn.prepareStatement("select number from order_cart where order_id=?");
			stmt.setInt(1, orderid);

			rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					return rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	public static ResultSet selectOrderid(int userid) {

		try {
			conn = MyConnection.getConnection();
			stmt = conn.prepareStatement("select id from `order` where user_id=? and status=0");
			stmt.setInt(1, userid);

			rs = stmt.executeQuery();
			if (rs != null) {
				return rs;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public static ResultSet selectOrderByUserid(int userid) {

		try {
			conn = MyConnection.getConnection();
			stmt = conn.prepareStatement("select * from `order` where user_id=? and status=0");
			stmt.setInt(1, userid);

			rs = stmt.executeQuery();
			if (rs != null) {
				return rs;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public static double selectOrderAmount(int orderid) {

		try {
			conn = MyConnection.getConnection();
			stmt = conn.prepareStatement("select totalamount from `order` where id=? and status=0");
			stmt.setInt(1, orderid);

			rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					return rs.getDouble(1);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	public static ResultSet selectShop(int userid) {

		try {
			conn = MyConnection.getConnection();
			stmt = conn.prepareStatement("select * from shop where user_id=?");
			stmt.setInt(1, userid);

			rs = stmt.executeQuery();
			if (rs != null) {
				return rs;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public static double selectGoodsPrice(int goodsid, int userid) {

		try {
			conn = MyConnection.getConnection();
			stmt = conn.prepareStatement("select price from goods,order_cart where 	"
					+ "goods.id=order_cart.goods_id and order_cart.goods_id=? and user_id=?");
			stmt.setInt(1, goodsid);
			stmt.setInt(2, userid);

			rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					return rs.getDouble(1);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

}
