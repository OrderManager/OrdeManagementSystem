package team.kirohuji.OrderManagerSystem.util;

import java.io.InputStream;
import java.util.Scanner;

import team.kirohuji.OrderManagerSystem.entity.Container;
import team.kirohuji.OrderManagerSystem.entity.Instruct;
import team.kirohuji.OrderManagerSystem.entity.User;

public class OrderManagerConsole {
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
			if (!"".equals(input)) {
				return input;
			}
			System.out.println("Invalid input.Empty value is not allowed!");
		}
	}

	public static void printUsage(Container<Instruct> container, int type) {
		StringBuilder sb = new StringBuilder("");
		for (int i = 0; i < container.size(); i++) {
			if (container.getItem(i).getRuleId() == type) {
				sb.append(container.getItem(i).getName());
				for (int j = 20 - container.getItem(i).getName().length(); j > 0; j--) {
					sb.append(" ");
				}
				sb.append("- " + container.getItem(i).getContent()).append("\n");
			}

		}
		System.out.println(sb.toString());
	}

	public static void printUsage(Container<Instruct> container) {
		StringBuilder sb = new StringBuilder("This is the usage for BeSuper \n");
		for (int i = 0; i < container.size(); i++) {
			sb.append(container.getItem(i).getName());
			for (int j = 20 - container.getItem(i).getName().length(); j > 0; j--) {
				sb.append(" ");
			}
			sb.append("- " + container.getItem(i).getContent()).append("\n");

		}
		System.out.println(sb.toString());
	}

}
