package com.orderManagement.command.impl;

import com.orderManagement.command.SystemCommand;
import com.orderManagement.utils.InputConsole;

public class HelpCommand extends SystemCommand {

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder("This is the usage for BeSuper game:\n");
		sb.append("exit   -Exit").append("\n");
		sb.append("help   -Help").append("\n");
		sb.append("login   -Login").append("\n");
		sb.append("signup   -Signup").append("\n");
		sb.append("goods   -Show all goods").append("\n");
		sb.append("buy   -add goods to ordercart").append("\n");
		sb.append("generate   -generate the order").append("\n");
		sb.append("settle   -settle accounts").append("\n");
		sb.append("shop   -open a new shop").append("\n");
		sb.append("close   -close the shop").append("\n");
		sb.append("add   -add a new goods to shop").append("\n");
		sb.append("off   -off the shelf").append("\n");

		InputConsole.println(sb.toString());
	}

}
