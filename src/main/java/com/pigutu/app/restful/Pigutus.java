package com.pigutu.app.restful;

import com.google.common.collect.ImmutableList;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Desc: 前端rest相关接口
 * <p>
 * Mail: chk@terminus.io
 * Created by IceMimosa
 * Date: 2017/10/20
 */
@RestController
@RequestMapping("/api/pigutu")
public class Pigutus {

    @RequestMapping("/test")
    public List<String> test() {
        return ImmutableList.of(
                "http://s.moemoe.la/image/2017-10-09/2ca1636e-56d1-4b3a-af42-ca359963c889.jpg",
                "http://s.moemoe.la/image/2017-10-17/835fabc6-9c7f-4714-ba97-c7224e1d9b90.png",
                "http://s.moemoe.la/image/2017-10-09/2ca1636e-56d1-4b3a-af42-ca359963c889.jpg",
                "http://s.moemoe.la/image/2017-10-17/835fabc6-9c7f-4714-ba97-c7224e1d9b90.png"
        );
    }
}
