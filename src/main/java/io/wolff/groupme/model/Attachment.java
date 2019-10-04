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
