package team.kirohuji.OrderManagerSystem.entity;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface CommandManager{
	public abstract boolean execute(User player);
	public abstract User execute();
	public void getConsoleCommand(Instruct instruct) throws ClassNotFoundException, IOException, SQLException;
	public void getSystemCommand(Instruct instruct) throws ClassNotFoundException, IOException;
}
