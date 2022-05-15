package com.ynuni.stuMS.dao.impl;

import com.ynuni.stuMS.dao.StudentsDao;
import com.ynuni.stuMS.po.Students;
import com.ynuni.stuMS.util.DBUtil;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentsDaoImpl implements StudentsDao{

    private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    @Override
    public List<Students> listStudents(String stuId,String name,String sex,Integer age,String department,String major,String dormitory){
        List<Students> list = new ArrayList<>();
        StringBuffer sql = new StringBuffer("select * from students where 1=1 ");
        if(stuId!=null&&!stuId.equals("")) {
            sql.append(" and stuId = '"+stuId+"'");
        }
        if(name!=null&&!name.equals("")) {
            sql.append(" and name ='"+name+"'");
        }
        if(sex!=null&&!sex.equals("")) {
            sql.append(" and sex = '"+sex+"'");
        }
        if(age!=null) {
            sql.append(" and age ='"+age+"'");
        }
        if(department!=null&&!department.equals("")) {
            sql.append(" and department = '"+department+"'");
        }
        if(major!=null&&!major.equals("")) {
            sql.append(" and major ='"+major+"'");
        }
        if(dormitory!=null&&!dormitory.equals("")) {
            sql.append(" and dormitory = '"+dormitory+"'");
        }
        try {
            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql.toString());
            rs = pst.executeQuery();
            while(rs.next()) {
                Students students = new Students(null,null,null,null,null,null,null);
                students.setStuId(rs.getString("stuId"));
                students.setName(rs.getString("name"));
                students.setSex(rs.getString("sex"));
                students.setAge(rs.getInt("age"));
                students.setDepartment(rs.getString("department"));
                students.setMajor(rs.getString("major"));
                students.setDormitory(rs.getString("dormitory"));
                list.add(students);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pst, con);
        }
        return list;
    }

    @Override
    public void insertStudents(String stuId,String name,String sex,Integer age,String department,String major,String dormitory){
        String sql = "insert into students(stuId,name,sex,age,department,major,dormitory) values(?,?,?,?,?,?,?)";
        try {
            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, stuId);
            pst.setString(2, name);
            pst.setString(3, sex);
            pst.setInt(4, age);
            pst.setString(5, department);
            pst.setString(6, major);
            pst.setString(7, dormitory);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pst, con);
        }
    }

    @Override
    public Integer deleteStudents(String stuId){
        int res=0;
        String sql = "delete from students where stuId='"+stuId+"'";
        try {
            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql);
            res = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pst, con);
        }
        return res;
    }

    //导出Excel
    @Override
    public int dbToExcel(StudentsDao studentsDao,String stuFile){
        try {
            WritableWorkbook wwb = null;

            // 创建可写入的Excel工作簿
            File file=new File(stuFile);
            if (file.isDirectory()) {
                System.out.println("该文件路径不正确!");
                return 0;
            }
            if (!file.exists()) {
                file.createNewFile();
                System.out.print("创建成功!");
            }
            //以file为文件名来创建一个Workbook
            wwb = Workbook.createWorkbook(file);

            // 创建工作表
            WritableSheet ws = wwb.createSheet("students1", 0);

            //查询数据库中所有的数据
            List<Students> studentsList=studentsDao.listStudents(null,null,null,null,null,null,null) ;
            //要插入到的Excel表格的行号，默认从0开始
            Label stuId= new Label(0, 0, "学号");
            Label name= new Label(1, 0, "姓名");
            Label sex= new Label(2, 0, "性别");
            Label age= new Label(3, 0, "年龄");
            Label department= new Label(4, 0, "学院");
            Label major= new Label(5, 0, "专业");
            Label dormitory= new Label(6, 0, "宿舍");

            ws.addCell(stuId);
            ws.addCell(name);
            ws.addCell(sex);
            ws.addCell(age);
            ws.addCell(department);
            ws.addCell(major);
            ws.addCell(dormitory);
            int[] a={0,1,2,3,4,5,6};
            ws.setColumnView(0,15);
            for (int i = 0; i < studentsList.size(); i++) {

                Label stuId_i= new Label(0, i+1, studentsList.get(i).getStuId()+"");
                Label name_i= new Label(1, i+1, studentsList.get(i).getName());
                Label sex_i= new Label(2, i+1, studentsList.get(i).getSex());
                Label age_i= new Label(3, i+1, studentsList.get(i).getAge()+"");
                Label department_i= new Label(4, i+1, studentsList.get(i).getDepartment()+"");
                Label major_i= new Label(5, i+1, studentsList.get(i).getMajor());
                Label dormitory_i= new Label(6, i+1, studentsList.get(i).getDormitory());
                ws.addCell(stuId_i);
                ws.addCell(name_i);
                ws.addCell(sex_i);
                ws.addCell(age_i);
                ws.addCell(department_i);
                ws.addCell(major_i);
                ws.addCell(dormitory_i);
            }

            //写进文档
            wwb.write();
            // 关闭Excel工作簿对象
            wwb.close();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 1;
    }

    //从Excel文件中查询数据
    public List<Students> getAllByExcel(String file){
        List<Students> list=new ArrayList<>();
        try {
            Workbook rwb=Workbook.getWorkbook(new File(file));
            Sheet rs=rwb.getSheet(0);//或者rwb.getSheet(0)
            int clos=rs.getColumns();//得到所有的列
            int rows=rs.getRows();//得到所有的行

            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    //第一个是列数，第二个是行数
                    String stuId=rs.getCell(j++, i).getContents();//默认最左边编号也算一列 所以这里得j++
                    String name=rs.getCell(j++, i).getContents();
                    String sex=rs.getCell(j++, i).getContents();
                    Integer age= Integer.valueOf(rs.getCell(j++, i).getContents());
                    String department=rs.getCell(j++, i).getContents();
                    String major=rs.getCell(j++, i).getContents();
                    String dormitory=rs.getCell(j++, i).getContents();

                    list.add(new Students(stuId,name,sex,age,department,major,dormitory));
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int excelToDb(StudentsDao studentsDao,String file) {
        File file1=new File(file);
        if(!file1.exists()){
            return 0;
        }
        Scanner input = new Scanner(System.in);
        List<Students> listExcel=studentsDao.getAllByExcel(file);
        DBUtil dbUtil=new DBUtil();
        for (Students student : listExcel) {
            String stuId=student.getStuId();
            if(!dbUtil.isExist(stuId)){
                String sql="insert into students (stuId,name,sex,age,department,major,dormitory) values(?,?,?,?,?,?,?)";
                String[] str=new String[]{student.getStuId(),student.getName(),student.getSex(), String.valueOf(student.getAge()),student.getDepartment(),student.getMajor(),student.getDormitory()+""};
                dbUtil.AddU(sql, str);
            }
            else {
                System.out.println("学号为"+stuId+"的学生信息已存在，是否确定覆盖(y/n)");
                String flag=input.next();
                if(flag.equals("n")){
                    continue;
                }
                else if(flag.equals("y")){
                    String sql="update students set name=?,sex=?,age=?,department=?,major=?,dormitory=? where stuId=?";
                    String[] str=new String[]{student.getName(),student.getSex(), String.valueOf(student.getAge()),student.getDepartment(),student.getMajor(),student.getDormitory(),stuId+""};
                    dbUtil.AddU(sql, str);
                    System.out.println("学生信息覆盖成功!");
                }
                else {
                    System.out.println("选择有误!默认不覆盖!");
                    continue;
                }
            }
        }
        return 1;
    }
}
