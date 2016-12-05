package lms.service;

import org.apache.commons.fileupload.FileItem;

import lms.model.CourseModel;
import lms.model.FileModel;

public interface FileService {
	int createSyllabus(FileItem syllabus, CourseModel course, String uploaderName);

	int createAssignmentResponse(FileItem responeFile, CourseModel course, String assignmentName, String username);

	int createAssignmentFile(FileItem responeFile, CourseModel course, String assignmentName, String username);

	FileModel getPathByFileId(int fileId);

	FileModel getSyllabusPathByCourseId(int courseId);
}
