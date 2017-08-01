package team.kirohuji.OrderManagerSystem.service;

import team.kirohuji.OrderManagerSystem.entity.AbstractCommandManager;
import team.kirohuji.OrderManagerSystem.entity.CommandManager;

public class SystemCommandManagerFactory extends AbstractCommandManager{
	private static volatile SystemCommandManager commandManager;
	@Override
	public ConsoleCommandManager getInstanceConsoleManager() {
		return null;
	}

	@Override
	public SystemCommandManager getInstanceSystemManager() {
		return commandManager=getInstance();
		// TODO Auto-generated method stub
		
	}
	public static synchronized SystemCommandManager getInstance() {
		if (commandManager == null) {
			return commandManager = new SystemCommandManager();
		}
		return null;
	}
}
