package team.kirohuji.OrderManagerSystem.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import team.kirohuji.OrderManagerSystem.entity.Instruct;
import team.kirohuji.OrderManagerSystem.entity.User;

public class OrderManagerConsole {
	//默认的进入界面
	public static String CMD = "cmd";
	public static String COMMANDLINE = CMD;
	public static final int SYSTEMCOMMAND = 1;
	public static final int SELLERCOMMAND = 2;
	public static final int BUYERCOMMAND = 3;
	private static Scanner scanner;
	static {
		scanner = new Scanner(System.in);
	}

	private OrderManagerConsole(InputStream is) {
		scanner = new Scanner(is);
	}

	public static void print(String line) {
		System.out.println(line);
	}

	public static void println(Object object) {
		System.out.println(object);
	}

	public static void println(String line) {
		System.out.println(line);
	}
	
	public static String askUserInput(String prompt) {
		while (true) {
			System.out.println(prompt);
			String input = scanner.nextLine().trim();
			return input;
		}
	}

	/**
	 * 根据用户类型和命令类型进行处理
	 * @param type
	 * @param user
	 */
	public static void printUsage(int type, User user) {
		JdbcUtil jdbc;
		List<String> excludeByNotAddress = Arrays.asList("buy", "outshop", "addItemCart", "showgoods");
		List<String> excludeByAddress = Arrays.asList("intoshop","showshop");
		try {
			jdbc = JdbcUtil.getInstance();
			SqlSession sqlSession = jdbc.getSqlSessionFactory().openSession();
			StringBuilder sb = new StringBuilder("This is the usage for BeSuper \n");
			List<Instruct> lists = sqlSession
					.selectList("team.kirohuji.OrderManagerSystem.mapping.InstructMapper.selectByRuleId", type);
			if (user.getAddress().equals("")) {
				lists.stream().filter(l -> {
					Iterator<String> it = excludeByNotAddress.iterator();
					while (it.hasNext()) {
						String name = it.next();
						if (l.getName().equals(name)) {
							return false;
						}
					}
					return l.getRuleId() == type;
				}).forEach(l -> {
					sb.append(l.getName());
					for (int j = 20 - l.getName().length(); j > 0; j--) {
						sb.append(" ");
					}
					sb.append("- " + l.getContent()).append("\n");
				});
			} else {
				lists.stream().filter(l -> {
					Iterator<String> it = excludeByAddress.iterator();
					while (it.hasNext()) {
						String name = it.next();
						if (l.getName().equals(name)) {
							return false;
						}
					}
					return l.getRuleId() == type;
				}).forEach(l -> {

					sb.append(l.getName());
					for (int j = 20 - l.getName().length(); j > 0; j--) {
						sb.append(" ");
					}
					sb.append("- " + l.getContent()).append("\n");
				});
			}

			System.out.println(sb.toString());
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 根据类型进行打印帮助命令
	 * @param type
	 */
	public static void printUsage(int type) {
		JdbcUtil jdbc;
		try {
			jdbc = JdbcUtil.getInstance();
			SqlSession sqlSession = jdbc.getSqlSessionFactory().openSession();
			StringBuilder sb = new StringBuilder("This is the usage for BeSuper \n");
			List<Instruct> lists = sqlSession
					.selectList("team.kirohuji.OrderManagerSystem.mapping.InstructMapper.selectByRuleId", type);
			lists.stream().filter(l -> l.getRuleId() == type && (!l.getName().equals(""))).forEach(l -> {
				sb.append(l.getName());
				for (int j = 20 - l.getName().length(); j > 0; j--) {
					sb.append(" ");
				}
				sb.append("- " + l.getContent()).append("\n");
			});
			System.out.println(sb.toString());
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}

	}
}
