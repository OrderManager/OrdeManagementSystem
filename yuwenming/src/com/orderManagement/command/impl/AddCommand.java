package com.orderManagement.command.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.orderManagement.command.UserCommand;
import com.orderManagement.dao.InsertDao;
import com.orderManagement.dao.MyConnection;
import com.orderManagement.dao.SelectDao;
import com.orderManagement.pojo.User;
import com.orderManagement.utils.InputConsole;

public class AddCommand extends UserCommand {

	public AddCommand(User userProFile) {
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
						.valueOf(InputConsole.askUserInput("Please enter the shopid where you want to add goods"));
				String goodsname = InputConsole.askUserInput("Please enter the goods name");
				double price = Double.valueOf(InputConsole.askUserInput("Please enter the goods price"));
				String content = InputConsole.askUserInput("Please enter the goods content");
				int inventory = Integer.valueOf(InputConsole.askUserInput("Please enter the goods inventory"));

				int addGoods = InsertDao.addGoods(price, goodsname, content, inventory, shopid);
				if (addGoods > 0)
					InputConsole.println("Add new goods successfully");
				else
					InputConsole.println("Add new goods failed");
			} else {
				InputConsole.println("You have no shop");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			MyConnection.clean(SelectDao.conn, SelectDao.stmt, SelectDao.rs);
			MyConnection.clean(InsertDao.conn, InsertDao.stmt, null);
		}

	}

}
