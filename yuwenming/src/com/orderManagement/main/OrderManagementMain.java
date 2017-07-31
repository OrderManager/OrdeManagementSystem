package com.orderManagement.main;

import com.orderManagement.command.Command;
import com.orderManagement.command.CommandFactory;
import com.orderManagement.command.CommandFactory.CommandCode;
import com.orderManagement.pojo.User;
import com.orderManagement.utils.InputConsole;

public class OrderManagementMain {

	private User userProfile;

	private static OrderManagementMain instance = null;

	private OrderManagementMain() {

	}

	public static OrderManagementMain getInstance() {
		if (instance == null) {
			instance = new OrderManagementMain();
		}
		return instance;
	}

	public User getUserfile() {
		return userProfile;
	}

	public void setUserfile(User userProfile) {
		this.userProfile = userProfile;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InputConsole.println("Welcome to the best mall!");
		InputConsole.println("You can use the \"HELP\" for command");

		while (true) {
			String cmd = InputConsole.askUserInput("cmd");
			CommandCode cc = null;

			try {
				cc = CommandCode.valueOf(cmd.toUpperCase());
			} catch (Exception e) {
				InputConsole.println("Invalid input.Empty value is bot allowed!");
				continue;
			}

			User user = OrderManagementMain.getInstance().getUserfile();
			CommandFactory commandFactory = CommandFactory.builderFactory(user);

			Command command = commandFactory.buildeCommand(cc);
			if (command != null) {
				command.execute();
			} //
		}
	}

}
