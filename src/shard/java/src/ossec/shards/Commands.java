package ossec.shards;

import ossec.shards.Actions.Post;

public class Commands {
	public static void main(String[] args) {
		if (args.length == 0) {
			System.err.println("Possible commands: add-user user, post user message, get-posts user, users-that-posted-pattern pattern");
			return;
		}
		
		String command = args[0];
		if ("add-user".equals(command)) {
			Actions.addUser(args[1]);
		} else if ("post".equals(command)) {
			Actions.post(args[1], args[2]);
		} else if ("get-posts".equals(command)) {
			System.out.println("User " + args[1] + " posted:");
			for (String message : Actions.getPosts(args[1])) {
				System.out.println(message);
			}
		} else if ("users-that-posted-pattern".equals(command)) {
			System.out.println("Users that posted posts with this pattern( " + args[1] + "):");
			for (Post post : Actions.findAllPostsWithPattern(args[1])) {
				System.out.println("\"" + post.name + "\":" + "\"" + post.message + "\"");
			}
		}
	}
}
