package com.orderManagement.command;

import com.orderManagement.pojo.User;

public abstract class UserCommand implements Command {

	protected static User userProFile;

	public UserCommand(User userProFile) {
		this.userProFile = userProFile;
	}

}
