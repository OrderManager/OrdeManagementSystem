package team.kirohuji.OrderManagerSystem.entity;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import team.kirohuji.OrderManagerSystem.util.Errors;

public interface CommandManager {
	public User execute(User player);

	public User execute(CommandType buyer);

	public void getConsoleCommand(Instruct instruct) throws ClassNotFoundException, IOException, SQLException;

	public void getSystemCommand(Instruct instruct) throws ClassNotFoundException, IOException;

	public void execute(User player, CommandType buyer);
}
