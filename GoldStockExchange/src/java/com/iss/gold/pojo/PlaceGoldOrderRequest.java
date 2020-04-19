package com.iss.gold.pojo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Vikram
 */
@XmlRootElement
public class PlaceGoldOrderRequest {

    private String buyerRequest;

    public String getBuyerRequest() {
        return buyerRequest;
    }

    public void setBuyerRequest(String buyerRequest) {
        this.buyerRequest = buyerRequest;
    }
}
