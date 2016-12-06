package lms.service.impl;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lms.dao.FileDAO;
import lms.model.CourseModel;
import lms.model.FileModel;
import lms.service.FileService;

@Service
public class FileServiceImpl implements FileService {
	@Autowired
	private FileDAO fileDAO;
	@Autowired
	private ServletContext servletContext;
	
	@Override
	public int createSyllabus(FileItem syllabus, CourseModel course, String uploaderName) throws IOException {
		int fileId = 0;
		
		// build the destination folder
		String UPLOAD_DIRECTORY = servletContext.getInitParameter("file-upload");
		String uploadPath = UPLOAD_DIRECTORY 		+ "/" +
						    course.getCode() 		+ "/" +
						    course.getSemester() 	+ "/syllabus";
		
		uploadPath = servletContext.getRealPath("/") + uploadPath;
		
		// creates the directory if it does not exist
		fileId = persistUploadedFile(syllabus, uploadPath, uploaderName);
        fileDAO.addSyllabusInfo(fileId, course.getId());
		
		return fileId;
	}
	
	@Override
	public int createAssignmentFile(FileItem assignmentFile, CourseModel course, String assignmentName, String uploaderName)
								throws IOException {
		int fileId = 0;
		
		// build the destination folder
		String UPLOAD_DIRECTORY = servletContext.getInitParameter("file-upload");
		String uploadPath = UPLOAD_DIRECTORY 		+ "/" +
						    course.getCode() 		+ "/" +
						    course.getSemester() 	+ "/" +
						    assignmentName;
		
		uploadPath = servletContext.getRealPath("/") + uploadPath;
		
		// creates the directory if it does not exist
		fileId = persistUploadedFile(assignmentFile, uploadPath, uploaderName);
        
		return fileId;
	}
	
	@Override
	public int createAssignmentResponse(FileItem responseFile, CourseModel course, String assignmentName, String uploaderName) throws IOException {
		int fileId = 0;
		
		// build the destination folder
		String UPLOAD_DIRECTORY = servletContext.getInitParameter("file-upload");
		String uploadPath = UPLOAD_DIRECTORY 		+ "/" +
						    course.getCode() 		+ "/" +
						    course.getSemester() 	+ "/" +
						    assignmentName          + "/" +
						    uploaderName;
		
		uploadPath = servletContext.getRealPath("/") + uploadPath;
		
		// creates the directory if it does not exist
		fileId = persistUploadedFile(responseFile, uploadPath, uploaderName);
        
		return fileId;
	}
	
	@Override
	public FileModel getFileByFileId(int fileId) {
		return fileDAO.getFileById(fileId);
	}
	
	@Override
	public FileModel getSyllabusByCourseId(int courseId) {
		return fileDAO.getSyllabusByCourseId(courseId);
	}
	
	private int persistUploadedFile(FileItem uploadedFile, String uploadPath, String uploaderName)
			throws IOException {
		String filePath = "";
		int    fileId = 0;
		File   uploadDir = new File(uploadPath);
        
		if (!uploadDir.exists()) {
            System.out.println("directory doesn't exist...creating...");
            uploadDir.mkdirs();
        }
        
        filePath = uploadPath + "/" + uploadedFile.getName();
        File storeFile = new File(filePath);
        System.out.println("Writing to : " + storeFile.getAbsolutePath());
        // saves the file on disk and writes the file information to file table in the database.
        try {
			uploadedFile.write(storeFile);
			fileId = fileDAO.createFile(new FileModel(uploaderName, uploadPath, uploadedFile.getName()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IOException("Exception while trying to write file to disk..." + e.getMessage());
		}
        
		return fileId;
	}
}