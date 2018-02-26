package com.pigutu.app.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * tu 配置选项
 */
@Data
@ConfigurationProperties(prefix = "pigutu")
public class TuConfig {

    /**
     * 默认页数
     */
    private int pageNumber = 18;

    private int categoryPageNumber = 8;
    private int categoryViewCountPageNumber = 5;
    /**
     * 主站url
     */
    private String url = "http://www.pigutu.com/";

    /**
     * 移动端url
     */
    private String mUrl = "http://m.pigutu.com/";

    private boolean mobileDebug = false;
}
