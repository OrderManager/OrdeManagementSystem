package com.orderManagement.command.impl;

import com.orderManagement.command.UserCommand;
import com.orderManagement.main.OrderManagementMain;
import com.orderManagement.pojo.User;

public class LogoutCommand extends UserCommand {

	public LogoutCommand(User userProFile) {
		super(userProFile);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		userProFile = null;
		OrderManagementMain.getInstance().setUserfile(userProFile);
		
	}

}
