package team.kirohuji.OrderManagerSystem.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import team.kirohuji.OrderManagerSystem.dao.QuestionImp;
import team.kirohuji.OrderManagerSystem.entity.Answer;
import team.kirohuji.OrderManagerSystem.entity.BeSuper;
import team.kirohuji.OrderManagerSystem.entity.Command;
import team.kirohuji.OrderManagerSystem.entity.CommandManager;
import team.kirohuji.OrderManagerSystem.entity.Question;
import team.kirohuji.OrderManagerSystem.entity.User;
import team.kirohuji.OrderManagerSystem.util.JdbcUtil;

public class ConsoleCommandManager extends CommandManager {
	private final static String ASK = "ask";
	private final static String ANSWER = "answer";
	private final static String LIST = "list";
	private final static String SCORE = "score";
	private static volatile CommandManager commandManager;
	private JdbcUtil jdbc;
	private Command command;
	private User player;
	private Connection conn = null;

	public boolean execute(User player) {
		this.player = player;
		return command.dispose();
	}

	public void getConsoleCommand(String commandName) throws ClassNotFoundException, IOException, SQLException {
		jdbc = JdbcUtil.getInstance();
		if (LIST.equalsIgnoreCase(commandName)) {
			command = () -> {
				BeSuper beSuper = new BeSuper();
				conn = jdbc.getConnection();
				Statement stmt;
				try {
					stmt = conn.createStatement();
					ResultSet result = stmt.executeQuery(
							"select q.id,q.solved,q.context,q.user_id,u.name from Question q join user u on u.id=q.user_id;");
					while (result.next()) {
						Question question = new Question();
						question.setId(result.getInt(1));
						question.setSolved(result.getInt(2));
						question.setContent(result.getString(3));
						question.setUserId(result.getInt(4));
						question.setUserName(result.getString(5));
						beSuper.addQuestion(question);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				beSuper.getQuestions().stream().forEach(QingkeConsole::println);
				return true;
			};

		} else if (SCORE.equalsIgnoreCase(commandName)) {
			command = () -> {
				QingkeConsole.println("You score:" + player.getScore());
				return true;
			};
		} else if (ASK.equalsIgnoreCase(commandName)) {

			command = () -> {
				String content = QingkeConsole.askUserInput("Please enter you question:");
				Question question = new Question();
				question.setUserId(player.getId());
				question.setContent(content);
				QuestionImp questionImp;
				try {
					questionImp = new QuestionImp();
					if (questionImp.setQuestion(question)) {
						QingkeConsole.println("Your question has been created");
					}
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return true;
			};

		} else if (ANSWER.equalsIgnoreCase(commandName)) {
			command = () -> {
				conn=jdbc.getConnection();
				String qid = QingkeConsole.askUserInput("Enter the question id:");
				Answer a = null;
				try {
					Question question = null;
					try {
						question=new QuestionImp().getQuestion(Integer.valueOf(qid));
					} catch (NumberFormatException | ClassNotFoundException | IOException e1) {
						
						e1.printStackTrace();
					}
					if (question==null) {
						QingkeConsole.println("Question for id:" + qid + "is not exist.");
					} else if (question.isSolved()) {
						PreparedStatement ps2 = conn.prepareStatement(
								"SELECT u.name, q.context, us.name,a.context FROM user u JOIN question q ON u.id = q.user_id join answer a on a.question_id=q.id join user us on us.id=a.user_id where q.id=?;");
						ps2.setInt(1, Integer.valueOf(qid));
						ResultSet result2 = ps2.executeQuery();
						QingkeConsole.println("Question has been solved.See anwser below:");
						while (result2.next()) {
							System.out.println();
							QingkeConsole.println(
									"anwser context:" + result2.getString(4) + ":anwser User:" + result2.getString(3));
						}

					} else {
						String content = QingkeConsole.askUserInput("Enter you answer for the question:");
						PreparedStatement pStmt3;
						try {
							pStmt3 = conn
									.prepareStatement("insert into answer(context,user_id,question_id) value(?,?,?)");
							pStmt3.setString(1, content);
							pStmt3.setInt(2, player.getId());
							pStmt3.setInt(3, Integer.valueOf(qid));
							if (pStmt3.execute()) {
								player.setScore(player.getScore() + 10);
								QingkeConsole.println("Thanks for you answer");
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				return true;
			};

		} else {
			QingkeConsole.println("Invalid command.See the user as below:\n");
			QingkeConsole.printUsage();
		}
	}

	@Override
	public User execute() {
		// TODO Auto-generated method stub
		return player;
	}
}
