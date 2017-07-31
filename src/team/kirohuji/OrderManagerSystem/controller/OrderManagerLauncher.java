package team.kirohuji.OrderManagerSystem.controller;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;

import team.kirohuji.OrderManagerSystem.dao.imp.InstructImp;
import team.kirohuji.OrderManagerSystem.entity.CommandManager;
import team.kirohuji.OrderManagerSystem.entity.CommandType;
import team.kirohuji.OrderManagerSystem.entity.Instruct;
import team.kirohuji.OrderManagerSystem.entity.User;
import team.kirohuji.OrderManagerSystem.util.CommandTypeJudge;
import team.kirohuji.OrderManagerSystem.util.Errors;
import team.kirohuji.OrderManagerSystem.util.JdbcUtil;
import team.kirohuji.OrderManagerSystem.util.OrderManagerConsole;

public class OrderManagerLauncher {
	private Instruct instruct = null;
	private User player = null;
	private JdbcUtil jdbc = null;
	private Errors erros=null;
	private CommandManager systemCommandManager = null;
	private CommandManager consoleCommandManager = null;
	private SqlSession sqlSession;
	private CommandTypeJudge commandTypeJudge;

	public OrderManagerLauncher() {

	}

	public boolean execute() throws SQLException {
		load();
		OrderManagerConsole.print("OrderManagerSystem version 1.0");
		OrderManagerConsole.println("Nice to meet you,Enter \"help\" for usage hints.");
		OrderManagerConsole.println("If no account please register first");
	//	OrderManagerConsole.printUsage(container,OrderManagerConsole.SYSTEMCOMMAND);
		while (true) {
			instruct = EncapsulationCommand(OrderManagerConsole.askUserInput(OrderManagerConsole.COMMANDLINE+"> "));
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
		systemCommandManager = cmp.getFactory("System").getInstanceSystemManager();
		consoleCommandManager = cmp.getFactory("Console").getInstanceConsoleManager();
		commandTypeJudge = new CommandTypeJudge();
		erros=Errors.getInstance();
	}

	private Instruct EncapsulationCommand(String command) {
		return new InstructImp().selectByName(command.toLowerCase());
	}

}
