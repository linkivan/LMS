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
	public int createFile(FileModel file) {
		final String sql = "INSERT INTO file (upload_time, uploader_name, file_path, file_name)" + " VALUES (?, ?, ?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			java.sql.Timestamp timeStamp = new java.sql.Timestamp(new java.util.Date().getTime());
			FileModel newFile;
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql, new String[] { "id" });
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
		}.init(file), keyHolder);
		
		return keyHolder.getKey().intValue();
	}
	
	@Override
	public int deleteFile(int fileId) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public FileModel getFileById(int id) {
		String sql = "SELECT * FROM file WHERE id = ?";
		
		FileModel retVal = jdbcTemplate.queryForObject(sql, new Object[] { id }, new RowMapper<FileModel>() {
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
}
