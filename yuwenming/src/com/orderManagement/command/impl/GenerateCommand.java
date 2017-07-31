package com.orderManagement.command.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.orderManagement.command.UserCommand;
import com.orderManagement.dao.InsertDao;
import com.orderManagement.dao.MyConnection;
import com.orderManagement.dao.SelectDao;
import com.orderManagement.dao.UpdateDao;
import com.orderManagement.pojo.User;
import com.orderManagement.utils.InputConsole;

public class GenerateCommand extends UserCommand {

	public GenerateCommand(User userProFile) {
		super(userProFile);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {

		double totalAmount = calclatAmount(userProFile.getId());
		String receiver = InputConsole.askUserInput("Pleasr enter the receiver");
		String address = InputConsole.askUserInput("Pleasr enter the address");
		String phone = InputConsole.askUserInput("Pleasr enter the receiver's phone");

		if (totalAmount > 0) {
			int addOrder = InsertDao.addOrder(totalAmount, userProFile.getId(), receiver, address, phone);
			if (addOrder > 0) {
				ResultSet selectOrderid = SelectDao.selectOrderid(userProFile.getId());
				int lastOrderid = 0;
				try {
					if (selectOrderid.next()) {
						selectOrderid.beforeFirst();
						while (selectOrderid.next()) {
							if (selectOrderid.isLast()) {
								lastOrderid = selectOrderid.getInt(1);
							}
						}

						UpdateDao.setOrderid(lastOrderid, userProFile.getId());
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					MyConnection.clean(SelectDao.conn, SelectDao.stmt, SelectDao.rs);
					MyConnection.clean(InsertDao.conn, InsertDao.stmt, null);
					MyConnection.clean(UpdateDao.conn, UpdateDao.stmt, null);
				}
				InputConsole.println("Generate orders successfully");
			} else
				InputConsole.println("Generate orders failed");
		} else {
			InputConsole.println("Your shopping cart is empty,please add goods to your shopping cart");
		}

	}

	public static double calclatAmount(int userid) {

		double totalAmount = 0;
		ResultSet rs = SelectDao.selectOrdercartByUserid(userid);

		try {
			if (rs.next()) {
				rs.beforeFirst();
				while (rs.next()) {
					totalAmount += rs.getInt(2) * SelectDao.selectGoodsPrice(rs.getInt(3), userid);
				}
				return totalAmount;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			MyConnection.clean(SelectDao.conn, SelectDao.stmt, SelectDao.rs);
		}
		return 0;

	}

}
