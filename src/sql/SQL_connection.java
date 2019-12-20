package sql;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONObject;

public class SQL_connection {

	static Connection connection = null;
	static String databaseName = "reddit200710";
	static String url = "jdbc:mysql://localhost:3306/" + databaseName + "?useSSL=false";
	
	static String username = "root";
	static String password = "kommer10";
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, SQLException, IOException {
		
		JSONDecoder jd = new JSONDecoder();
		ArrayList<JSONObject> jo = jd.getJSONObjetcs("reddit");
		System.out.println(jo.size());
		Class.forName("com.mysql.jdbc.Driver").getDeclaredConstructor().newInstance();
		connection = DriverManager.getConnection(url, username, password);
		
		for(long i = 0; i < jo.size(); i++) {
			System.out.println("ID: " + jo.get((int) i).get("id"));
			System.out.println("PARENT_ID: " + jo.get((int) i).get("parent_id"));
			System.out.println("LINK_ID: " + jo.get((int) i).get("link_id"));
			System.out.println("NAME: " + jo.get((int) i).get("name"));
			System.out.println("AUTHOR: " + jo.get((int) i).get("author"));
			System.out.println("BODY: " + jo.get((int) i).get("body"));
			
			/**
			 * 2019-12-04 20:30, Making progress but i need to look up on prepareStatement() now i create a new tuple for every
			 * prepared statement execution. I want all statements to create one single row. Need to look up on that.
			 * TODO:
			 * 
			 * Look up on prepareStatement();¨
			 * Change Body to a larger VARCHAR value.
			 */
			PreparedStatement ps_id = connection.prepareStatement("INSERT INTO `reddit200710`.`2007`(`id`, `parent_id`, `link_id`, `name`, `author`, `body`, `subreddit_id`, `subreddit`, `score`, `created_utc`) VALUES ('"+jo.get((int)i).get("id")+"', '"
					+ jo.get((int) i).get("parent_id")+"', '"
					+ jo.get((int) i).get("link_id")+"', '"
					+ jo.get((int) i).get("name")+"', '"
					+ jo.get((int) i).get("author")+"', '"
					+ jo.get((int) i).get("body")+"', '"
					+ jo.get((int) i).get("subreddit_id")+"', '"
					+ jo.get((int) i).get("subreddit")+"', '"
					+ jo.get((int) i).get("score")+"', '"
					+ jo.get((int) i).get("created_utc")+"')");
			
			/*PreparedStatement ps_parent_id = connection.prepareStatement("INSERT INTO `reddit200710`.`2007`(`parent_id`) VALUES ('" + jo.get((int) i).get("parent_id") + "')");
			PreparedStatement ps_linked_id = connection.prepareStatement("INSERT INTO `reddit200710`.`2007`(`link_id`) VALUES ('" + jo.get((int) i).get("link_id") + "')");
			PreparedStatement ps_name = connection.prepareStatement("INSERT INTO `reddit200710`.`2007`(`name`) VALUES ('" + jo.get((int) i).get("name") + "')");
			PreparedStatement ps_author = connection.prepareStatement("INSERT INTO `reddit200710`.`2007`(`author`) VALUES ('" + jo.get((int) i).get("author") + "')");
			PreparedStatement ps_body = connection.prepareStatement("INSERT INTO `reddit200710`.`2007`(`body`) VALUES ('" + jo.get((int) i).get("body") + "')");
			PreparedStatement ps_subreddit_id = connection.prepareStatement("INSERT INTO `reddit200710`.`2007`(`subreddit_id`) VALUES ('" + jo.get((int) i).get("subreddit_id") + "')");
			PreparedStatement ps_subreddit = connection.prepareStatement("INSERT INTO `reddit200710`.`2007`(`subreddit`) VALUES ('" + jo.get((int) i).get("subreddit") + "')");
			PreparedStatement ps_score = connection.prepareStatement("INSERT INTO `reddit200710`.`2007`(`score`) VALUES ('" + jo.get((int) i).get("score") + "')");
			PreparedStatement ps_created_utc = connection.prepareStatement("INSERT INTO `reddit200710`.`2007`(`created_utc`) VALUES ('" + jo.get((int) i).get("created_utc") + "')");*/
			
			ps_id.executeUpdate();
			
			/*ps_parent_id.executeUpdate();
			ps_linked_id.executeUpdate();
			ps_name.executeUpdate();
			ps_author.executeUpdate();
			ps_body.executeUpdate();
			ps_subreddit_id.executeUpdate();
			ps_subreddit.executeUpdate();
			ps_score.executeUpdate();
			ps_created_utc.executeUpdate();*/
		}
		
	}
	
}
