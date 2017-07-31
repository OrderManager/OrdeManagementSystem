package com.orderManagement.command.impl;

import com.orderManagement.command.UserCommand;
import com.orderManagement.dao.InsertDao;
import com.orderManagement.pojo.User;
import com.orderManagement.utils.InputConsole;

public class ShopCommand extends UserCommand {

	public ShopCommand(User userProFile) {
		super(userProFile);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		String shopname = InputConsole.askUserInput("Please enter your shop name");
		String content = InputConsole.askUserInput("Please enter your shop content");

		int addShop = InsertDao.addShop(shopname, content, userProFile.getId());
		if (addShop > 0)
			InputConsole.println("Add a new shop successfully");
		else
			InputConsole.println("Add a new shop failed");

	}

}
