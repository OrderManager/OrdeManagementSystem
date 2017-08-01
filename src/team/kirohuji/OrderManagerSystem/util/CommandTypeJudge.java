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
	
	/**
	 * 用户和命令的类型匹配
	 * @param instruct
	 * @param user
	 * @return true 代表相等
	 * @return false 代表不相等
	 */
	public boolean JudgeAndUser(Instruct instruct,User user) {
		if(instruct.getId()==OrderManagerConsole.SYSTEMCOMMAND){
			return true;
		}
		if(user.getRuleId()==instruct.getId()){
			return true;
		}else{
			return false;
		}
	}
}
