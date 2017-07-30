package com.orderManagement.command;

import com.orderManagement.pojo.User;

public abstract class CommandFactory {

	public static enum CommandCode {
		// System
		EXIT, HELP, LOGIN, SIGNUP,LOGOUT,

		// User
		GOODS, BUY, ORDERCART,GENERATE, SETTLE, SHOP, CLOSE, ADD, OFF
	}

	public static CommandFactory builderFactory(User user) {
		if (user == null) {
			return new SystemCommandFactory();
		}
		return new UserCommandFactory(user);
	}

	public abstract Command buildeCommand(CommandCode cmd);

}
