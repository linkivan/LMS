package lms.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import lms.model.CourseModel;
import lms.model.UIMenu;
import lms.model.UserModel;
import lms.service.CourseService;

@Controller
public class CourseController {

	@Autowired
	private CourseService courseService;
	@Autowired
	private ServletContext servletContext;

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String dashboardPage() {
		return "redirect:/courses/";
	}

	@RequestMapping(value = { "/courses" }, method = RequestMethod.GET)
	public ModelAndView coursesPage(String code, String semester, Authentication authentication) {
		ModelAndView model = new ModelAndView();
		List<CourseModel> courses;
		if ("[ROLE_ADMIN]".equals(authentication.getAuthorities().toString())) {
			courses = courseService.getCourseByCodeAndSemester(code, semester);
		} else {
			courses = courseService.getCoursesByUserId(((UserModel) authentication.getPrincipal()).getId());
		}
		model.addObject("uiMenu", new UIMenu("", 0, false));
		model.addObject("courses", courses);
		model.addObject("code", code);
		model.addObject("semester", semester);
		model.setViewName("courses");
		return model;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/courses/new", method = RequestMethod.GET)
	public ModelAndView coursesForm() {
		ModelAndView model = new ModelAndView();
		model.addObject("uiMenu", new UIMenu("", 0, false));
		model.setViewName("coursesForm");
		return model;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/courses", method = RequestMethod.POST)
	public String addCourses(CourseModel course) {
		int id = courseService.addCourse(course);
		return "redirect:/course/" + id;

	}

	@RequestMapping(value = { "/course/{id}", "/course/{id}/information" }, method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_ADMIN') or @userService.isCurrentUserinCourse(authentication, #id)")
	public ModelAndView coursePage(@PathVariable("id") int id) {
		ModelAndView model = new ModelAndView();
		CourseModel course = courseService.getCourseById(id);
		model.addObject("uiMenu", new UIMenu(course.getCode(), 1, true));
		model.addObject("course", course);
		model.setViewName("courseInfo");
		return model;

	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/course/{id}", method = RequestMethod.DELETE)
	public String deleteCourse(@PathVariable("id") int id) {
		courseService.deleteCourse(id);
		return "redirect:/courses/";

	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/course/{id}/info", method = RequestMethod.GET)
	public ModelAndView courseModifyForm(@PathVariable("id") int id) {
		ModelAndView model = new ModelAndView();
		CourseModel course = courseService.getCourseById(id);
		model.addObject("uiMenu", new UIMenu(course.getCode(), 1, true));
		model.addObject("course", course);
		model.setViewName("courseInfoForm");
		return model;

	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/course/{id}", method = RequestMethod.PUT)
	public String modifyCourse(@PathVariable("id") int id, CourseModel course) {
		course.setId(id);
		courseService.modifyCourse(course);
		return "redirect:/course/" + id;

	}

	@RequestMapping(value = "/course/{id}/files/{fileName:.+}", method = RequestMethod.GET)
	public void downloadFile(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("fileName") String fileName, String fileLocation) throws IOException {
		// If user is not authorized - he should be thrown out from here itself
		// Authorized user will download the file

		File file = new File(fileLocation + "/" + fileName);

		if (!file.exists()) {
			System.out.println("File doesn't exist or, not found!");
			// Throw an exception, or send 404 if file not found
			response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
			return;
		}

		String mimeType = servletContext.getMimeType(file.getName());

		if (mimeType == null) {
			// set to binary type if MIME mapping not found
			mimeType = "application/octet-stream";
		}

		System.out.println("MIME type : " + mimeType);

		response.reset();
		response.setContentType(mimeType);
		response.setContentLength((int) file.length());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");

		System.out.println("File name           : " + file.getName());
		System.out.println("File absolute path  : " + file.getAbsolutePath());
		System.out.println("File canonical path : " + file.getCanonicalPath());

		OutputStream out = null;

		try {
			FileInputStream in = new FileInputStream(file);
			byte[] buffer = new byte[1024];
			int length = 0;

			out = response.getOutputStream();
			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}
			in.close();
			out.flush();
		} catch (IOException err) {
			err.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
					System.out.println("File downloaded successfully...");
				}
			} catch (IOException err) {
				err.printStackTrace();
			}
		}

		/*
		 * String dataDirectory =
		 * request.getServletContext().getRealPath(fileLocation); Path file =
		 * Paths.get(dataDirectory, fileName); if (Files.exists(file)) {
		 * response.setContentType("application/pdf");
		 * response.addHeader("Content-Disposition", "attachment; filename=" +
		 * fileName); try { Files.copy(file, response.getOutputStream());
		 * response.getOutputStream().flush(); } catch (IOException ex) {
		 * ex.printStackTrace(); } }
		 */
	}
}