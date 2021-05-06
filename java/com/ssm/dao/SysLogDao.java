package com.ssm.dao;

import com.ssm.pojo.SysLog;

import java.util.List;

/**
 * @author kneesh
 * @description 系统日志DAO
 * @date 2021/4/19-20:38
 */
public interface SysLogDao {
    /**
     * 保存日志
     * @param sysLog
     * @date 2021/4/19-20:38
     */
    void saveLog(SysLog sysLog);

    /**
     * 查询全部日志信息
     * @return 日志列表
     * @date 2021/4/19-20:38
     */
    List<SysLog> queryAllSysLog();
}
