package team.kirohuji.OrderManagerSystem.entity;

public abstract class AbstractCommandManager {
	//�����û����������
	public abstract CommandManager getInstanceConsoleManager();
	//����ϵͳ���������
	public abstract CommandManager getInstanceSystemManager();
}
