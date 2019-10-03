package io.wolff.groupme;

import com.google.gson.Gson;

import io.wolff.groupme.model.Message;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
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
	
	public Mono<HttpClientResponse> postMessage() {
		return HttpClient.create()
				.baseUrl("https://api.groupme.com/v3/bots/post?bot_id="+this.botId)
				// TODO: set content
				.post().response();
	}
}
