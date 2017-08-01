package team.kirohuji.OrderManagerSystem.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import team.kirohuji.OrderManagerSystem.entity.AbstractCommandManager;
import team.kirohuji.OrderManagerSystem.entity.CommandManager;

public class ConsoleCommandManagerFacotry extends AbstractCommandManager{
	private static volatile ConsoleCommandManager commandManager;
	@Override
	public ConsoleCommandManager getInstanceConsoleManager() {
		return commandManager=getInstance();
	}

	@Override
	public SystemCommandManager getInstanceSystemManager() {
		return null;

	}
	public static synchronized ConsoleCommandManager getInstance() {
		if (commandManager == null) {
			return commandManager = new ConsoleCommandManager();
		}
		return null;
	}
}
