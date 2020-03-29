package Mentality.utils;

import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

public class CronScheduler {
    Date date;

    //send report every Sunday at 6am
    @Scheduled (cron = "0 0 6 * * SUN")
    public void run () throws InterruptedException{
        date = new Date();
        ReportMaker.generateReport();
        System.out.println("Cron scheduler is running at " + date);
        Thread.sleep(20000);
    }

    public Date getDate() {
        if (date == null)
            date = new Date();
        return date;
    }
}
