package com.poscoict.jblog.vo;

public class PostVo {
	private Long no;
	private String title;
	private String content;
	private String regDate;
	private Long CategoryNo;

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
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

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public Long getCategoryNo() {
		return CategoryNo;
	}

	public void setCategoryNo(Long categoryNo) {
		CategoryNo = categoryNo;
	}

	@Override
	public String toString() {
		return "PostVo [no=" + no + ", title=" + title + ", content=" + content + ", regDate=" + regDate
				+ ", CategoryNo=" + CategoryNo + "]";
	}

}
