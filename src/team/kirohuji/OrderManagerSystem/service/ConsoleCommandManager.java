package team.kirohuji.OrderManagerSystem.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import team.kirohuji.OrderManagerSystem.entity.Command;
import team.kirohuji.OrderManagerSystem.entity.CommandManager;
import team.kirohuji.OrderManagerSystem.entity.Container;
import team.kirohuji.OrderManagerSystem.entity.Instruct;
import team.kirohuji.OrderManagerSystem.entity.User;
import team.kirohuji.OrderManagerSystem.util.JdbcUtil;
import team.kirohuji.OrderManagerSystem.util.OrderManagerConsole;

public class ConsoleCommandManager implements CommandManager {
	private static volatile CommandManager commandManager;
	private static final int SYSTEMCOMMAND = 1;
	private static final int USERCOMMAND = 2;
	private Container<Instruct> container;
	private Instruct instruct;
	private JdbcUtil jdbc;
	private Command command;
	private User player;
	private Connection conn = null;

	public boolean execute(User player) {
		this.player = player;
		return command.dispose();
	}

	@Override
	public User execute() {
		// TODO Auto-generated method stub
		return player;
	}

	@Override
	public void getConsoleCommand(Instruct instruct) throws ClassNotFoundException, IOException, SQLException {
		jdbc = JdbcUtil.getInstance();
		OrderManagerConsole.println("Invalid command.See the user as below:\n");
		OrderManagerConsole.printUsage();
	}

	@Override
	public void getSystemCommand(Instruct instruct) throws ClassNotFoundException, IOException {
		
	}
}
