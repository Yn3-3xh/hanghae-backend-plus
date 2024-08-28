package org.example.junitinaction3.chapter02.assertions;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SUT {
    private String systemName;
    private boolean isVerified;
    private List<Job> jobs = new ArrayList<>();
    @Getter
    private Job currentJob;

    public SUT(String systemName) {
        this.systemName = systemName;
        this.isVerified = false;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void verify() {
        this.isVerified = true;
    }

    public void addJob(Job job) {
        jobs.add(job);
    }

    public Object[] getJobsAsArray() {
        return jobs.toArray();
    }

    public void run() {
        if (!jobs.isEmpty()) {
            currentJob = jobs.removeFirst();
            System.out.println("Running job: " + currentJob);
            return;
        }
        throw new NoJobException("실행할 작업이 없습니다!");
    }

    public void run(int jobDuration) throws InterruptedException {
        if (!jobs.isEmpty()) {
            currentJob = jobs.removeFirst();
            System.out.println("Running job: " + currentJob + " lasts " + jobDuration + " milliseconds");
            Thread.sleep(jobDuration);
            return;
        }
        throw new NoJobException("실행할 작업이 없습니다!");
    }
}
