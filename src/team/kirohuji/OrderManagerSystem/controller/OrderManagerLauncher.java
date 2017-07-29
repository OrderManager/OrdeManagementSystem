package team.kirohuji.OrderManagerSystem.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import team.kirohuji.OrderManagerSystem.entity.CommandManager;
import team.kirohuji.OrderManagerSystem.entity.Container;
import team.kirohuji.OrderManagerSystem.entity.Instruct;
import team.kirohuji.OrderManagerSystem.entity.User;
import team.kirohuji.OrderManagerSystem.util.JdbcUtil;
import team.kirohuji.OrderManagerSystem.util.OrderManagerConsole;

public class OrderManagerLauncher {
	private static final int SYSTEMCOMMAND = 1;
	private static final int USERCOMMAND = 2;
	private Container<Instruct> container;
	private Instruct instruct;
	// private String command;
	private User player = null;
	private JdbcUtil jdbc;
	private CommandManager systemCommandManager;
	private CommandManager consoleCommandManager;

	public OrderManagerLauncher() {
	}

	public boolean execute() throws SQLException {
		load();
		OrderManagerConsole.print("Welcome to play this game");
		OrderManagerConsole.println("Nice to meet you,You can type 'help' for usage");
		OrderManagerConsole.println("If no account please register first");
		while (true) {
			boolean flag = false;
			instruct = EncapsulationCommand(OrderManagerConsole.askUserInput("cmd> "));
			if (container.contains(instruct)) {
				if (instruct.getRuleId() == SYSTEMCOMMAND) {
					try {
						systemCommandManager.getSystemCommand(instruct);
						player = systemCommandManager.execute();
//						if (player != null) {
//							break;
//						}
					} catch (ClassNotFoundException | IOException e) {
						e.printStackTrace();
					}
				} else {
					break;
				}
			} else {
				OrderManagerConsole.println("Don't have this command, please enter again");
			}
		}
		OrderManagerConsole.println("You can type 'help' for usage");
		while (true) {
			instruct = EncapsulationCommand(OrderManagerConsole.askUserInput("cmd> "));
			if (container.contains(instruct)) {
				if (instruct.getRuleId() == SYSTEMCOMMAND) {
					break;
				} else {
					try {
						consoleCommandManager.getConsoleCommand(instruct);
						consoleCommandManager.execute(player);
					} catch (ClassNotFoundException | IOException e) {
						e.printStackTrace();
					}
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
		container = new Container(jdbc.getSqlSessionFactory().openSession());
		systemCommandManager = cmp.getFactory("System").getInstanceSystemManager();
		consoleCommandManager = cmp.getFactory("Console").getInstanceConsoleManager();
	}

	private Instruct EncapsulationCommand(String command) {
		return new Instruct();
	}
}
