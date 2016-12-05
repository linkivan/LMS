package lms.service.impl;

import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lms.dao.CourseDAO;
import lms.dao.FileDAO;
import lms.model.CourseModel;
import lms.model.FileModel;
import lms.service.FileService;

@Service
public class FileServiceImpl implements FileService {
	@Autowired
	private FileDAO fileDAO;

	@Override
	public int createSyllabus(FileItem syllabus, CourseModel course, String uploaderName) {
		// TODO Auto-generated method stub
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
