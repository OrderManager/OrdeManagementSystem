package com.orderManagement.command.impl;

import com.orderManagement.command.UserCommand;
import com.orderManagement.dao.InsertDao;
import com.orderManagement.pojo.User;
import com.orderManagement.utils.InputConsole;

public class BuyCommand extends UserCommand {

	public BuyCommand(User userProFile) {
		super(userProFile);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {

		GoodsCommand gc = new GoodsCommand(userProFile);

		while (true) {
			int goodsid = Integer.valueOf(InputConsole.askUserInput("Please enter the goodsid that you need to buy"));
			int number = Integer.valueOf(InputConsole.askUserInput("Pleas enter the goods'number"));

			if (number <= gc.goodslist.get(goodsid)) {
				int addResult = InsertDao.addToOrderCart(goodsid, number, userProFile.getId());
				InputConsole.println("Successfully added to the shopping cart");
			} else {
				InputConsole.println("The number of goods is insufficient");
			}

			String want = InputConsole.askUserInput("Do yu want to continue to buy goods ");
			if (want.equalsIgnoreCase("YES")) {
				continue;
			} else {
				break;
			}
		}

	}

}
