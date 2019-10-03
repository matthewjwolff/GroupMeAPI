package io.wolff.groupme.model;

public class Attachment {
	String type; // either "location" or "image"
	
	// for image types only
	String url;
	
	// for location types only
	String lng;
	String lat;
	String name;
}
