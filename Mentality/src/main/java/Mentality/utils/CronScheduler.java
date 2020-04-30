package Mentality.utils;

import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

public class CronScheduler {
    ReportMaker reportMaker = new ReportMaker();
    Date date;

    //change to update surveys every minute
    //send report every Sunday at 6am
//    @Scheduled (cron = "0 0 6 * * SUN")
    @Scheduled(cron = "*/20 * * * * *")
    public void run () throws InterruptedException{
        date = new Date();
        reportMaker.generateReport();
        System.out.println("Cron scheduler is running at " + date);
        Thread.sleep(10000);

    }

    public Date getDate() {
        if (date == null)
            date = new Date();
        return date;
    }
}
