package team.kirohuji.OrderManagerSystem.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import team.kirohuji.OrderManagerSystem.dao.imp.InstructImp;
import team.kirohuji.OrderManagerSystem.entity.CommandManager;
import team.kirohuji.OrderManagerSystem.entity.CommandType;
import team.kirohuji.OrderManagerSystem.entity.Container;
import team.kirohuji.OrderManagerSystem.entity.Instruct;
import team.kirohuji.OrderManagerSystem.entity.User;
import team.kirohuji.OrderManagerSystem.util.CommandTypeJudge;
import team.kirohuji.OrderManagerSystem.util.JdbcUtil;
import team.kirohuji.OrderManagerSystem.util.OrderManagerConsole;

public class OrderManagerLauncher {

	private Container<Instruct> container = null;
	private Instruct instruct = null;
	private User player = null;
	private JdbcUtil jdbc = null;
	private CommandManager systemCommandManager = null;
	private CommandManager consoleCommandManager = null;
	private SqlSession sqlSession;
	private CommandTypeJudge commandTypeJudge;

	public OrderManagerLauncher() {

	}

	public boolean execute() throws SQLException {
		load();
		OrderManagerConsole.print("Welcome to play this game");
		OrderManagerConsole.println("Nice to meet you,You can type 'help' for usage");
		OrderManagerConsole.println("If no account please register first");
		OrderManagerConsole.printUsage(container,OrderManagerConsole.SYSTEMCOMMAND);
		while (true) {
			instruct = EncapsulationCommand(OrderManagerConsole.askUserInput("cmd> "));
			if (instruct != null) {
				if (instruct.getName().equalsIgnoreCase("exit")){
					sqlSession.close();
					break;
				}
				CommandType commandType = commandTypeJudge.Judge(instruct);
				switch (commandType) {
				case SYSTEMCOMMAND:
					try {
						systemCommandManager.getSystemCommand(instruct);
						player = systemCommandManager.execute(commandType.SYSTEMCOMMAND);
						continue;
					} catch (ClassNotFoundException | IOException e) {
						e.printStackTrace();
					}
					break;
				case SELLER:
					try {
						consoleCommandManager.getConsoleCommand(instruct);
						consoleCommandManager.execute(player,commandType.SELLER);
						continue;
					} catch (ClassNotFoundException | IOException e) {
						e.printStackTrace();
					}
					break;
				case BUYER:
					try {
						consoleCommandManager.getConsoleCommand(instruct);
						consoleCommandManager.execute(player,commandType.BUYER);
						continue;
					} catch (ClassNotFoundException | IOException e) {
						e.printStackTrace();
					}
					break;
				default:
					break;
				}
			} else {
				OrderManagerConsole.println("Don't have this command, please enter again");
			}
		}return true;
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
		sqlSession = jdbc.getSqlSessionFactory().openSession();
		SetContainer();
		systemCommandManager = cmp.getFactory("System").getInstanceSystemManager();
		consoleCommandManager = cmp.getFactory("Console").getInstanceConsoleManager();
		systemCommandManager.setInstructSet(container);
		consoleCommandManager.setInstructSet(container);
		commandTypeJudge = new CommandTypeJudge();
		systemCommandManager.setUserInstructSet(container);
	}

	private Instruct EncapsulationCommand(String command) {
		return new InstructImp().selectByName(command);
	}

	private void SetContainer() {
		container = new Container<Instruct>();
		List<Instruct> lists = new InstructImp().selectAll();
		Iterator<Instruct> it = lists.iterator();
		while (it.hasNext()) {
			Instruct instruct = it.next();
			container.insert(instruct);
		}
	}
}
