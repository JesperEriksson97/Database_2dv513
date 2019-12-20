package sql;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

import org.apache.commons.text.StringEscapeUtils;

import org.json.JSONObject;

public class SQL_connection {

	static Connection connection = null;
	static String databaseName = "reddit200710";
	static String url = "jdbc:mysql://localhost:3306/" + databaseName + "?useSSL=false";
	
	static String username = "root";
	static String password = "kommer10";
	java.sql.PreparedStatement ps_id = null;
	BufferedReader reader;
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, SQLException, IOException {
		
		JSONDecoder jd = new JSONDecoder();
		jd.createTables("RC_2011-07.txt"); // real deal
		// jd.createTables("reddit"); // test
		
		/* for(long i = 0; i < jo.size(); i++) {
			
			 Creating tables.
			ps_id = connection.prepareStatement("INSERT INTO `reddit200710`.`2011`(`id`, `parent_id`, `link_id`, `name`, `author`, `body`, `subreddit_id`, `subreddit`, `score`, `created_utc`) VALUES ('"+jo.get((int)i).get("id")+"', '"
					+ jo.get((int) i).get("parent_id")+"', '"
					+ jo.get((int) i).get("link_id")+"', '"
					+ jo.get((int) i).get("name")+"', '"
					+ jo.get((int) i).get("author")+"', '"
					+ StringEscapeUtils.escapeEcmaScript((jo.get((int) i).get("body")).toString()) +"', '"
					+ jo.get((int) i).get("subreddit_id")+"', '"
					+ jo.get((int) i).get("subreddit")+"', '"
					+ jo.get((int) i).get("score")+"', '"
					+ jo.get((int) i).get("created_utc")+"')");
			
			// Executing Query.
			int log = ps_id.executeUpdate();
			System.out.println("LOG :" + log);
			
			ps_id = null; */

			
		}
	
	}
	
		
	
	

