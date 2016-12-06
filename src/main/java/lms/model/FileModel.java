package lms.model;

import java.sql.Timestamp;

public class FileModel {
	private int fileId;
	private int userId;
	private int courseId;
	private int assignmentId;
	private String userName;
	private String filePath;
	private String fileName;
	private Timestamp timestamp;
	
	public FileModel() {}
	
	public FileModel(String userName, String filePath, String fileName) {
		this.userName = userName;
		this.fileName = fileName;
		this.filePath = filePath;
	}
	
	public FileModel(int fileId, String userName, String filePath, String fileName) {
		this.fileId   = fileId;
		this.userName = userName;
		this.fileName = fileName;
		this.filePath = filePath;
	}
	
	public FileModel(int fileId, int userId, int courseId, int assignmentId, String filePath, String fileName) {
		this.fileId = fileId;
		this.userId = userId;
		this.courseId = courseId;
		this.assignmentId = assignmentId;
		this.fileName = fileName;
		this.filePath = filePath;
	}
	
	public Timestamp getTimestamp() {
		return timestamp;
	}
	
	public void setTimeStamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getAssignmentId() {
		return assignmentId;
	}

	public void setAssignmentId(int assignmentId) {
		this.assignmentId = assignmentId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}