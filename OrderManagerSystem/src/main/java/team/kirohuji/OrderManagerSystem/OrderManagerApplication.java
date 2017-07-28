package team.kirohuji.OrderManagerSystem;

import java.sql.SQLException;

import team.kirohuji.OrderManagerSystem.controller.OrderManagerLauncher;
import team.kirohuji.OrderManagerSystem.service.OrderManagerLauncherFacotry;

public class OrderManagerApplication {
	private static boolean flag=false;
	private static OrderManagerLauncherFacotry bslf;
	public static void main(String[] args) throws InterruptedException {
		bslf=new OrderManagerLauncherFacotry();
		new Thread(() -> {
			OrderManagerLauncher bsl = OrderManagerLauncherFacotry.getInstance();
			try {
				flag = bsl.execute();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start();
	}

}
