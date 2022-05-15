package com.ynuni.stuMS.po;

public class Students {
    private String stuId;
    private String name;
    private String sex;
    private Integer age;
    private String department;
    private String major;
    private String dormitory;

    public Students(String stuId, String name, String sex, Integer age, String department, String major, String dormitory) {
        this.stuId=stuId;
        this.name=name;
        this.sex=sex;
        this.age=age;
        this.department=department;
        this.major=major;
        this.dormitory=dormitory;
    }


    @Override
    public String toString(){
        return "\n学生学号:"+this.stuId+
                "\n学生姓名:"+this.name+
                "\n学生性别:"+this.sex+
                "\n学生年龄:"+this.age+
                "\n学生所属学院:"+this.department+
                "\n学生专业:"+this.major+
                "\n学生宿舍:"+this.dormitory;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getDormitory() {
        return dormitory;
    }

    public void setDormitory(String dormitory) {
        this.dormitory = dormitory;
    }
}
