package team.kirohuji.OrderManagerSystem.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import team.kirohuji.OrderManagerSystem.entity.BeSuper;
import team.kirohuji.OrderManagerSystem.entity.CommandManager;
import team.kirohuji.OrderManagerSystem.entity.User;
import team.kirohuji.OrderManagerSystem.service.QingkeConsole;
import team.kirohuji.OrderManagerSystem.util.JdbcUtil;

public class BeSuperLauncher {
	private final static String EXIT = "exit";
	private final static String HELP = "help";
	private final static String ASK = "ask";
	private final static String ANSWER = "answer";
	private final static String LIST = "list";
	private final static String SCORE = "score";
	private final static String REGISTER = "register";
	private final static String LOGIN = "login";
//	private final static String 
	private String command;
	private User player = null;
	private JdbcUtil jdbc;
	private CommandManager systemCommandManager;
	private CommandManager consoleCommandManager;

	public boolean execute() throws SQLException {
		load();
		QingkeConsole.print("Welcome to play this game");
		QingkeConsole.println("Nice to meet you,You can type 'help' for usage");
		QingkeConsole.println("If no account please register first");
		// ������Ϣ
		while (true) {
			boolean flag = false;
			command = QingkeConsole.askUserInput("cmd> ");
			if (command.equalsIgnoreCase(LOGIN)) {
				try {
					systemCommandManager.getSystemCommand(command);
					player = systemCommandManager.execute();
					if(player!=null){
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				QingkeConsole.println("Don't have this command, please enter again");
			}
		}
		// ����ģʽ
		QingkeConsole.println("You can type 'help' for usage");
		while (true) {
			command = QingkeConsole.askUserInput("cmd> ");
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
				QingkeConsole.println("Don't have this command, please enter again");
			}
		}
		return true;
	}

	private JdbcUtil loadBeSuper() {
		try {
			return JdbcUtil.getInstance();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		beSuper = new BeSuper();
		return jdbc;
	}

	private void load() {
		CommandManagerProducer cmp = new CommandManagerProducer();
		jdbc = loadBeSuper();
		systemCommandManager = cmp.getFactory("System").getInstanceSystemManager();
		consoleCommandManager = cmp.getFactory("Console").getInstanceConsoleManager();
	}

}
