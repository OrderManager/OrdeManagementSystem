package com.orderManagement.command.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.orderManagement.command.SystemCommand;
import com.orderManagement.dao.MyConnection;
import com.orderManagement.dao.SelectDao;
import com.orderManagement.main.OrderManagementMain;
import com.orderManagement.pojo.User;
import com.orderManagement.utils.InputConsole;

public class LoginCommand extends SystemCommand {

	@Override
	public void execute() {

		String username = InputConsole.askUserInput("Please enter your username£º");
		String password = InputConsole.askUserInput("Please enter your password£º");

		ResultSet rs = SelectDao.login(username, password);
		try {
			if (rs.next()) {
				rs.beforeFirst();
				while (rs.next()) {
					User user = null;
					user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5), rs.getDouble(6), rs.getInt(7));
					OrderManagementMain.getInstance().setUserfile(user);

					InputConsole.println("Login Successfully");
				}
			} else {
				InputConsole.println("Your username or password is false");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			MyConnection.clean(SelectDao.conn, SelectDao.stmt, SelectDao.rs);
		}

	}

}
