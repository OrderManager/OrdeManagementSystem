package team.kirohuji.OrderManagerSystem.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import team.kirohuji.OrderManagerSystem.dao.UserImp;
import team.kirohuji.OrderManagerSystem.entity.Command;
import team.kirohuji.OrderManagerSystem.entity.CommandManager;
import team.kirohuji.OrderManagerSystem.entity.User;
import team.kirohuji.OrderManagerSystem.util.JdbcUtil;

public class SystemCommandManager extends CommandManager {
	private final static String HELP = "help";
	private final static String REGISTER = "register";
	private final static String LOGIN = "login";
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
		if (HELP.equalsIgnoreCase(commandName)) {
			command = () -> {
				QingkeConsole.printUsage();
				return true;
			};

		} else if (REGISTER.equalsIgnoreCase(commandName)) {
			command=()->{
				while (true) {
					String name = QingkeConsole.askUserInput("please enter you name:");
					if ("".equals(name.trim())) {
						QingkeConsole.println("Name is not allowed to be empty!");
						continue;
					}
					String password = QingkeConsole.askUserInput("please enter you password:");
					User user=new User();
					user.setName(name);
					user.setPassword(password);
					user.setScore(10);
					try {
						UserImp userImp=new UserImp();
						if(userImp.setUser(user)){
							QingkeConsole.println("welcome! "+name);
							player=userImp.getUserByName(name);
							return true;
						}else{
							QingkeConsole.println("This name is already in use");
							return false;
						}
					} catch (ClassNotFoundException | IOException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
		} else if (LOGIN.equalsIgnoreCase(commandName)) {
			command = () -> {
				while (true) {
					String name = QingkeConsole.askUserInput("please enter you name:");
					if ("".equals(name.trim())) {
						QingkeConsole.println("Name is not allowed to be empty!");
						continue;
					}
					String password = QingkeConsole.askUserInput("please enter you password:");
					User user=new User();
					user.setName(name);
					user.setPassword(password);
					try {
						UserImp userImp=new UserImp();
						if(userImp.getUser(user)){
							QingkeConsole.println("welcome! "+name);
							player=userImp.getUserByName(name);
							return true;
						}else{
							QingkeConsole.println("Password mistake, or the user does not exist,please");
							return false;
						}
					} catch (ClassNotFoundException | IOException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};

		}
	}

}
