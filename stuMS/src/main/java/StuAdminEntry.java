import com.ynuni.stuMS.dao.StudentsDao;
import com.ynuni.stuMS.dao.impl.StudentsDaoImpl;
import com.ynuni.stuMS.po.Admin;
import com.ynuni.stuMS.po.Students;
import com.ynuni.stuMS.view.AdminView;
import com.ynuni.stuMS.view.StudentsView;
import com.ynuni.stuMS.view.impl.AdminViewImpl;
import com.ynuni.stuMS.view.impl.StudentsViewImpl;

import java.util.List;
import java.util.Scanner;

public class StuAdminEntry {
    public void work() {
        Scanner input = new Scanner(System.in);

        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("|\t\t\t\t\t\t\t\t\t  学生信息管理系统  \t\t\t\t\t\t\t\t\t|");
        System.out.println("-----------------------------------------------------------------------------------------");

        AdminView adminView = new AdminViewImpl();
        StudentsView studentsView=new StudentsViewImpl();
        Admin admin = adminView.login();

        //登录
        if(admin!=null) {
            int menu = 0;
            while(menu!=5) {
                //输出主菜单
                System.out.println("\n======= 1.所有学生信息=2.搜索学生=3.添加学生=4.删除学生=5.退出系统=6.导出Excel=7.导入Excel =======");
                System.out.println("请输入你的选择：");
                menu = input.nextInt();
                switch(menu) {
                    case 1:
                        studentsView.listStudentsAll();
                        break;
                    case 2:
                        studentsView.listStudents();
                        break;
                    case 3:
                        studentsView.insertStudents();
                        break;
                    case 4:
                        studentsView.deleteStudents();
                        break;
                    case 5:
                        System.out.println("--------------------------使---用---愉---快---！-------------------------");
                        break;
                    case 6:
                        studentsView.dbToExcel();
                        break;
                    case 7:
                        studentsView.excelToDb();
                        break;
                    default:
                        System.out.println("没有这个选项！\n");
                        break;
                }
            }

        }else {
            System.out.println("\n管理员名称或密码输入错误!\n");
        }
    }

    public static void main(String[] args) {
        new StuAdminEntry().work();
    }

}
