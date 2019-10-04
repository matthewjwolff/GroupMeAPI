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

import io.wolff.groupme.model.BotMessage;
import io.wolff.groupme.model.Message;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.ByteBufFlux;
import reactor.netty.DisposableServer;
import reactor.netty.http.client.HttpClient;
import reactor.netty.http.client.HttpClientResponse;
import reactor.netty.http.server.HttpServer;

public class GroupMeBot {
	
	private final String botId;
	private DisposableServer server;

	public GroupMeBot(String botId) {
		this.botId = botId;
	}
	
	public void stopListening() {
		// TODO: make sure threads are closed
		server.dispose();
	}
	
	
	public Flux<Message> listen() {
		return Flux.create(sink -> {
			server = HttpServer.create()
					.port(8080)
					
					.handle( (request, response) ->  
						request // this happens on a netty thread
						.receive()
						.asString()
						.map(payload -> new Gson().fromJson(payload, Message.class))
						.map(sink::next)
						.then(response.send())
					).bindNow();
			
			sink.onDispose(server);
		});
	}
	
	public Mono<HttpClientResponse> postMessage(BotMessage message) {
		message.bot_id = this.botId;
		return HttpClient.create()
				.baseUrl("https://api.groupme.com/v3/bots/post")
				.post().send(ByteBufFlux.fromString(Mono.just(new Gson().toJson(message)))).response();
	}
}
