package com.orderManagement.utils;

import java.io.InputStream;
import java.util.Scanner;

public class InputConsole {

	private static Scanner scanner;

	static {
		scanner = new Scanner(System.in);
	}

	private InputConsole(InputStream is) {
		scanner = new Scanner(is);
	}

	public static String NEW_LINE = "\n";

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

		String input = null;
		while (true) {
			System.out.println(prompt + ">");
			input = scanner.nextLine().trim();

			if (!"".equals(input)) {
				break;
			}

			System.out.println("Invalid input.Empty value is bot allowed!");
		}

		return input;

	}

	public static int askUserInt(String prompt) {

		int inputInt = -1;
		while (true) {
			String input = askUserInput(prompt);
			try {
				inputInt = Integer.valueOf(input);
				break;
			} catch (NumberFormatException e) {
				System.out.println("Please enter a number value!");
			}
		}
		return inputInt;

	}

	public static void terminate() {
		System.exit(0);
	}
}
