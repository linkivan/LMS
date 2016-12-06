package lms.service;

import java.io.IOException;

import org.apache.commons.fileupload.FileItem;

import lms.model.CourseModel;
import lms.model.FileModel;

public interface FileService {
    int 	  createSyllabus(FileItem syllabus, CourseModel course, String uploaderName) throws IOException ;
	int 	  createAssignmentResponse(FileItem responseFile, CourseModel course, String assignmentName, String username) throws IOException;
	int 	  createAssignmentFile(FileItem responseFile, CourseModel course, String assignmentName, String username) throws IOException;
	FileModel getFileByFileId(int fileId);
	FileModel getSyllabusByCourseId(int courseId);
}