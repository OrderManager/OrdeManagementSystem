package com.orderManagement.command.impl;

import com.orderManagement.command.SystemCommand;
import com.orderManagement.utils.InputConsole;

public class ExitCommand extends SystemCommand {

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		InputConsole.terminate();
	}

}
