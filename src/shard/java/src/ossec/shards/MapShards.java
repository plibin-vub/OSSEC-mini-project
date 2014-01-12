package ossec.shards;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MapShards {
	static String whichShard(String userName) {
		return userName.hashCode() < 0 ? "shard1" : "shard2";
	}
	
	private static String[] shards = {"shard1", "shard2"};
	static Set<String> allShards() {
		return new HashSet<String>(Arrays.asList(shards));
	}
}
