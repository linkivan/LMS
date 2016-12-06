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
		// build the destination folder
		String UPLOAD_DIRECTORY = servletContext.getInitParameter("file-upload");
		String uploadPath = UPLOAD_DIRECTORY 		+ "/" +
						    course.getCode() 		+ "/" +
						    course.getSemester() 	+ "/syllabus";
		String filePath = "";
		uploadPath = servletContext.getRealPath("/") + uploadPath;
		
		// creates the directory if it does not exist
		File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            System.out.println("directory doesn't exist...creating...");
            uploadDir.mkdirs();
        }
        
        filePath = uploadPath + "/" + syllabus.getName();
        File storeFile = new File(filePath);
        System.out.println("Writing to : " + storeFile.getAbsolutePath());
        // saves the file on disk
        try {
			syllabus.write(storeFile);
			fileDAO.createFile(new FileModel(uploaderName, uploadPath, syllabus.getName()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IOException("Exception while trying to write file to disk..." + e.getMessage());
		}
        
		return 0;
	}

	@Override
	public int createAssignmentResponse(FileItem responeFile, CourseModel course, String assignmentName,
			String username) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int createAssignmentFile(FileItem responeFile, CourseModel course, String assignmentName, String username) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public FileModel getPathByFileId(int fileId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileModel getSyllabusPathByCourseId(int CourseId) {
		// TODO Auto-generated method stub
		return null;
	}

}