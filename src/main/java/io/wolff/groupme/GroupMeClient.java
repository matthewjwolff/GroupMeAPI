/*******************************************************************************
 * This file is part of GroupMeAPI.
 *
 * GroupMeAPI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GroupMeAPI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <https://www.gnu.org/licenses/>.
 *******************************************************************************/
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
