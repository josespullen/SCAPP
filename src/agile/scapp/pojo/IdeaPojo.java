package agile.scapp.pojo;

import java.util.Date;

public class IdeaPojo {
	private int Iid = 0;
	private boolean nextRow=false;
	

	public boolean isNextRow() {
		return nextRow;
	}

	public void setNextRow(boolean nextRow) {
		this.nextRow = nextRow;
	}

	private String userId = "null";
	private String title = "null";
	private String description = "null";
	private boolean rated = false;
	private double rating = 0;
	private String reviewer = "NA";
	private String feedback = "NA";
	private Date postedDate;
	private Date reviewedDate;
	private String department = "null";


	public int getIid() {
		return Iid;
	}

	public void setIid(int iid) {
		Iid = iid;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isRated() {
		return rated;
	}

	public void setRated(boolean rated) {
		this.rated = rated;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getReviewer() {
		return reviewer;
	}

	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}

	public Date getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}

	public Date getReviewedDate() {
		return reviewedDate;
	}

	public void setReviewedDate(Date reviewedDate) {
		this.reviewedDate = reviewedDate;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

}
