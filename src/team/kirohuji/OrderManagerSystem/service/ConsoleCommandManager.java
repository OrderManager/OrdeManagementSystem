package team.kirohuji.OrderManagerSystem.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import team.kirohuji.OrderManagerSystem.dao.imp.GoodsHasShopImp;
import team.kirohuji.OrderManagerSystem.dao.imp.GoodsImp;
import team.kirohuji.OrderManagerSystem.dao.imp.ShopImp;
import team.kirohuji.OrderManagerSystem.entity.Command;
import team.kirohuji.OrderManagerSystem.entity.CommandManager;
import team.kirohuji.OrderManagerSystem.entity.CommandType;
import team.kirohuji.OrderManagerSystem.entity.Container;
import team.kirohuji.OrderManagerSystem.entity.Goods;
import team.kirohuji.OrderManagerSystem.entity.GoodsHasShop;
import team.kirohuji.OrderManagerSystem.entity.Instruct;
import team.kirohuji.OrderManagerSystem.entity.Shop;
import team.kirohuji.OrderManagerSystem.entity.User;
import team.kirohuji.OrderManagerSystem.util.JdbcUtil;
import team.kirohuji.OrderManagerSystem.util.OrderManagerConsole;

public class ConsoleCommandManager implements CommandManager {
	// private static volatile CommandManager commandManager;
	private Container<Instruct> container = null;
	private Instruct instruct = null;
	private JdbcUtil jdbc = null;
	private Command command = null;
	private User player = null;
	private Connection conn = null;

	public User execute(User player) {
		this.player = player;
		// command.dispose();
		return player;
	}

	@Override
	public User execute(CommandType buyer) {
		// TODO Auto-generated method stub
		return player;
	}

	@Override
	public void execute(User player, CommandType buyer) {
		this.player = player;
		command.dispose(buyer);
	}

	@Override
	public void getConsoleCommand(Instruct instruct) throws ClassNotFoundException, IOException, SQLException {
		jdbc = JdbcUtil.getInstance();
		if (instruct.getRuleId() == OrderManagerConsole.SELLERCOMMAND) {
			switch (instruct.getName().toLowerCase()) {
			case "addgoods":
				command = c -> {
					String goodName = OrderManagerConsole.askUserInput("Please enter your goods's name\nname>");
					String price = OrderManagerConsole.askUserInput("Please enter your price\nprice>");
					String content = OrderManagerConsole.askUserInput("Please enter your goods content\ncontent>");
					String inventry = OrderManagerConsole.askUserInput("Please enter your price's inventry\nnumber>");
					String shopName = OrderManagerConsole.askUserInput("Please enter your shop's name\nname>");
					GoodsImp goodsUtil = new GoodsImp();
					GoodsHasShopImp goodsHasShopUtil = new GoodsHasShopImp();
					int id=goodsUtil.selectId()+1;
					Goods goods=new Goods(id, Double.valueOf(price),goodName, content, 0, Integer.valueOf(inventry));
					if (goodsUtil.insert(goods) > 0) {
						ShopImp shopUtil=new ShopImp();
						Shop shop=shopUtil.selectByName(shopName);
						GoodsHasShop goodsHasShop=new GoodsHasShop(id, shop.getId());
						goodsHasShopUtil.insert(goodsHasShop);
						OrderManagerConsole.println("success addGoods");
					}
					return true;
				};
				break;
			case "deletegoods":
				command = c -> {
					String goodName = OrderManagerConsole.askUserInput("Please enter your goods's name\nname>");
					String shopName = OrderManagerConsole.askUserInput("Please enter your shop's name\nname>");
					GoodsImp goodsUtil = new GoodsImp();
					if (goodsUtil.deleteByName(goodName) > 0) {
						
						OrderManagerConsole.println("success deleteGoods");
					}
					return true;
				};
				break;
			case "openshop":
				command = c -> {
					String name = OrderManagerConsole.askUserInput("Please enter your shop's name\nshopName>");
					ShopImp shopUtil = new ShopImp();
					shopUtil.updateByNameOpen(name);
					return true;
				};
				break;
			case "closeshop":
				command = c -> {
					String name = OrderManagerConsole.askUserInput("Please enter your close's name\nshopName>");
					ShopImp shopUtil = new ShopImp();
					shopUtil.updateByNameClose(name);
					return true;
				};
				break;
			case "showgoods":
				command = c -> {
					return false;
				};
				break;
			case "showshop":
				command = c -> {
					ShopImp shopUtil = new ShopImp();
					ArrayList<Shop> shops = shopUtil.selectAll();
					shops.stream().filter(s -> s.getIsOpen() != 0).forEach(System.out::println);
					return true;
				};
				break;
			default:
				break;
			}
		} else {
			switch (instruct.getName()) {
			case "buy":
				command = c -> {
					return false;
				};
				break;
			 case "showgoods":
			 command = c -> {
			 return false;
			 };
			 break;
			case "showshop":
				command = c -> {
					ShopImp shopUtil = new ShopImp();
					ArrayList<Shop> shops = shopUtil.selectAll();
					shops.stream().filter(s -> s.getIsOpen() != 0).forEach(System.out::println);
					return true;
				};
				break;
			case "addItemCart":
				command = c -> {
					return false;
				};
				break;
			default:
				break;
			}
		}

		OrderManagerConsole.println("Invalid command.See the user as below:\n");
		// OrderManagerConsole.printUsage();
	}

	@Override
	public void getSystemCommand(Instruct instruct) throws ClassNotFoundException, IOException {

	}

	@Override
	public void setInstructSet(Container container) {
		Iterator<Instruct> it = container.iterator();
		if (it.hasNext()) {
			Instruct instruct = it.next();
			if (instruct.getRuleId() != OrderManagerConsole.SYSTEMCOMMAND) {
				this.container.insert(instruct);
			}
		}
	}

	@Override
	public Container<Instruct> getContainer() {
		// TODO Auto-generated method stub
		return container;
	}

	@Override
	public void setUserInstructSet(Container container) {
		// TODO Auto-generated method stub

	}

}
