package photo;

import java.util.Date;

public class Fov {
	private int con_id;
	private String img_pass;
	private Date date;
	private String comname;
	private String comment;
	private String name;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getComname() {
		return comname;
	}
	public void setComname(String comname) {
		this.comname = comname;
	}
	public int getCon_id() {
		return con_id;
	}
	public void setCon_id(int con_id) {
		this.con_id = con_id;
	}
	public String getImg_pass() {
		return img_pass;
	}
	public void setImg_pass(String img_pass) {
		this.img_pass = img_pass;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
