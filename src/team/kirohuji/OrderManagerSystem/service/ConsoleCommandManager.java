package team.kirohuji.OrderManagerSystem.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import team.kirohuji.OrderManagerSystem.dao.imp.BillImp;
import team.kirohuji.OrderManagerSystem.dao.imp.GoodsHasShopImp;
import team.kirohuji.OrderManagerSystem.dao.imp.GoodsImp;
import team.kirohuji.OrderManagerSystem.dao.imp.ItemImp;
import team.kirohuji.OrderManagerSystem.dao.imp.ShopImp;
import team.kirohuji.OrderManagerSystem.dao.imp.UserImp;
import team.kirohuji.OrderManagerSystem.entity.Bill;
import team.kirohuji.OrderManagerSystem.entity.Command;
import team.kirohuji.OrderManagerSystem.entity.CommandManager;
import team.kirohuji.OrderManagerSystem.entity.CommandType;
import team.kirohuji.OrderManagerSystem.entity.Goods;
import team.kirohuji.OrderManagerSystem.entity.GoodsHasShop;
import team.kirohuji.OrderManagerSystem.entity.Instruct;
import team.kirohuji.OrderManagerSystem.entity.Item;
import team.kirohuji.OrderManagerSystem.entity.Shop;
import team.kirohuji.OrderManagerSystem.entity.ShopAndGoods;
import team.kirohuji.OrderManagerSystem.entity.User;
import team.kirohuji.OrderManagerSystem.util.Errors;
import team.kirohuji.OrderManagerSystem.util.JdbcUtil;
import team.kirohuji.OrderManagerSystem.util.OrderManagerConsole;

public class ConsoleCommandManager implements CommandManager {
	private Instruct instruct = null;
	private JdbcUtil jdbc = null;
	private Command command = null;
	private User player = null;
	private int playerType = 0;

	public User execute(User player) {
		this.player = player;
		// command.dispose();
		return player;
	}

	@Override
	public User execute(CommandType type) {
		// TODO Auto-generated method stub
		return player;
	}

	@Override
	public void execute(User player, CommandType type) {
		this.player = player;
		command.dispose(type);
	}

	@Override
	public void getConsoleCommand(Instruct instruct) throws ClassNotFoundException, IOException, SQLException {
		jdbc = JdbcUtil.getInstance();
		playerJudge(instruct);
		OrderManagerConsole.println("Invalid command.See the user as below:\n");
	}

	@Override
	public void getSystemCommand(Instruct instruct) throws ClassNotFoundException, IOException {

	}

	@Override
	public boolean playerJudge(Instruct instruct) {
		if (playerType == 0) {
			if (instruct.getRuleId() == OrderManagerConsole.SELLERCOMMAND) {
				playerType = OrderManagerConsole.SELLERCOMMAND;
			} else {
				playerType = OrderManagerConsole.BUYERCOMMAND;
			}
		}
			switch (playerType) {
			case OrderManagerConsole.SELLERCOMMAND:
				seller(instruct);
				break;
			case OrderManagerConsole.BUYERCOMMAND:
				buyer(instruct);
				break;
			}
			return true;
	}

	private void buyer(Instruct instruct) {
		switch (instruct.getName().toLowerCase()) {
		case "intoshop":
			command = c -> {
				String name = OrderManagerConsole.askUserInput("Please enter your intoShop's name\nshopName>");
				player.setAddress(name);
				OrderManagerConsole.COMMANDLINE = "in " + name;
				return true;
			};
			break;
		case "outshop":
			command = c -> {
				player.setAddress("");
				OrderManagerConsole.COMMANDLINE = OrderManagerConsole.CMD;
				OrderManagerConsole.println("You left the store");
				return true;
			};
			break;
		case "buy":
			command = c -> {
				String goodsName = OrderManagerConsole.askUserInput("Please enter your goods's name\nname>");
				return buy(goodsName);
			};
			break;
		case "showgoods":
			command = c -> {
				GoodsImp goodUtil = new GoodsImp();
				ArrayList<Goods> lists = goodUtil.selectAllByShopName(player.getAddress());
				if (lists.isEmpty() || Errors.NullPointerProcessing(lists)) {
					OrderManagerConsole.println("The store did not sell merchandise");
					return false;
				} else {
					lists.stream().forEach(System.out::println);
					return true;
				}
			};
			break;
		case "showshop":
			command = c -> {
				ShopImp shopUtil = new ShopImp();
				ArrayList<Shop> shops = shopUtil.selectAll();
				if (shops.isEmpty() || Errors.NullPointerProcessing(shops)) {
					OrderManagerConsole.println("It's not open yet for all shop");
					return false;
				} else {
					shops.stream().filter(s -> s.getIsOpen() != 0).forEach(System.out::println);
					return true;
				}
			};
			break;
		default:
			break;
		}
	}

