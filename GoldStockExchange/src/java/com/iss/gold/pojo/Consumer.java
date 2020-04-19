package com.iss.gold.pojo;

import java.util.Date;

/**
 * 
 * @author Vikram
 */
public abstract class Consumer {

    private String name;
    private Date timestamp;
    private int rate;
    private int amount;

    public Consumer(String name, Date timestamp, int rate, int amount) {
        this.name = name;
        this.timestamp = timestamp;
        this.rate = rate;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getDisplayString() {
        return name + "/" + rate + "/" + amount;
    }

    @Override
    public String toString() {
        return "Consumer{" + "name=" + name + ", timestamp=" + timestamp + ", rate=" + rate + ", amount=" + amount + '}';
    }
}
