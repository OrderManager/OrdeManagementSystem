package com.orderManagement.command;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.orderManagement.command.impl.AddCommand;
import com.orderManagement.command.impl.BuyCommand;
import com.orderManagement.command.impl.CloseCommand;
import com.orderManagement.command.impl.GenerateCommand;
import com.orderManagement.command.impl.GoodsCommand;
import com.orderManagement.command.impl.LogoutCommand;
import com.orderManagement.command.impl.OffCommand;
import com.orderManagement.command.impl.OrderCartCommand;
import com.orderManagement.command.impl.SettleCommand;
import com.orderManagement.command.impl.ShopCommand;
import com.orderManagement.command.impl.ViewrecordCommand;
import com.orderManagement.main.OrderManagementMain;
import com.orderManagement.pojo.User;

public class UserCommandFactory extends SystemCommandFactory {

	private User user;

	public UserCommandFactory(User user) {
		// TODO Auto-generated constructor stub
		this.user = user;
	}

	private static Map<CommandCode, Class<? extends Command>> commandMap1 = new HashMap<>();
	private static Map<CommandCode, Class<? extends Command>> commandMap2 = new HashMap<>();
	private static List<Map<CommandCode, Class<? extends Command>>> commandList = new ArrayList<>();

	static {
		commandMap1.put(CommandCode.LOGOUT, LogoutCommand.class);
		commandMap1.put(CommandCode.VIEWRECORD, ViewrecordCommand.class);
		commandMap1.put(CommandCode.GOODS, GoodsCommand.class);
		commandMap1.put(CommandCode.BUY, BuyCommand.class);
		commandMap1.put(CommandCode.ORDERCART, OrderCartCommand.class);
		commandMap1.put(CommandCode.GENERATE, GenerateCommand.class);
		commandMap1.put(CommandCode.SETTLE, SettleCommand.class);
		commandList.add(commandMap1);

		commandMap2.put(CommandCode.LOGOUT, LogoutCommand.class);
		commandMap2.put(CommandCode.SHOP, ShopCommand.class);
		commandMap2.put(CommandCode.CLOSE, CloseCommand.class);
		commandMap2.put(CommandCode.ADD, AddCommand.class);
		commandMap2.put(CommandCode.OFF, OffCommand.class);
		commandList.add(commandMap2);
	}

	@Override
	public Command buildeCommand(CommandCode cmd) {
		Class<? extends Command> clazz = null;
		if (OrderManagementMain.getInstance().getUserfile().getRuleid() == 1)
			clazz = commandList.get(0).get(cmd);
		else if (OrderManagementMain.getInstance().getUserfile().getRuleid() == 2)
			clazz = commandList.get(1).get(cmd);

		if (clazz == null) {
			return super.buildeCommand(cmd);
		}

		try {
			Constructor<? extends Command> c = clazz.getConstructor(User.class);
			Command command = c.newInstance(user);

			return command;
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
