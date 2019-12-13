package sql;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.text.StringEscapeUtils;
import org.json.JSONObject;

public class SQL_connection {

	static Connection connection = null;
	static String databaseName = "reddit";
	static String url = "jdbc:mysql://localhost:3306/" + databaseName + "?useSSL=false";
	
	static String username = "root";
	static String password = "kommer10";
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, SQLException, IOException {
		
		JSONDecoder jd = new JSONDecoder();
		ArrayList<JSONObject> jo = jd.getJSONObjetcs("reddit");
		System.out.println(jo.size());
		Class.forName("com.mysql.jdbc.Driver").getDeclaredConstructor().newInstance();
		connection = DriverManager.getConnection(url, username, password);
		PreparedStatement ps_id = null;
		
		for(long i = 0; i < jo.size(); i++) {
			
			/*System.out.println("ID: " + jo.get((int) i).get("id"));
			System.out.println("PARENT_ID: " + jo.get((int) i).get("parent_id"));
			System.out.println("LINK_ID: " + jo.get((int) i).get("link_id"));
			System.out.println("NAME: " + jo.get((int) i).get("name"));
			System.out.println("AUTHOR: " + jo.get((int) i).get("author"));
			System.out.println("BODY: " + StringEscapeUtils.escapeJava((jo.get((int) i).get("body")).toString()).toString());
			
			/**
			 * 2019-12-10 23:01, Still stuck on that MySQLSyntaxErrorException, can't seem to find an answer.
			 * 
			 * Thins I've tried so far:
			 * 
			 * Checking that nested qoutes doesnt ruin the strin, now using escapeJava() method to fix that.
			 * Tried to use executeQuery instead but it fails.
			 * 
			 * TODO:
			 * 
			 * Look up on executeUpdate()
			 * Change Body to a larger VARCHAR value.
			 */
			
			ps_id = connection.prepareStatement("INSERT INTO `reddit`.`reddit200710`(`id`, `parent_id`, `link_id`, `name`, `author`, `body`, `subreddit_id`, `subreddit`, `score`, `created_utc`) VALUES ('"+jo.get((int)i).get("id")+"', '"
					+ jo.get((int) i).get("parent_id")+"', '"
					+ jo.get((int) i).get("link_id")+"', '"
					+ jo.get((int) i).get("name")+"', '"
					+ jo.get((int) i).get("author")+"', '"
					+ StringEscapeUtils.escapeEcmaScript((jo.get((int) i).get("body")).toString()) +"', '"
					+ jo.get((int) i).get("subreddit_id")+"', '"
					+ jo.get((int) i).get("subreddit")+"', '"
					+ jo.get((int) i).get("score")+"', '"
					+ jo.get((int) i).get("created_utc")+"')");
			
			int log = ps_id.executeUpdate();
			System.out.println("LOG :" + log);
			
			ps_id = null;
			
		}
		
	}
	
}
