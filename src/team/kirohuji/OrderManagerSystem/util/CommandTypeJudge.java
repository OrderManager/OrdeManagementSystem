package team.kirohuji.OrderManagerSystem.util;

import team.kirohuji.OrderManagerSystem.entity.CommandType;
import team.kirohuji.OrderManagerSystem.entity.Instruct;
import team.kirohuji.OrderManagerSystem.entity.User;

public class CommandTypeJudge {

	public CommandType Judge(Instruct instruct) {
		if(instruct.getRuleId()==1){
			return CommandType.SYSTEMCOMMAND;
		}else if(instruct.getRuleId()==2){
			return CommandType.SELLER;
		}else{
			return CommandType.BUYER;
		}
		
	}
	
}
