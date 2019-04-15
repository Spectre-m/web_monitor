package com.zjgsu.controller;

import com.zjgsu.pojo.History;
import com.zjgsu.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Spect on 2018/12/1.
 */

@Controller
public class HistoryController {

    private final HistoryService historyService;

    @Autowired
    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    // 获取 History 总数
    @RequestMapping(value = "/getSumOfHistoryByUsername", method = RequestMethod.POST)
    @ResponseBody
    public int getSumOfHistoryByUsername(String username) {
        return historyService.getSumOfHistoryByUsername(username);
    }

    // 获取对应的 History 记录，并分页
    @RequestMapping(value = "/findHistoryByUsernamePaging", method = RequestMethod.POST)
    @ResponseBody
    public List<History > findHistoryByUsernamePaging( String username, int start, int end) {
        return historyService.findHistoryByUsernamePaging(username, start, end);
    }
}
