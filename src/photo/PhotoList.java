package photo;

public class PhotoList {

	private int contribution_id;
	private String img_pass;
	private String img_title;
	private int tag_no;
	private String name;
	private int cnt;
	private int rank;
	

	public String getImg_pass() {
		return img_pass;
	}

	public void setImg_pass(String img_pass) {
		this.img_pass = img_pass;
	}

	public String getImg_title() {
		return img_title;
	}

	public void setImg_title(String img_title) {
		this.img_title = img_title;
	}

	public int getContribution_id() {
		return contribution_id;
	}

	public void setContribution_id(int contribution_id) {
		this.contribution_id = contribution_id;
	}

	public int getTag_no() {
		return tag_no;
	}

	public void setTag_no(int tag_no) {
		this.tag_no = tag_no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	
}
