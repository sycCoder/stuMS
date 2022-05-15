package com.ynuni.stuMS.view.impl;

import com.ynuni.stuMS.dao.StudentsDao;
import com.ynuni.stuMS.dao.impl.StudentsDaoImpl;
import com.ynuni.stuMS.po.Students;
import com.ynuni.stuMS.view.StudentsView;

import java.util.List;
import java.util.Scanner;

public class StudentsViewImpl implements StudentsView{

    private Scanner input = new Scanner(System.in);
    @Override
    public void listStudentsAll(){
        StudentsDao dao = new StudentsDaoImpl();
        List<Students> list = dao.listStudents(null,null,null,null,null,null,null);
        System.out.println("学号\t\t姓名\t\t性别\t\t年龄\t\t学院\t\t\t\t专业\t\t\t宿舍");
        for(Students s : list) {
            System.out.println(s.getStuId()+"\t\t"+s.getName()+"\t\t"+s.getSex()+"\t\t"+s.getAge()+"\t\t"+s.getDepartment()+"\t\t\t"+s.getMajor()+"\t\t"+s.getDormitory());
        }
    }
    @Override
    public void listStudents(){
        String stuId = "";
        String name = "";
        String sex = "";
        Integer age=null;
        String department = "";
        String major = "";
        String dormitory = "";

        String inputStr = "";
        System.out.println("请选择您需要查询方式：\n======= 1.学号===2.姓名===3.性别===4.年龄===5.学院===6.专业===7.宿舍 =======");
        inputStr=input.next();
        switch (inputStr){
            case "1":
                System.out.println("请输入学生学号：");
                stuId = input.next();
                break;
            case "2":
                System.out.println("请输入学生姓名：");
                name = input.next();
                break;
            case "3":
                System.out.println("请输入学生性别：");
                sex = input.next();
                break;
            case "4":
                System.out.println("请输入学生年龄：");
                age = input.nextInt();
                break;
            case "5":
                System.out.println("请输入学生所属学院：");
                department = input.next();
                break;
            case "6":
                System.out.println("请输入学生专业：");
                major = input.next();
                break;
            case "7":
                System.out.println("请输入学生宿舍：");
                dormitory = input.next();
                break;
            default:
                System.out.println("-------------------------查询失败!(请输入合理选项)-------------------------");
                return;
        }
        StudentsDao dao = new StudentsDaoImpl();
        List<Students> list = dao.listStudents(stuId, name ,sex,age,department,major,dormitory);
        System.out.println("学号\t\t姓名\t\t性别\t\t年龄\t\t学院\t\t\t专业\t\t宿舍");
        for(Students s : list) {
            System.out.println(s.getStuId()+"\t\t"+s.getName()+"\t\t"+s.getSex()+"\t\t"+s.getAge()+"\t\t"+s.getDepartment()+"\t\t"+s.getMajor()+"\t"+s.getDormitory());
        }
    }
    @Override
    public void insertStudents(){
        System.out.println("请输入学生学号：");
        String stuId = input.next();
        System.out.println("请输入学生姓名：");
        String name = input.next();
        System.out.println("请输入学生性别：");
        String sex = input.next();
        System.out.println("请输入学生年龄：");
        Integer age = input.nextInt();
        System.out.println("请输入学生所属学院：");
        String department = input.next();
        System.out.println("请输入学生专业：");
        String major = input.next();
        System.out.println("请输入学生宿舍：");
        String dormitory = input.next();
        StudentsDao dao = new StudentsDaoImpl();
        dao.insertStudents(stuId,name,sex,age,department,major,dormitory);
        System.out.println("添加学生成功！");
    }
    @Override
    public void deleteStudents(){
        System.out.println("请输入学生学号：");
        String stuId = input.next();
        StudentsDao dao = new StudentsDaoImpl();
        System.out.println("确认要删除吗(y/n)：");
        if(input.next().equals("y")) {
            Integer result = dao.deleteStudents(stuId);
            if(result==1) {
                System.out.println("删除学生信息成功！");
            }else {
                System.out.println("删除学生信息失败！");
            }
        }
    }
    @Override
    public void dbToExcel(){
        StudentsDao studentsDao=new StudentsDaoImpl();
        System.out.println("请输入要导出的Excel文件路径：");
        String file=input.next();
        studentsDao.dbToExcel(studentsDao,file);
        System.out.println("已经将数据导出为Excel文件!");
    }

    @Override
    public void excelToDb(){
        StudentsDao studentsDao=new StudentsDaoImpl();
        System.out.println("请输入要导入的Excel文件路径：");
        String file=input.next();
        int flag=studentsDao.excelToDb(studentsDao,file);
        if(flag==0){
            System.out.println("对不起，您要导入的Excel文件不存在!");
        }
        else if (flag==1){
            System.out.println("已经将Excel文件数据导入到数据库!");
        }

    }
}
