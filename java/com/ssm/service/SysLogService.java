package com.ssm.service;

import com.ssm.pojo.SysLog;

import java.util.List;

/**
 * @author kneesh
 * @Description 系统日志服务接口
 * @date 2021/4/27-12:37
 */
public interface SysLogService {
    /**
     * 保存日志
     * @param sysLog
     */
    void saveLog(SysLog sysLog);

    /**
     * 查询全部日志信息
     * @param page
     * @param size
     * @return
     */
    List<SysLog> queryAllSysLog(Integer page, Integer size);
}
