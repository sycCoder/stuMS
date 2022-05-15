package com.ynuni.stuMS.dao;

import com.ynuni.stuMS.po.Admin;

public interface AdminDao {
    public Admin getAdminByNameByPass(String adminName, String password);
}
