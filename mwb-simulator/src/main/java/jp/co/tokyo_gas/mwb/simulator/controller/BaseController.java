package jp.co.tokyo_gas.mwb.simulator.controller;


import jp.co.tokyo_gas.mwb.simulator.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.tokyo_gas.mwb.simulator.service.TestDataService;

import javax.servlet.http.HttpServletResponse;

@RestController
public class BaseController {

    @Autowired
    private TestDataService testDataService;

    @GetMapping("/user/mwb-properties")
    public void getBukenlistNew(HttpServletResponse response) {

        Object jsonData = testDataService.GetJsonData(Thread.currentThread().getStackTrace()[1].getMethodName());
        if (StringUtils.isEmpty(jsonData))
            ResponseUtil.error(response);
        ResponseUtil.ok(response, jsonData);
    }

    @PostMapping("/user/mwb-logout")
    public void logout(HttpServletResponse response) {

        // 正常ケース
        Object jsonData = testDataService.GetJsonData(Thread.currentThread().getStackTrace()[1].getMethodName());
        if (StringUtils.isEmpty(jsonData))
            ResponseUtil.error(response);
        ResponseUtil.ok(response, jsonData);
    }
}

