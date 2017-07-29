package team.kirohuji.OrderManagerSystem.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import team.kirohuji.OrderManagerSystem.dao.imp.UserImp;
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
	private Container<Instruct> container = new Container<>();
	private Instruct instruct;
	private JdbcUtil jdbc;
	private Command command;
	private User player;
	private Connection conn = null;

	@Override
	public void execute(User player, CommandType buyer) {
		this.player = player;
		command.dispose(buyer);

	}

	@Override
	public User execute(CommandType buyer) {
		command.dispose(buyer);
		return player;
	}

	public User execute(User player) {
		this.player = player;
		// command.dispose();
		return player;
	}

	@Override
	public void getConsoleCommand(Instruct instruct) throws ClassNotFoundException, IOException, SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getSystemCommand(Instruct instruct) throws ClassNotFoundException, IOException {
		jdbc = JdbcUtil.getInstance();
		if (player == null && instruct.getName().toLowerCase().equalsIgnoreCase("logout")) {
			command = c -> {
				OrderManagerConsole.println("You are not logged in, so you can't log out");
				return false;
			};
		} else {
			switch (instruct.getName().toLowerCase()) {
			case "help":
				command = c -> {
					OrderManagerConsole.printUsage(container);
					return true;
				};
				break;
			case "register":
				command = c -> {
					OrderManagerConsole.println("in register ... Ok!\n   start register");
					String name = OrderManagerConsole.askUserInput("Please enter your register name\ncmd>");
					String code = OrderManagerConsole.askUserInput("Please enter your register account\ncmd>");
					String password = OrderManagerConsole.askUserInput("Please enter your register password\ncmd>");
					String phone = OrderManagerConsole.askUserInput("Please enter your register phone\ncmd>");
					String address = OrderManagerConsole.askUserInput("Please enter your register address\ncmd>");
					String money = OrderManagerConsole.askUserInput("Please enter your register money\ncmd>");
					String rule = OrderManagerConsole
							.askUserInput("Please enter your register rule:buyer or seller or admin \ncmd>");
					UserImp userUtil = new UserImp();
					int rule_id = rule.equals("buyer") ? 3 : rule.equals("admin") ? 1 : 2;
					User temp = new User(userUtil.selectId()+1, name, code, password, Integer.valueOf(phone), address,
							Double.valueOf(money), rule_id);
					OrderManagerConsole.println("regigstering...");
					if (userUtil.insert(temp) > 0) {
						OrderManagerConsole.println("success register");
						String is = OrderManagerConsole.askUserInput("Are you logged in?:yes or no\ncmd>");
						if (is.equalsIgnoreCase("yes")) {
							OrderManagerConsole.println("success login");
							OrderManagerConsole.println("Welcome " + temp.getName());
							OrderManagerConsole
									.println("Your current status is " + userUtil.selectByCodeGainRule(temp));
							this.player = temp;
							return true;
						}else{
							OrderManagerConsole.println("Success,You can type 'help' for usage")
							this.player = null;
							return true;
						}

					}
					return true;
				};
				break;
			case "login":
				command = c -> {
					String code = OrderManagerConsole.askUserInput("Please enter your account\ncmd>");
					String password = OrderManagerConsole.askUserInput("Please enter your password\ncmd>");
					UserImp userUtil = new UserImp();
					User temp = new User();
					temp.setCode(code);
					temp.setPassword(password);
					OrderManagerConsole.println("loging...");
					temp = userUtil.selectUserByCodeAndPassword(temp);
					if (temp != null) {
						OrderManagerConsole.println("success login");
						OrderManagerConsole.println("Welcome " + temp.getName());
						OrderManagerConsole.println("Your current status is " + userUtil.selectByCodeGainRule(temp));
						this.player = temp;
					}
					return true;
				};
				break;
			case "logout":
				command = c -> {
					OrderManagerConsole.println("logouting...");
					this.player = null;
					OrderManagerConsole.println("Success,You can type 'help' for usage");
					return true;
				};
				break;
			default:
				command = c -> {
					OrderManagerConsole.println("Invalid command.See the user as below:\n");
					return true;
				};
				break;
			}
		}

		// OrderManagerConsole.println("Invalid command.See the user as
		// below:\n");
		// OrderManagerConsole.printUsage();
	}

	@Override
	public void setInstructSet(Container container) {
		Iterator<Instruct> it = container.iterator();
		while (it.hasNext()) {
			Instruct instruct = it.next();
			if (instruct.getRuleId() == SYSTEMCOMMAND) {
				this.container.insert(instruct);
			}
		}

	}

	@Override
	public Container<Instruct> getContainer() {
		return container;
	}

}
