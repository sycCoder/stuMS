package com.ynuni.stuMS.dao;

import com.ynuni.stuMS.dao.impl.StudentsDaoImpl;
import com.ynuni.stuMS.po.Students;

import java.util.List;

public interface StudentsDao {
    public List<Students> listStudents(String stuId,String name,String sex,Integer age,String department,String major,String dormitory);
    public void insertStudents(String stuId,String name,String sex,Integer age,String department,String major,String dormitory);
    public Integer deleteStudents(String stuId);
    public int dbToExcel(StudentsDao studentsDao,String stuFile);
    public List<Students> getAllByExcel(String file);
    public int excelToDb(StudentsDao studentsDao,String file);
}
