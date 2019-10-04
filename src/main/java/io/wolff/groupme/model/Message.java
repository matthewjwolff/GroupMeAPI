package io.wolff.groupme.model;

import java.util.List;

public class Message {
	public List<Attachment> attachments;
	public String avatar_url;
	// timestamp class
	public Object created_at;
	public String group_id;
	public String id;
	public String name;
	public String sender_id;
	public String sender_type;
	public String source_guid; //?
	public boolean system;
	public String text;
	public String user_id;
}
