package team.kirohuji.OrderManagerSystem.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import team.kirohuji.OrderManagerSystem.entity.CommandManager;
import team.kirohuji.OrderManagerSystem.entity.Instructs;
import team.kirohuji.OrderManagerSystem.entity.User;
import team.kirohuji.OrderManagerSystem.service.OrderManagerConsole;
import team.kirohuji.OrderManagerSystem.util.JdbcUtil;

public class OrderManagerLauncher {
	private String command;
	private User player = null;
	private JdbcUtil jdbc;
	private CommandManager systemCommandManager;
	private CommandManager consoleCommandManager;
	public OrderManagerLauncher(){
	}
	public boolean execute() throws SQLException {
		load();
		OrderManagerConsole.print("Welcome to play this game");
		OrderManagerConsole.println("Nice to meet you,You can type 'help' for usage");
		OrderManagerConsole.println("If no account please register first");
		while (true) {
			boolean flag = false;
			command = OrderManagerConsole.askUserInput("cmd> ");
			if (command.equalsIgnoreCase(LOGIN)) {
				try {
					systemCommandManager.getSystemCommand(command);
					player = systemCommandManager.execute();
					if (player != null) {
						break;
					}

				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
			} else if (command.equalsIgnoreCase(HELP)) {
				try {
					systemCommandManager.getSystemCommand(command);
					player = systemCommandManager.execute();
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
			} else if (command.equalsIgnoreCase(REGISTER)) {
				try {
					systemCommandManager.getSystemCommand(command);
					player = systemCommandManager.execute();
				} catch (ClassNotFoundException | IOException e) {
				
					e.printStackTrace();
				}
			} else {
				OrderManagerConsole.println("Don't have this command, please enter again");
			}
		}
	
		OrderManagerConsole.println("You can type 'help' for usage");
		while (true) {
			command = OrderManagerConsole.askUserInput("cmd> ");
			if (command.equalsIgnoreCase(EXIT)) {
				System.out.println("Bye Bye");
				break;
			} else if (command.equalsIgnoreCase(ASK) || command.equalsIgnoreCase(ANSWER)
					|| command.equalsIgnoreCase(LIST) || command.equalsIgnoreCase(SCORE)) {
				try {

					consoleCommandManager.getConsoleCommand(command);
					consoleCommandManager.execute(player);
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}

			} else if (command.equalsIgnoreCase(HELP)) {
				try {
					systemCommandManager.getSystemCommand(command);
					systemCommandManager.execute();
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
			} else {
				OrderManagerConsole.println("Don't have this command, please enter again");
			}
		}
		return true;
	}
	private JdbcUtil loadBeSuper() {
		try {
			return JdbcUtil.getInstance();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return jdbc;
	}

	private void load() {
		CommandManagerProducer cmp = new CommandManagerProducer();
		jdbc = loadBeSuper();
		systemCommandManager = cmp.getFactory("System").getInstanceSystemManager();
		consoleCommandManager = cmp.getFactory("Console").getInstanceConsoleManager();
	}

}
