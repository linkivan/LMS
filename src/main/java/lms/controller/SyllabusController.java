package lms.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import lms.model.CourseModel;
import lms.model.FileModel;
import lms.model.UIMenu;
import lms.service.CourseService;
import lms.service.FileService;

@Controller
@RequestMapping(value = "/course/{courseId}")
public class SyllabusController {
	//@Autowired
	//private UserService userService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private FileService fileService;
	// upload settings
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE    = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB
    //private String UPLOAD_DIRECTORY = "course_upload_files"; //servletContext.getInitParameter("file-upload");
	
	@RequestMapping(value = { "/syllabus" }, method = RequestMethod.GET)
	public ModelAndView syllabusPage(@PathVariable("courseId") int courseId) {
		ModelAndView model = new ModelAndView();
		CourseModel course = courseService.getCourseById(courseId);
		FileModel syllabus = fileService.getSyllabusByCourseId(courseId);
		model.addObject("uiMenu", new UIMenu(course.getCode(), 5, true));
		model.addObject("syllabus", syllabus);
		model.addObject("course", course);
		model.setViewName("syllabus");
		return model;
	}

	@RequestMapping(value = { "/syllabus" }, method = RequestMethod.POST)
	public String uploadSyllabus(HttpServletRequest request,
								 HttpServletResponse response,
								 @PathVariable("courseId") int courseId,
								 @RequestParam CommonsMultipartFile fileUpload,
								 Authentication authentication) throws Exception {
		ModelAndView model = new ModelAndView();
		CourseModel  course = courseService.getCourseById(courseId);
		//FileItem     syllabus = null;
		
		/*
		 * 12/05/2016
		 * Freddy added the controller part
		 */
        // checks if the request actually contains upload file
        if (!ServletFileUpload.isMultipartContent(request)) {
            // if not, we stop here
            PrintWriter writer;
			try {
				writer = response.getWriter();
	            writer.println("Error: Form must be enctype=multipart/form-data.");
	            writer.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            return "";
        }
        
        // configures upload settings
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // sets memory threshold - beyond which files are stored in disk
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // sets temporary location to store files
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        
        ServletFileUpload upload = new ServletFileUpload(factory);
        
        // sets maximum size of upload file
        upload.setFileSizeMax(MAX_FILE_SIZE);
        
        // sets maximum size of request (include file + form data)
        upload.setSizeMax(MAX_REQUEST_SIZE);
        
        /*
        try {
            // parses the request's content to extract file data
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);
            
            if (formItems != null && formItems.size() > 0) {
                // iterates over form's fields
                for (FileItem item : formItems) {
                    // process fields that are not form fields (to handle file input)
                    if (!item.isFormField()) {
                        syllabus = item;
                    }
                }
            }
        } catch (Exception ex) {
        	ex.printStackTrace();
            request.setAttribute("message", "There was an error: " + ex.getMessage());
        }
		*/
        
		FileItem syllabus = fileUpload.getFileItem();
		fileService.createSyllabus(syllabus, course, authentication.getName());
		
		return "redirect:/course/" + courseId + "/syllabus";

	}
}