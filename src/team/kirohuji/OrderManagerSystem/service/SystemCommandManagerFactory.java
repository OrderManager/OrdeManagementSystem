package team.kirohuji.OrderManagerSystem.service;

import team.kirohuji.OrderManagerSystem.entity.AbstractCommandManager;
import team.kirohuji.OrderManagerSystem.entity.CommandManager;

public class SystemCommandManagerFactory extends AbstractCommandManager{
	private static volatile CommandManager commandManager;
	@Override
	public CommandManager getInstanceConsoleManager() {
		return null;
		// TODO Auto-generated method stub
		
	}

	@Override
	public CommandManager getInstanceSystemManager() {
		return commandManager=getInstance();
		// TODO Auto-generated method stub
		
	}
	public static synchronized CommandManager getInstance() {
		if (commandManager == null) {
			return (SystemCommandManager) (commandManager = new SystemCommandManager());
		}
		return null;
	}
}
