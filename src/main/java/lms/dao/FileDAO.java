package lms.dao;

import lms.model.FileModel;

public interface FileDAO {
	int createFile(FileModel file);
	int deleteFile(int fileId);
	FileModel getFileById(int id);
	FileModel getSyllabusByCourseId(int courseId);
	int addSyllabusInfo(int fileId, int id);
}