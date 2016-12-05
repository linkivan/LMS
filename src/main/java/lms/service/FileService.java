package lms.service;

import org.apache.commons.fileupload.FileItem;

import lms.model.CourseModel;

public interface FileService {
	String createSyllabus(FileItem syllabus, CourseModel course, String uploaderName);

	String createAssignmentResponse(FileItem responeFile, CourseModel course, String assignmentName, String username);

	String createAssignmentFile(FileItem responeFile, CourseModel course, String assignmentName, String username);

	String getPathByFileId(int fileId);

	String getSyllabusPathByCourseId(int courseId);
}
