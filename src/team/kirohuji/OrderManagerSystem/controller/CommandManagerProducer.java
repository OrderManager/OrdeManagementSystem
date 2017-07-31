package team.kirohuji.OrderManagerSystem.controller;

import team.kirohuji.OrderManagerSystem.entity.AbstractCommandManager;
import team.kirohuji.OrderManagerSystem.service.ConsoleCommandManagerFacotry;
import team.kirohuji.OrderManagerSystem.service.SystemCommandManagerFactory;

public class CommandManagerProducer {
	public static AbstractCommandManager getFactory(String choice){
	      if(choice.equalsIgnoreCase("System")){
	         return new SystemCommandManagerFactory();
	      } else if(choice.equalsIgnoreCase("Console")){
	         return new ConsoleCommandManagerFacotry();
	      }
	      return null;
	   }
}
