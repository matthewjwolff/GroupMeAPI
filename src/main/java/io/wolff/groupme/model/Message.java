package io.wolff.groupme.model;

import java.util.List;

public class Message {
	List<Attachment> attachments;
	String avatar_url;
	// timestamp class
	Object created_at;
	String group_id;
	String id;
	String name;
	String sender_id;
	String sender_type;
	String source_guid; //?
	boolean system;
	String text;
	String user_id;
}
