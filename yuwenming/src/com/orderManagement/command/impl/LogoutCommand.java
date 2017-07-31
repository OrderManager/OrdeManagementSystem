package com.orderManagement.command.impl;

import com.orderManagement.command.UserCommand;
import com.orderManagement.main.OrderManagementMain;
import com.orderManagement.pojo.User;
import com.orderManagement.utils.InputConsole;

public class LogoutCommand extends UserCommand {

	public LogoutCommand(User userProFile) {
		super(userProFile);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {

		userProFile = null;
		OrderManagementMain.getInstance().setUserfile(userProFile);
		InputConsole.println("You have loged out successfully");
		
	}

}
