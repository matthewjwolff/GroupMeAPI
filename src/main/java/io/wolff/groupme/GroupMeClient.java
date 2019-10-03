package io.wolff.groupme;

import com.google.gson.Gson;

import io.wolff.groupme.model.GroupsResponse;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

public class GroupMeClient {
	private final String token;
	
	public GroupMeClient(String token) {
		this.token = token;
	}
	
	public Mono<GroupsResponse> findGroups() {
		// TODO: urlescape?
		return HttpClient.create()
		.baseUrl("https://api.groupme.com/v3/groups?token="+token)
		.get().responseSingle((response, buffer) -> {
			return buffer.asString().map(content -> new Gson().fromJson(content, GroupsResponse.class));
		});
	}
	
	

}
