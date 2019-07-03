package edu.usts.sddb.util.Task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TimerTask {
    //秒 分 时 日 月 周 年
    @Scheduled(cron = "* * */3 * * ?")//每隔3个小时清空访问池
    public void clearIpList() {
        ViewPool.clear();
    }
}
