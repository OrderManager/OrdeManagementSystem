package team.kirohuji.OrderManagerSystem;

import java.sql.SQLException;

import team.kirohuji.OrderManagerSystem.controller.BeSuperLauncher;
import team.kirohuji.OrderManagerSystem.service.BeSuperLauncherFacotry;

public class BeSuperApplication {
	private static boolean flag=false;
	private static BeSuperLauncherFacotry bslf;
	public static void main(String[] args) throws InterruptedException {
		bslf=new BeSuperLauncherFacotry();
		new Thread(() -> {
			BeSuperLauncher bsl = BeSuperLauncherFacotry.getInstance();
			try {
				flag = bsl.execute();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start();
	}

}
