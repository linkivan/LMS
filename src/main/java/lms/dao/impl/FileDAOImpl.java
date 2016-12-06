package lms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import lms.dao.FileDAO;
import lms.model.FileModel;

@Repository
public class FileDAOImpl implements FileDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int addFileInfo(FileModel file) {
		final String SQL_INSERT = "INSERT INTO file (upload_time, uploader_name, file_path, file_name) VALUES (?, ?, ?, ?)";
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		System.out.println("FileDAO.addFileInfo -> adding file info for : " + file.getFileName());
		jdbcTemplate.update(
			new PreparedStatementCreator() {
				java.sql.Timestamp timeStamp = new java.sql.Timestamp(new java.util.Date().getTime());
				FileModel newFile;
				
				@Override
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					System.out.println("jdbc.update -> file : " + newFile.getFileName());
					PreparedStatement ps = connection.prepareStatement(SQL_INSERT, new String [] { "id" });
					ps.setString(1, timeStamp.toString());
					ps.setString(2, newFile.getUserName());
					ps.setString(3, newFile.getFilePath());
					ps.setString(4, newFile.getFileName());
					
					return ps;
				}
				
				private PreparedStatementCreator init(FileModel file) {
					newFile = file;
					
					return this;
				}
			}.init(file),
			keyHolder);
		
		return keyHolder.getKey().intValue();
	}
	
	@Override
	public int addSyllabusInfo(int fileId, int courseId) {
		final String SQL_DELETE = "DELETE FROM syllabus WHERE course_id = ?";
		final String SQL_INSERT = "INSERT INTO syllabus (file_id, course_id) VALUES (?, ?)";
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		System.out.println("FileDAO.addSyllabusInfo -> deleting records for courseId : " + courseId);
		jdbcTemplate.update(SQL_DELETE, courseId);
		
		System.out.println("FileDAO.addSyllabusInfo -> adding fileId : " + fileId + ", courseId : " + courseId);
		jdbcTemplate.update(
			new PreparedStatementCreator() {
				int newFileId, newCourseId;
				
				@Override
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					System.out.println("jdbc.update -> fileId : " + newFileId + ", courseId : " + newCourseId);
					PreparedStatement ps = connection.prepareStatement(SQL_INSERT, new String [] { "id" });
					ps.setInt(1, newFileId);
					ps.setInt(2, newCourseId);
					
					return ps;
				}
				private PreparedStatementCreator init(int fileId, int courseId) {
					this.newFileId = fileId;
					this.newCourseId = courseId;
					
					return this;
				}
			}.init(fileId, courseId),
			keyHolder);
		
		return keyHolder.getKey().intValue();
	}
	
	@Override
	public int deleteFile(int fileId) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public FileModel getFileById(int id) {
		String SQL = "SELECT * FROM file WHERE id = ?";
		
		FileModel retVal = jdbcTemplate.queryForObject(SQL, new Object[] { id }, new RowMapper<FileModel>() {
			@Override
			public FileModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				FileModel file = new FileModel(rs.getInt("id"),
							       			   rs.getString("uploader_name"),
							       			   rs.getString("file_path"),
							       			   rs.getString("file_name"));
				
				file.setTimeStamp(rs.getTimestamp("upload_time"));
				
				return file;
			}
		});
		
		return retVal;
	}
	
	@Override
	public FileModel getSyllabusByCourseId(int courseId) {
		FileModel retVal = null;
		String    SQL = " SELECT file.id,"
				      + "        file.uploader_name,"
				      + "        file.file_path,"
				      + "        file.file_name,"
				      + "        file.upload_time"
				      + "   FROM file, syllabus "
				      + "  WHERE file.id = syllabus.file_id "
				      + "    AND syllabus.course_id = ?";
		
		try {
			retVal = jdbcTemplate.queryForObject(SQL, new Object[] { courseId }, new RowMapper<FileModel>() {
				@Override
				public FileModel mapRow(ResultSet rs, int rowNum) throws SQLException {
					FileModel file = new FileModel(rs.getInt("id"),
							       			   	   rs.getString("uploader_name"),
							       			   	   rs.getString("file_path"),
							       			   	   rs.getString("file_name"));
				
					file.setTimeStamp(rs.getTimestamp("upload_time"));
				
					return file;
				}
			});
		} catch (Exception e) {
			System.out.println("Exception in getSyllabusByCourseId :=> " + e.getMessage());
		}
		
		return retVal;
	}
}