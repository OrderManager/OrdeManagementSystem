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

public class SystemCommandManager extends CommandManager {
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

	public void getSystemCommand(String commandName) {

	}

}
