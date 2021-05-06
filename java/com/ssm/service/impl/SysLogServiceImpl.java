package com.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.ssm.dao.SysLogDao;
import com.ssm.pojo.SysLog;
import com.ssm.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author kneesh
 * @Description 系统日志服务接口的实现类
 * @date 2021/4/27-12:56
 */
@Service
@Transactional
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    private SysLogDao sysLogDao;

    @Override
    public void saveLog(SysLog sysLog) {
        sysLogDao.saveLog(sysLog);
    }

    @Override
    public List<SysLog> queryAllSysLog(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        return sysLogDao.queryAllSysLog();
    }
}
