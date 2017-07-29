package team.kirohuji.OrderManagerSystem.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import team.kirohuji.OrderManagerSystem.entity.Command;
import team.kirohuji.OrderManagerSystem.entity.CommandManager;
import team.kirohuji.OrderManagerSystem.entity.CommandType;
import team.kirohuji.OrderManagerSystem.entity.Container;
import team.kirohuji.OrderManagerSystem.entity.Instruct;
import team.kirohuji.OrderManagerSystem.entity.User;
import team.kirohuji.OrderManagerSystem.util.JdbcUtil;
import team.kirohuji.OrderManagerSystem.util.OrderManagerConsole;

public class SystemCommandManager implements CommandManager {
	private static final int SYSTEMCOMMAND = 1;
	private static final int USERCOMMAND = 2;
	private Container<Instruct> container=new Container<>();
	private Instruct instruct;
	private JdbcUtil jdbc;
	private Command command;
	private User player;
	private Connection conn = null;

	@Override
	public void execute(User player, CommandType buyer) {
		this.player = player;
		command.dispose();
		
	}
	public User execute() {
		command.dispose();
		return player;
	}

	public User execute(User player) {
		this.player = player;
		command.dispose();
		return player;
	}
	@Override
	public void getConsoleCommand(Instruct instruct) throws ClassNotFoundException, IOException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getSystemCommand(Instruct instruct) throws ClassNotFoundException, IOException {
		jdbc = JdbcUtil.getInstance();
		switch (instruct.getName().toLowerCase()) {
		case "help":
			command=()->{
				OrderManagerConsole.printUsage(container);
				return true;
			};
			break;
		case "register":
			command=()->{
				OrderManagerConsole.println("register");
				return true;
			};
			break;
		case "login":
			command=()->{
				OrderManagerConsole.println("login");
				return true;
			};
			break;
		case "logout":
			command=()->{
				OrderManagerConsole.println("logout");
				return true;
			};
			break;
		default:
			command=()->{
				OrderManagerConsole.println("Invalid command.See the user as below:\n");
				return true;
			};
			break;
		}
	//OrderManagerConsole.println("Invalid command.See the user as below:\n");
	//	OrderManagerConsole.printUsage();
	}

	@Override
	public void setInstructSet(Container container) {
		Iterator<Instruct> it=container.iterator();
		while(it.hasNext()){
			Instruct instruct=it.next();
			if(instruct.getRuleId()==SYSTEMCOMMAND){
				this.container.insert(instruct);
			}
		}
		
	}
	@Override
	public Container<Instruct> getContainer() {
		return container;
	}


}
