/**
 * S. Chatchawal 
 * May 5, 2558 BE 10:12:24 PM
 * http://www.conan.in.th
 * chatchawal.sangkeettrakarn@nectec.or.th, conan@conan.in.th
 */
package model;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author conan
 *
 */
public class News implements Comparable<News>{
	
	String nid;
	String title;
	String content;
	String description;
	String publisher;
	String category;
	String url;
	
	Date datetime;
	ArrayList<String> images;
	ArrayList<String> tags;

	
	

	@Override
	public int compareTo(News o) {
		return o.getDatetime().before(this.getDatetime())?1:0;
	}




	public String getNid() {
		return nid;
	}




	public void setNid(String nid) {
		this.nid = nid;
	}




	public String getTitle() {
		return title;
	}




	public void setTitle(String title) {
		this.title = title;
	}




	public String getContent() {
		return content;
	}




	public void setContent(String content) {
		this.content = content;
	}




	public String getDescription() {
		return description;
	}




	public void setDescription(String description) {
		this.description = description;
	}




	public String getPublisher() {
		return publisher;
	}




	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}




	public String getCategory() {
		return category;
	}




	public void setCategory(String category) {
		this.category = category;
	}




	public String getUrl() {
		return url;
	}




	public void setUrl(String url) {
		this.url = url;
	}




	public Date getDatetime() {
		return datetime;
	}




	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}




	public ArrayList<String> getImages() {
		return images;
	}




	public void setImages(ArrayList<String> images) {
		this.images = images;
	}




	public ArrayList<String> getTags() {
		return tags;
	}




	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}

}
