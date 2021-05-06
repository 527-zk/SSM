package com.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.ssm.pojo.SysLog;
import com.ssm.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

/**
 * @author kneesh
 * @Description 系统日志的Controller
 * @date 2021/4/27-10:56
 */
@Controller
@RequestMapping("/sysLog")
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;

    @RequestMapping("queryAllSysLog.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView queryAllSysLog(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page, @RequestParam(name = "size",required = true,defaultValue = "10") Integer size){
        ModelAndView mv = new ModelAndView();
        List<SysLog> sysLogList = sysLogService.queryAllSysLog(page,size);
        PageInfo pageInfo = new PageInfo(sysLogList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("syslog-list");
        return mv;
    }

}
