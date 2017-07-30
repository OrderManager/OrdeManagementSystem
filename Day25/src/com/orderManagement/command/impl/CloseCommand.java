package com.orderManagement.command.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.orderManagement.command.UserCommand;
import com.orderManagement.dao.MyConnection;
import com.orderManagement.dao.SelectDao;
import com.orderManagement.dao.UpdateDao;
import com.orderManagement.pojo.User;
import com.orderManagement.utils.InputConsole;

public class CloseCommand extends UserCommand {

	public CloseCommand(User userProFile) {
		super(userProFile);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		ResultSet selectShop = SelectDao.selectShop(userProFile.getId());
		try {
			if (selectShop.next()) {
				selectShop.beforeFirst();
				while (selectShop.next()) {
					InputConsole.println(
							selectShop.getInt(1) + " " + selectShop.getString(2) + " " + selectShop.getString(3));
				}
				int shopid = Integer
						.valueOf(InputConsole.askUserInput("Please enter the shopid that you want to close"));
				int deleteShop = UpdateDao.deleteShop(shopid);
				if (deleteShop > 0)
					InputConsole.println("Close the shop successfully");
				else
					InputConsole.println("Close the shop failed");
			} else {
				InputConsole.println("You have no shop");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			MyConnection.clean(SelectDao.conn, SelectDao.stmt, SelectDao.rs);
			MyConnection.clean(UpdateDao.conn, UpdateDao.stmt, null);
		}

	}

}
