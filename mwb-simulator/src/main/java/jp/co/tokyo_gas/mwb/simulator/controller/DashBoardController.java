package jp.co.tokyo_gas.mwb.simulator.controller;


import jp.co.tokyo_gas.mwb.simulator.util.ObjectMapperHelper;
import jp.co.tokyo_gas.mwb.simulator.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.tokyo_gas.mwb.simulator.common.RespResult;
import jp.co.tokyo_gas.mwb.simulator.common.RespResultWrapUtil;
import jp.co.tokyo_gas.mwb.simulator.service.TestDataService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class DashBoardController {

    @Autowired
    private TestDataService testDataService;

    @GetMapping("/services/mwb-dashboard")
    public void dashboradInit(HttpServletResponse response) {

        // 正常ケース
        Object jsonData = testDataService.GetJsonData(Thread.currentThread().getStackTrace()[1].getMethodName());
        if (StringUtils.isEmpty(jsonData)) {
            ResponseUtil.error(response);
            return;
        }

        if (StringUtils.isEmpty(jsonData)) {
            ResponseUtil.error(response);
            return;
        }
        ResponseUtil.ok(response, jsonData);
    }
}

