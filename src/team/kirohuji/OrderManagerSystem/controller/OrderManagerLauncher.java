package team.kirohuji.OrderManagerSystem.controller;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;

import team.kirohuji.OrderManagerSystem.dao.imp.InstructImp;
import team.kirohuji.OrderManagerSystem.entity.CommandManager;
import team.kirohuji.OrderManagerSystem.entity.CommandType;
import team.kirohuji.OrderManagerSystem.entity.Instruct;
import team.kirohuji.OrderManagerSystem.entity.User;
import team.kirohuji.OrderManagerSystem.service.ConsoleCommandManager;
import team.kirohuji.OrderManagerSystem.service.SystemCommandManager;
import team.kirohuji.OrderManagerSystem.util.CommandTypeJudge;
import team.kirohuji.OrderManagerSystem.util.Errors;
import team.kirohuji.OrderManagerSystem.util.JdbcUtil;
import team.kirohuji.OrderManagerSystem.util.OrderManagerConsole;

public class OrderManagerLauncher {
	private Instruct instruct = null;
	private User player = null;
	private JdbcUtil jdbc = null;
	private Errors erros=null;
	private SystemCommandManager systemCommandManager = null;
	private ConsoleCommandManager consoleCommandManager = null;
	private SqlSession sqlSession;
	private CommandTypeJudge commandTypeJudge;

	public OrderManagerLauncher() {

	}
	//运行主线程
	public boolean execute() throws SQLException {
		//加载配置
		load();
		OrderManagerConsole.print("OrderManagerSystem version 1.0");
		OrderManagerConsole.println("Nice to meet you,Enter \"help\" for usage hints.");
		OrderManagerConsole.println("If no account please register first");
		while (true) {
			instruct = EncapsulationCommand(OrderManagerConsole.askUserInput(OrderManagerConsole.COMMANDLINE+"> "));
			if (Errors.NullPointerProcessing(instruct)) {
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
		sqlSession = jdbc.getSqlSessionFactory().openSession();
		systemCommandManager = CommandManagerProducer.getFactory("System").getInstanceSystemManager();
		consoleCommandManager = cmp.getFactory("Console").getInstanceConsoleManager();
		commandTypeJudge = new CommandTypeJudge();
	}

	private Instruct EncapsulationCommand(String command) {
		if(command.equals("")||command==null){
			OrderManagerConsole.println("Invalid input.Empty value is not allowed!");
		}
		return new InstructImp().selectByName(command.toLowerCase());
	}

}
