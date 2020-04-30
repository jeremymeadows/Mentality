package Mentality.utils;

import Mentality.DomainLayer.ReportMaker;
import Mentality.DomainLayer.ReportObj;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

public class CronScheduler {
    ReportMaker reportMaker = ReportMaker.getInstance();
    Date date;

    // static variable single_instance of type Singleton
    private static CronScheduler single_instance = null;

    // static method to create instance of Singleton class
    public static CronScheduler getInstance()
    {
        if (single_instance == null)
            single_instance = new CronScheduler();

        return single_instance;
    }

    //update report every 20 seconds
    @Scheduled(cron = "*/10 * * * * *")
    public void run () throws InterruptedException{
        date = new Date();
        reportMaker.setReport(new ReportObj());
        reportMaker.generateReport();
        System.out.println("Cron scheduler is running at " + date);
        Thread.sleep(10000);

    }

    public ReportObj getReportObj() {
        System.out.println("Sending data for updated Reports GUIS...");
        reportMaker.getReportObj().averageMoods();
        reportMaker.getReportObj().workoutInfo();
        return reportMaker.getReportObj();

    }
}
