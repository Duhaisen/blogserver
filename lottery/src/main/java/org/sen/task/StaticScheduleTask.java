package org.sen.task;

import org.sen.service.CpHistoryService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class StaticScheduleTask {

    private static final Logger logger = LoggerFactory.getLogger(StaticScheduleTask.class);

    @Autowired
    private CpHistoryService cpHistoryService;

    //3.添加定时任务
    @Scheduled(cron = "0 0 21 * * ?")
    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)
    private void configureTasks() {

        logger.info("start at : " + DateFormatUtils.format(new Date(), DateFormatUtils.ISO_8601_EXTENDED_DATETIME_FORMAT.getPattern()));

        cpHistoryService.saveCurrent();

        logger.info("end at : " + DateFormatUtils.format(new Date(), DateFormatUtils.ISO_8601_EXTENDED_DATETIME_FORMAT.getPattern()));
    }


}