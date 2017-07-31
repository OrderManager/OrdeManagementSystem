package com.orderManagement.command.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.orderManagement.command.UserCommand;
import com.orderManagement.dao.MyConnection;
import com.orderManagement.dao.SelectDao;
import com.orderManagement.pojo.User;
import com.orderManagement.utils.InputConsole;

public class GoodsCommand extends UserCommand {

	public static Map<Integer, Integer> goodslist = new HashMap<>();

	public GoodsCommand(User userProFile) {
		super(userProFile);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {

		ResultSet selectGoods = SelectDao.selectGoods();

		try {
			if (selectGoods.next()) {
				selectGoods.beforeFirst();
				InputConsole.println("The following is the goods information");
				while (selectGoods.next()) {
					InputConsole.println(selectGoods.getInt(1) + " " + selectGoods.getString(3) + " "
							+ selectGoods.getDouble(2) + " " + selectGoods.getString(4) + " " + selectGoods.getInt(6));
					goodslist.put(selectGoods.getInt(1), selectGoods.getInt(6));
				}
			} else {
				InputConsole.print("There is currently no goods information");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			MyConnection.clean(SelectDao.conn, SelectDao.stmt, SelectDao.rs);
		}

	}

}
