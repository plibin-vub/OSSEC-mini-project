package ossec.shards;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Actions {
	static void addUser(String name) {
		String targetShard = MapShards.whichShard(name);
		
		try {
			String sql = "INSERT INTO user (name) VALUES (?)";
			PreparedStatement s = createConnection(targetShard).prepareStatement(sql);
			s.setString(1, name);
			s.execute();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	static void post(String name, String message) {
		String targetShard = MapShards.whichShard(name);
		
		try {
			String sql = "INSERT INTO post (name, message) VALUES (?,?)";
			PreparedStatement s = createConnection(targetShard).prepareStatement(sql);
			s.setString(1, name);
			s.setString(2, message);
			s.execute();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	static List<Post> findAllPostsWithPattern(String pattern) {
		List<Post> posts = new ArrayList<Post>();
		
		for (String shard : MapShards.allShards()) {
			posts.addAll(findAllPostsWithPattern(shard, pattern));
		}
		
		return posts;
	}	
	
	static class Post {
		String name;
		String message;
	}
	private static List<Post> findAllPostsWithPattern(String shard, String pattern) {
		List<Post> posts = new ArrayList<Post>();
		
		try {
			String sql = "SELECT name, message FROM post WHERE message LIKE ?";
			PreparedStatement s = createConnection(shard).prepareStatement(sql);
			s.setString(1, "%" + pattern + "%");
			ResultSet rs = s.executeQuery();
			while(rs.next()) {
				Post p = new Post();
				p.name = rs.getString("name");
				p.message = rs.getString("message");
				posts.add(p);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return posts;
	}
	
	static List<String> getPosts(String name) {
		String targetShard = MapShards.whichShard(name);
		
		List<String> posts = new ArrayList<String>();
		
		try {
			String sql = "SELECT * FROM post WHERE name = ?";
			PreparedStatement s = createConnection(targetShard).prepareStatement(sql);
			s.setString(1, name);
			ResultSet rs = s.executeQuery();
			while(rs.next()) {
				String message = rs.getString("message");
				posts.add(message);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return posts;
	}
	
	private final static String user = "plibin";
	private final static String password  = "plibin";
	private final static String database = "ossec";
	private final static Connection createConnection(String shard) throws ClassNotFoundException, SQLException {
	      Class.forName("com.mysql.jdbc.Driver");
	      Connection connect = DriverManager
	          .getConnection(
	        		  "jdbc:mysql://" + shard + "/" + database, user, password);

	      return connect;
	}
}
