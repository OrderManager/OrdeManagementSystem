package team.kirohuji.OrderManagerSystem.entity;

public abstract class AbstractCommandManager {
	public abstract CommandManager getInstanceConsoleManager();
	public abstract CommandManager getInstanceSystemManager();
}
