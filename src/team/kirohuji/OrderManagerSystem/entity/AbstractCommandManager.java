package team.kirohuji.OrderManagerSystem.entity;

import team.kirohuji.OrderManagerSystem.service.ConsoleCommandManager;
import team.kirohuji.OrderManagerSystem.service.SystemCommandManager;

public abstract class AbstractCommandManager {
	public abstract ConsoleCommandManager getInstanceConsoleManager();
	public abstract SystemCommandManager getInstanceSystemManager();
}
