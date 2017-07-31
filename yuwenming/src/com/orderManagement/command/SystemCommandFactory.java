package com.orderManagement.command;

import java.util.HashMap;
import java.util.Map;

import com.orderManagement.command.impl.ExitCommand;
import com.orderManagement.command.impl.HelpCommand;
import com.orderManagement.command.impl.LoginCommand;
import com.orderManagement.command.impl.SignupCommand;

public class SystemCommandFactory extends CommandFactory {

	private static Map<CommandCode, Command> commandMap = new HashMap<>();

	static {
		commandMap.put(CommandCode.EXIT, new ExitCommand());
		commandMap.put(CommandCode.HELP, new HelpCommand());
		commandMap.put(CommandCode.SIGNUP, new SignupCommand());
		commandMap.put(CommandCode.LOGIN, new LoginCommand());
	}

	@Override
	public Command buildeCommand(CommandCode cmd) {
		// TODO Auto-generated method stub
		Command command = commandMap.get(cmd);
		return command;
	}

}
