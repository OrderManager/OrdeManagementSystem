package team.kirohuji.OrderManagerSystem.util;

import java.io.InputStream;
import java.util.Scanner;

import team.kirohuji.OrderManagerSystem.entity.Container;
import team.kirohuji.OrderManagerSystem.entity.Instruct;
import team.kirohuji.OrderManagerSystem.entity.User;

public class OrderManagerConsole {
	public static String NEW_LINE = "\n";
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
	public static void printUsage(Container<Instruct> container) {
		StringBuilder sb = new StringBuilder("This is the usage for BeSuper \n");
		for(int i=0;i<container.size();i++){
			sb.append(container.getItem(i).getName());
			for(int j=20-container.getItem(i).getName().length();j>0;j--){
				sb.append(" ");
			}
			sb.append("- "+container.getItem(i).getContent()).append("\n");
		}
//		sb.append("exit        - Exit").append("\n");
//		sb.append("help        - Print usage").append("\n");
//		sb.append("register    - register a user").append("\n");
//		sb.append("login       - login user").append("\n");
//		sb.append("list        - List the opening question").append("\n");
//		sb.append("score       - Print score").append("\n");
//		sb.append("ask         - Create a question").append("\n");
//		sb.append("answer      - Answer a question").append("\n");
		System.out.println(sb.toString());
		
	}
	public static void printUsage(Container<Instruct> container,User user) {
		StringBuilder sb = new StringBuilder("This is the usage for BeSuper \n");
		for(int i=0;i<container.size();i++){
			sb.append(container.getItem(i).getName());
			for(int j=20-container.getItem(i).getName().length();j>0;j--){
				sb.append(" ");
			}
			sb.append("- "+container.getItem(i).getContent()).append("\n");
		}
//		sb.append("exit        - Exit").append("\n");
//		sb.append("help        - Print usage").append("\n");
//		sb.append("register    - register a user").append("\n");
//		sb.append("login       - login user").append("\n");
//		sb.append("list        - List the opening question").append("\n");
//		sb.append("score       - Print score").append("\n");
//		sb.append("ask         - Create a question").append("\n");
//		sb.append("answer      - Answer a question").append("\n");
		System.out.println(sb.toString());
		
	}

}