	private boolean buy(String goodsName) {
		GoodsImp goodsUtil = new GoodsImp();
		Goods goods = goodsUtil.selectByName(goodsName);
		if (player.getMoney() - goods.getPrice() > 0) {
			ItemImp itemUtil = new ItemImp();
			Item item = new Item();
			item.setGoodsId(goods.getId());
			item.setId(itemUtil.selectId() + 1);
			item.setiId(String.valueOf(System.currentTimeMillis()));
			item.setNumber(1);
			item.setStatus(1);
			if (Errors.NullPointerProcessing(itemUtil.insert(item))) {
				OrderManagerConsole.println("Generate billing error");
				return false;
			} else {
				OrderManagerConsole.println(item + goods.getName() + "]");
			}
			int id = goodsUtil.selectId() + 1;
			Bill bill = new Bill(id, String.valueOf(System.currentTimeMillis()), String.valueOf(goods.getPrice()), "0",
					player.getId(), item.getId(), "buy");
			BillImp billUtil = new BillImp();
			if (Errors.NullPointerProcessing(billUtil.insert(bill))) {
				OrderManagerConsole.println("Wrong purchase, please try again");
			} else {
				player.setMoney(player.getMoney() - goods.getPrice());
				UserImp userUtil = new UserImp();
				userUtil.updateMoney(player);
				OrderManagerConsole.println("success buy");
			}
			return true;
		} else {
			OrderManagerConsole.println("don't have enough money");
			return false;
		}
	}

	private void seller(Instruct instruct) {
		switch (instruct.getName().toLowerCase()) {
		case "addgoods":
			command = c -> {
				String goodName = OrderManagerConsole.askUserInput("Please enter your goods's name\nname>");
				String price = OrderManagerConsole.askUserInput("Please enter your price\nprice>");
				String content = OrderManagerConsole.askUserInput("Please enter your goods content\ncontent>");
				String inventry = OrderManagerConsole.askUserInput("Please enter your price's inventry\nnumber>");
				String shopName = OrderManagerConsole.askUserInput("Please enter your shop's name\nname>");
				return addGoods(goodName, price, content, inventry, shopName);

			};
			break;
		case "deletegoods":
			command = c -> {
				String goodName = OrderManagerConsole.askUserInput("Please enter your goods's name\nname>");
				String shopName = OrderManagerConsole.askUserInput("Please enter your shop's name\nname>");
				GoodsImp goodsUtil = new GoodsImp();
				if (Errors.NullPointerProcessing(goodsUtil.deleteByName(goodName))) {
					return false;
				} else {
					OrderManagerConsole.println("success deleteGoods");
					return true;
				}
			};
			break;
		case "openshop":
			command = c -> {
				String name = OrderManagerConsole.askUserInput("Please enter your shop's name\nshopName>");
				ShopImp shopUtil = new ShopImp();
				if (Errors.NullPointerProcessing(shopUtil.updateByNameOpen(name))) {
					OrderManagerConsole.println("There is no the shop of the name");
					return false;
				} else {
					OrderManagerConsole.println("success");
					return true;
				}
			};
			break;
		case "closeshop":
			command = c -> {
				String name = OrderManagerConsole.askUserInput("Please enter your close's name\nshopName>");
				ShopImp shopUtil = new ShopImp();
				if (Errors.NullPointerProcessing(shopUtil.updateByNameClose(name))) {
					OrderManagerConsole.println("There is no the shop of the name");
					return false;
				} else {
					OrderManagerConsole.println("success");
					return true;
				}
			};
			break;

		case "showyougoods":
			command = c -> {
				String name = OrderManagerConsole
						.askUserInput("Please enter your shop's name:Not input represents all\nshopName>");
				return showYouGoods(name);
			};
			break;
		case "showyoushop":
			command = c -> {
				ShopImp shopUtil = new ShopImp();
				ArrayList<Shop> shops = shopUtil.selectAllByUserId(player.getId());
				if (Errors.NullPointerProcessing(shops)) {
					OrderManagerConsole.println("You didn't open a shop");
					return true;
				} else {
					shops.stream().filter(s -> s.getIsOpen() != 0).forEach(System.out::println);
					return true;
				}
			};
			break;
		default:
			break;
		}
	}

	private boolean showYouGoods(String name) {
		GoodsHasShopImp goodsHasShopUtil = new GoodsHasShopImp();
		if (Errors.NullPointerProcessing(name)) {
			ArrayList<ShopAndGoods> lists = goodsHasShopUtil.selectAllShopAndGoods();
			lists.stream().forEach(System.out::println);
			return true;
		} else {
			ArrayList<ShopAndGoods> lists = goodsHasShopUtil.selectShopAndAllGoods(name);
			lists.stream().forEach(System.out::println);
			return true;
		}
	}

	private boolean addGoods(String goodName, String price, String content, String inventry, String shopName) {
		ShopImp shopUtil = new ShopImp();
		Shop shop = shopUtil.selectByName(shopName);
		if (Errors.NullPointerProcessing(shop)) {
			OrderManagerConsole.println("There is no the shop of the name");
			return false;
		} else {
			GoodsImp goodsUtil = new GoodsImp();
			GoodsHasShopImp goodsHasShopUtil = new GoodsHasShopImp();
			int id = goodsUtil.selectId() + 1;
			Goods goods = new Goods(id, Double.valueOf(price), goodName, content, 0, Integer.valueOf(inventry));
			if (Errors.NullPointerProcessing(goodsUtil.insert(goods))) {
				OrderManagerConsole.println("Failure addGoods");
				return false;
			} else {
				GoodsHasShop goodsHasShop = new GoodsHasShop(id, shop.getId());
				if (Errors.NullPointerProcessing(goodsHasShopUtil.insert(goodsHasShop))) {
					OrderManagerConsole.println("Failure addGoods,We have the goods");
					return false;
				} else {
					OrderManagerConsole.println("Success addGoods");
					return true;
				}
			}
		}
	}
}
