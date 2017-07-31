package com.orderManagement.command.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.orderManagement.command.UserCommand;
import com.orderManagement.dao.MyConnection;
import com.orderManagement.dao.SelectDao;
import com.orderManagement.pojo.User;
import com.orderManagement.utils.InputConsole;

public class OrderCartCommand extends UserCommand {

	public OrderCartCommand(User userProFile) {
		super(userProFile);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		InputConsole.println("The following is your shopping cart information");
		ResultSet rs = SelectDao.selectOrderByUserid(userProFile.getId());

		try {
			if (rs.next()) {
				rs.beforeFirst();
				while (rs.next()) {
					InputConsole.println(rs.getInt(2) + " " + rs.getInt(3));
				}
			} else {
				InputConsole.println("You have no goods in your shopping cart");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			MyConnection.clean(SelectDao.conn, SelectDao.stmt, SelectDao.rs);
		}

	}

}
