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

	public static final String CMD = "cmd";
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
//			if (!"".equals(input)) {
//				return input;
//			}
		//	System.out.println("Invalid input.Empty value is not allowed!");
			return input;
		}
	}

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
