package team.kirohuji.OrderManagerSystem.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import team.kirohuji.OrderManagerSystem.entity.Command;
import team.kirohuji.OrderManagerSystem.entity.CommandManager;
import team.kirohuji.OrderManagerSystem.entity.User;
import team.kirohuji.OrderManagerSystem.util.JdbcUtil;

public class ConsoleCommandManager extends CommandManager {
	private static volatile CommandManager commandManager;
	private JdbcUtil jdbc;
	private Command command;
	private User player;
	private Connection conn = null;

	public boolean execute(User player) {
		this.player = player;
		return command.dispose();
	}

	public void getConsoleCommand(String commandName) throws ClassNotFoundException, IOException, SQLException {
		jdbc = JdbcUtil.getInstance();
		OrderManagerConsole.println("Invalid command.See the user as below:\n");
		OrderManagerConsole.printUsage();

	}

	@Override
	public User execute() {
		// TODO Auto-generated method stub
		return player;
	}
}
