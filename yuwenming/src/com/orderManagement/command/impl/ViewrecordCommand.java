package com.orderManagement.command.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.orderManagement.command.UserCommand;
import com.orderManagement.dao.MyConnection;
import com.orderManagement.dao.SelectDao;
import com.orderManagement.pojo.User;
import com.orderManagement.utils.InputConsole;

public class ViewrecordCommand extends UserCommand {

	public ViewrecordCommand(User userProFile) {
		super(userProFile);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {

		ResultSet rs = SelectDao.viewRecord(userProFile.getId());
		try {
			if (rs.next()) {
				rs.beforeFirst();
				InputConsole.println("The following is the transaction record");
				while (rs.next()) {
					InputConsole.println(rs.getInt(1) + " " + rs.getInt(2) + " " + rs.getDouble(3) + " "
							+ rs.getString(5) + " " + rs.getString(6) + " " + rs.getString(7));
				}
			} else {
				InputConsole.println("You have no orders");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			MyConnection.clean(SelectDao.conn, SelectDao.stmt, rs);
		}

	}

}
