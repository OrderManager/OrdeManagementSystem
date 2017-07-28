package team.kirohuji.OrderManagerSystem.service;

import team.kirohuji.OrderManagerSystem.controller.OrderManagerLauncher;

public class OrderManagerLauncherFacotry {
	private static volatile OrderManagerLauncher bsl;
	public static synchronized OrderManagerLauncher getInstance() {
		if (bsl == null) {
			return bsl = new OrderManagerLauncher();
		}
		return null;
	}

}
