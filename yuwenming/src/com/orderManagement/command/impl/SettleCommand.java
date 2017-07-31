package com.orderManagement.command.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.orderManagement.command.UserCommand;
import com.orderManagement.dao.MyConnection;
import com.orderManagement.dao.SelectDao;
import com.orderManagement.dao.UpdateDao;
import com.orderManagement.pojo.User;
import com.orderManagement.utils.InputConsole;

public class SettleCommand extends UserCommand {

	public SettleCommand(User userProFile) {
		super(userProFile);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		ResultSet orderrs = SelectDao.selectOrderByUserid(userProFile.getId());
		try {
			if (orderrs.next()) {
				orderrs.beforeFirst();
				boolean flag = false;
				while (orderrs.next()) {
					InputConsole.println(orderrs.getInt(1) + " " + orderrs.getDouble(3) + " " + orderrs.getString(5)
							+ " " + orderrs.getString(6) + " " + orderrs.getString(7));
				}

				int orderid = Integer
						.valueOf(InputConsole.askUserInput("Please enter the orderid that you want to settle"));
				double totalAmount = SelectDao.selectOrderAmount(orderid);
				double usermoney = SelectDao.selectMoney(userProFile.getId());
				ResultSet ordercartrs = SelectDao.selectOrdercartByOrderid(orderid);
				while (ordercartrs.next()) {
					if (ordercartrs.getInt(2) >= SelectDao.selectGoodsInventory(orderid))
						flag = false;
					else
						flag = true;
				}
				
				if (totalAmount <= usermoney && flag == true) {
					UpdateDao.charge(userProFile.getId(), SelectDao.selectOrderAmount(orderid));
					
					ResultSet ordercartrs2 = SelectDao.selectOrdercartByOrderid(orderid);
					while (ordercartrs2.next()) {
						UpdateDao.receipt(SelectDao.selectUseridByGoodsid(ordercartrs2.getInt(3)),
								SelectDao.selectGoodsPrice(ordercartrs2.getInt(3), userProFile.getId())
										* ordercartrs2.getInt(2));
						
						UpdateDao.reduceInventory(ordercartrs2.getInt(3), ordercartrs2.getInt(2));
						
						UpdateDao.deleteOrder(orderid);
						
						InputConsole.println("The transaction was successful");
					}
				} else {
					InputConsole
							.println("settle failed,Your money may be insufficient or the inventory is insufficient");
				}
			} else {
				InputConsole.println("You have no orders");
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
