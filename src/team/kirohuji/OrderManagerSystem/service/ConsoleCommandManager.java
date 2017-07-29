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

public class ConsoleCommandManager implements CommandManager {
	private static volatile CommandManager commandManager;
	private static final int SYSTEMCOMMAND = 1;
	private static final int USERCOMMAND = 2;
	private Container<Instruct> container=null;
	private Instruct instruct=null;
	private JdbcUtil jdbc=null;
	private Command command=null;
	private User player=null;
	private Connection conn = null;

	public User execute(User player) {
		this.player = player;
	//	command.dispose();
		return player;
	}

	@Override
	public User execute(CommandType buyer) {
		// TODO Auto-generated method stub
		return player;
	}
	@Override
	public void execute(User player, CommandType buyer) {
		this.player = player;
		command.dispose(buyer);
	}
	@Override
	public void getConsoleCommand(Instruct instruct) throws ClassNotFoundException, IOException, SQLException {
		jdbc = JdbcUtil.getInstance();
		switch (instruct.getName()) {
		case "addGoods":
			command=c->{
				return false;
			};
			break;
		case "deleteGoods":
			command=c->{
				return false;
			};
			break;
		case "openShop":
			command=c->{
				return false;
			};
			break;
		case "closeShop":
			command=c->{
				return false;
			};
			break;
		case "buy":
			command=c->{
				return false;
			};
			break;
		case "showgoods":
			command=c->{
				return false;
			};
			break;
		case "showshop":
			command=c->{
				return false;
			};
			break;
		case "location":
			command=c->{
				return false;
			};
			break;
		case "addItemCart":
			command=c->{
				return false;
			};
			break;
		default:
			break;
		}
		OrderManagerConsole.println("Invalid command.See the user as below:\n");
		//OrderManagerConsole.printUsage();
	}

	@Override
	public void getSystemCommand(Instruct instruct) throws ClassNotFoundException, IOException {

	}

	@Override
	public void setInstructSet(Container container) {
		Iterator<Instruct> it=container.iterator();
		if(it.hasNext()){
			Instruct instruct=it.next();
			if(instruct.getRuleId()==USERCOMMAND){
				this.container.insert(instruct);
			}
		}
	}

	@Override
	public Container<Instruct> getContainer() {
		// TODO Auto-generated method stub
		return container;
	}


}
