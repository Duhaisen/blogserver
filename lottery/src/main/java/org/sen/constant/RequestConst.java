package org.sen.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class RequestConst {

    @Value("${org.sen.url}")
    private String cpHistoryUrl;

    @Value("${org.sen.url-without-page}")
    private String cpHistoryUrlWithoutPage;

    @Value("${org.sen.max-page}")
    private Integer maxPage;

    @Value("${application.charset}")
    private String defaultCharset;

    public static String CP_HISTORY_URL;

    public static String CP_HISTORY_URL_WITH_PAGE;

    public static Integer CP_HISTORY_MAX_PAGE;

    public static String DEFAULT_CHARSET;


    @PostConstruct
    public void InitCpHistoryUrl() {
        CP_HISTORY_URL = cpHistoryUrl;
        CP_HISTORY_URL_WITH_PAGE = cpHistoryUrlWithoutPage;
        CP_HISTORY_MAX_PAGE = maxPage;
        DEFAULT_CHARSET = defaultCharset;
    }

}
