package com.orderManagement.command.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.orderManagement.command.UserCommand;
import com.orderManagement.dao.MyConnection;
import com.orderManagement.dao.SelectDao;
import com.orderManagement.dao.UpdateDao;
import com.orderManagement.pojo.User;
import com.orderManagement.utils.InputConsole;

public class OffCommand extends UserCommand {

	public OffCommand(User userProFile) {
		super(userProFile);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		ResultSet rs = SelectDao.selectGoodsByShopid(userProFile.getId());

		try {
			if (rs.next()) {
				rs.beforeFirst();
				while (rs.next()) {
					InputConsole.println(rs.getInt(1) + " " + rs.getDouble(2) + " " + rs.getString(3) + " "
							+ rs.getString(4) + " " + rs.getInt(6) + " " + rs.getInt(7));
				}

				int goodsid = Integer
						.valueOf(InputConsole.askUserInput("Please enter the goodsid that you want to off the shelf"));
				int deleteGoods = UpdateDao.deleteGoods(goodsid);
				if (deleteGoods > 0)
					InputConsole.println("Delete the goods successfully");
				else
					InputConsole.println("Delete the goods failed");

			} else {
				InputConsole.println("You have no goods on the shelf");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			MyConnection.clean(SelectDao.conn, SelectDao.stmt, SelectDao.rs);
			MyConnection.clean(UpdateDao.conn, UpdateDao.stmt, null);
		}
	}

}
