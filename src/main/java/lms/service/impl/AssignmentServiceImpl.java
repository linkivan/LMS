package lms.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import lms.dao.AssignResponseDAO;
import lms.dao.AssignmentDAO;
import lms.dao.CourseDAO;
import lms.model.AssignResponseModel;
import lms.model.AssignmentModel;
import lms.model.CourseModel;
import lms.model.FileModel;
import lms.model.UserModel;
import lms.service.AssignmentService;
import lms.service.FileService;

@Service
public class AssignmentServiceImpl implements AssignmentService {

	@Autowired
	private AssignmentDAO assignmentDAO;
	@Autowired
	private CourseDAO courseDAO;
	@Autowired
	private FileService fileService;
	@Autowired
	private AssignResponseDAO assignResponseDAO;

	@Override
	public int addAssignment(AssignmentModel assignment, FileItem assignmentFile) {
		CourseModel course = courseDAO.getCourseById(assignment.getCourseId());
		int fileId = 0;
		try {
			if (!"".equals(assignmentFile.getName())) {
				fileId = fileService.createAssignmentFile(assignmentFile, course, assignment.getName(),
						this.currentUserName());
			}

			assignment.setFileId(fileId);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return assignmentDAO.addAssignment(assignment);
	}

	@Override
	public boolean modifyAssignment(AssignmentModel assignment, FileItem assignmentFile) {
		CourseModel course = courseDAO.getCourseById(assignment.getCourseId());
		int fileId = 0;
		try {
			if (!"".equals(assignmentFile.getName())) {
				fileId = fileService.createAssignmentFile(assignmentFile, course, assignment.getName(),
						this.currentUserName());
			}
			assignment.setFileId(fileId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return assignmentDAO.modifyAssignment(assignment);
	}

	@Override
	public boolean deleteAssignment(int assignmentId) {

		return assignmentDAO.deleteAssignment(assignmentId);
	}

	@Override
	public AssignmentModel getAssignmentById(int assignmentId) {

		return assignmentDAO.getAssignmentById(assignmentId);
	}

	@Override
	public List<AssignmentModel> getAssignmentByCourseId(int courseId) {
		return assignmentDAO.getAssignmentByCourseId(courseId);

	}

	private String currentUserName() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUserName = authentication.getName();
			return currentUserName;
		}
		return "";
	}

	@Override
	public boolean submitAssignResponse(AssignResponseModel assignmentResponse, FileItem assignmentResponseFile) {
		int assignResFileId = 0;
		CourseModel course = courseDAO.getCourseById(assignmentResponse.getCourseId());

		try {
			assignResFileId = fileService.createAssignmentResponse(assignmentResponseFile, course,
					getAssignmentById(assignmentResponse.getAssignmentId()).getName(), this.currentUserName());
			assignmentResponse.setFileId(assignResFileId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return assignResponseDAO.addAssignResponse(assignmentResponse);
	}

}
