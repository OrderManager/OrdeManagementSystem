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

public class SystemCommandManager implements CommandManager {
	private static final int SYSTEMCOMMAND = 1;
	private static final int USERCOMMAND = 2;
	private Container<Instruct> container;
	private Instruct instruct;
	private JdbcUtil jdbc;
	private Command command;
	private User player;
	private Connection conn = null;

	public User execute() {
		command.dispose();
		return player;
	}

	public boolean execute(User player) {
		this.player = player;
		return command.dispose();
	}
	@Override
	public void getConsoleCommand(Instruct instruct) throws ClassNotFoundException, IOException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getSystemCommand(Instruct instruct) throws ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		
	}

}
