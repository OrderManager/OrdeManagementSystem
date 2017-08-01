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
import team.kirohuji.OrderManagerSystem.util.Errors;
import team.kirohuji.OrderManagerSystem.util.JdbcUtil;
import team.kirohuji.OrderManagerSystem.util.OrderManagerConsole;

public class SystemCommandManager implements CommandManager {
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

	}

	@Override
	public void getSystemCommand(Instruct instruct) throws ClassNotFoundException, IOException {
		jdbc = JdbcUtil.getInstance();
		this.instruct=instruct;
		// 每次进行用户判断是否为空
		if (playerJudge(instruct)) {
			switch (instruct.getName().toLowerCase()) {
			// 帮助
			case "help":
				command = c -> {
					OrderManagerConsole.printUsage(OrderManagerConsole.SYSTEMCOMMAND);
					return true;
				};
				break;
			// 登录
			case "login":
				command = c -> {
					String code = OrderManagerConsole.askUserInput("Please enter your account\nlogin>");
					String password = OrderManagerConsole.askUserInput("Please enter your password\nlogin>");
					return login(code, password);
				};
				break;
			// 登出
			case "logout":
				command = c -> {
					OrderManagerConsole.println("You are not logged in, so you can't log out");
					OrderManagerConsole.COMMANDLINE = OrderManagerConsole.CMD;
					return true;
				};
				break;
			// 当前状态
			case "status":
				command = c -> {
					System.out.println("You haven't logged in yet");
					return true;
				};
				break;
			}
		} else {
			switch (instruct.getName().toLowerCase()) {
			case "help":
				command = c -> {
					OrderManagerConsole.printUsage(OrderManagerConsole.SYSTEMCOMMAND);
					OrderManagerConsole.printUsage(player.getRuleId(), player);
					return true;
				};
				break;
			case "register":
				command = c -> {
					OrderManagerConsole.println("in register ... Ok!\n   start register");
					String name = OrderManagerConsole
							.askUserInput("Please enter your register name:can not be empty\nregister>");
					String code = OrderManagerConsole
							.askUserInput("Please enter your register account:can not be empty\nregister>");
					String password = OrderManagerConsole
							.askUserInput("Please enter your register password:can not be empty\nregister>");
					String phone = OrderManagerConsole.askUserInput("Please enter your register phone\nregister>");
					String address = OrderManagerConsole.askUserInput("Please enter your register address\nregister>");
					String money = OrderManagerConsole.askUserInput("Please enter your register money\nregister>");
					String rule = OrderManagerConsole
							.askUserInput("Please enter your register rule:buyer or seller or admin \ncmd>");
					return register(rule, name, code, password, phone, address, money);
				};
				break;
			case "login":
				command = c -> {
					OrderManagerConsole.println("Login Error");
					OrderManagerConsole.println("Unable to log in because you are logged in");
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
			case "status":
				command = c -> {
					System.out.println(player);
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
	}

	/**
	 * 用户登录
	 * 
	 * @param code
	 * @param password
	 * @return true 代表登录成功
	 * @return false 代表登录失败
	 */
	private boolean login(String code, String password) {
		UserImp userUtil = new UserImp();
		User temp = new User();
		temp.setCode(code);
		temp.setPassword(password);
		OrderManagerConsole.println("loging...");
		temp = userUtil.selectUserByCodeAndPassword(temp);
		if (Errors.NullPointerProcessing(temp)) {
			OrderManagerConsole.println("Login Error");
			OrderManagerConsole.println("User name or password error");
			return false;
		} else {
			OrderManagerConsole.println("Success login");
			OrderManagerConsole.println("Welcome " + temp.getName());
			OrderManagerConsole.println("Your current status is " + userUtil.selectByCodeGainRule(temp));
			this.player = temp;
			return true;
		}
	}

	/**
	 * 用户判断
	 * 
	 * @return true 代表用户为为空
	 * @return false 代表用户不为空
	 */
	public boolean playerJudge(Instruct instruct) {
		if (player == null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 注册用户
	 * 
	 * @param rule
	 * @param name
	 * @param code
	 * @param password
	 * @param phone
	 * @param address
	 * @param money
	 * @return true 表示注册成功
	 * @return false 表示注册失败
	 */
	public boolean register(String rule, String name, String code, String password, String phone, String address,
			String money) {
		UserImp userUtil = new UserImp();
		int rule_id = rule.equals("buyer") ? 3 : rule.equals("admin") ? 1 : 2;
		User temp = new User(userUtil.selectId() + 1, name, code, password, Integer.valueOf(phone), address,
				Double.valueOf(money), rule_id);
		OrderManagerConsole.println("regigstering...");
		if (Errors.NullPointerProcessing(userUtil.insert(temp))) {
			OrderManagerConsole.println("success register");
			String is = OrderManagerConsole.askUserInput("Are you logged in?:yes or no\ncmd>");
			if (is.equalsIgnoreCase("yes")) {
				OrderManagerConsole.println("success login");
				OrderManagerConsole.println("Welcome " + temp.getName());
				OrderManagerConsole.println("Your current status is " + userUtil.selectByCodeGainRule(temp));
				this.player = temp;
				return true;
			} else {
				OrderManagerConsole.println("Success,You can type 'help' for usage");
				this.player = null;
				return true;
			}
		} else {
			OrderManagerConsole.println("Failure,Your registered user already exists,You can type 'help' for usage");
			return false;
		}

	}
}
