package sql;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.text.StringEscapeUtils;
import org.json.JSONException;
import org.json.JSONObject;


public class JSONDecoder {
	
	static Connection connection = null;
	static String databaseName = "reddit200710";
	static String url = "jdbc:mysql://localhost:3306/" + databaseName + "?useSSL=false";
	
	static String username = "root";
	static String password = "kommer10";
	java.sql.PreparedStatement ps_id = null;
	

	BufferedReader reader;
	String filePath = "reddit";
	String cur;
	BufferedReader br;
	
	public void createTables(String fileName) throws IOException, JSONException, SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver").getDeclaredConstructor().newInstance();
		connection = DriverManager.getConnection(url, username, password);

		File file = new File(fileName);
		String path = file.getAbsolutePath();
		JSONObject json;
		
		reader = new BufferedReader(new FileReader(path));
		String line = reader.readLine();
		
		
		
		//InputStreamReader isr = new InputStreamReader(new FileInputStream(path),"unicode");
		//br = new BufferedReader(isr);
		
		//while (cur = br.readLine()) != null
	
		while(line != null) {
			json = encodeToJSON(line);
			createMySqlTable(json.getString("id"), json.getString("parent_id"), json.getString("link_id"), json.getString("name"), json.getString("author"), json.getString("body"), json.getString("subreddit_id"), json.getString("subreddit"), json.getInt("score"), json.getString("created_utc"));
			line = reader.readLine();
		}
		
		/** while(line != null) {
			// and then read next line
			jsonObjs.add(encodeToJSON(line));
			line = reader.readLine();
		}**/
		
		
		
	}

	private JSONObject encodeToJSON(String line) {
	
		JSONObject value = new JSONObject(line);
		
		return value;
				 
	}
	
	private void createMySqlTable(String id, String parent_id, String link_id, String name, String author, String body, String subreddit_id, String subreddit, int score, String created_utc) throws SQLException {
		try {
			ps_id = connection.prepareStatement("INSERT INTO `reddit200710`.`2011`(`id`, `parent_id`, `link_id`, `name`, `author`, `body`, `subreddit_id`, `subreddit`, `score`, `created_utc`) VALUES (?,?,?,?,?,?,?,?,?,?)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ps_id.setString(1, id);	
		ps_id.setString(2, parent_id);	
		ps_id.setString(3, link_id);
		ps_id.setString(4, name);
		ps_id.setString(5, author);
		ps_id.setString(6, StringEscapeUtils.escapeEcmaScript(body));
		ps_id.setString(7, subreddit_id);
		ps_id.setString(8, subreddit);
		ps_id.setInt(9, score);
		ps_id.setString(10, created_utc);
			
		int log = ps_id.executeUpdate();
		ps_id = null;
		System.out.println("LOG :" + log);
	}
	
	
	
}