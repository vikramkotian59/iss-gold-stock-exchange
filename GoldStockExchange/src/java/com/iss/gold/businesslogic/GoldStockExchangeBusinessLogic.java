package com.iss.gold.businesslogic;

import com.iss.gold.pojo.Buyer;
import com.iss.gold.pojo.Consumer;
import com.iss.gold.pojo.PlaceGoldOrderRequest;
import com.iss.gold.pojo.Seller;
import java.util.*;

/**
 *
 * @author Vikram
 */
public class GoldStockExchangeBusinessLogic {

    private static List<Seller> sellersList = new ArrayList<>();

    //static code block to initialise seller details
    static {
        sellersList.add(new Seller("Seller-1", new Date(2020, 3, 18, 8, 1, 3), 500, 5));
        sellersList.add(new Seller("Seller-2", new Date(2020, 3, 18, 8, 3, 2), 700, 2));
        sellersList.add(new Seller("Seller-3", new Date(2020, 3, 18, 8, 4, 4), 800, 6));
        sellersList.add(new Seller("Seller-4", new Date(2020, 3, 18, 8, 4, 5), 600, 10));
    }

    public String placeGoldOrder(PlaceGoldOrderRequest placeGoldOrderRequest) {
        try {
            Buyer buyer;
            try {
                // System.out.println(placeGoldOrderRequest); // log input
                String[] buyerDetails = placeGoldOrderRequest.getBuyerRequest().split("/");
                buyer = new Buyer(buyerDetails[0], new Date(),
                        Integer.parseInt(buyerDetails[2]), Integer.parseInt(buyerDetails[1]));
            } catch (Exception ex) {
                ex.printStackTrace(); //error log statement
                return ("Incorrect input format");
            }

            return checkEligibleSellers(buyer);

        } catch (Exception ex) {
            ex.printStackTrace(); //error log statement
            return ("Unknown exception occured");
        }
    }

    private String checkEligibleSellers(Buyer buyer) {
        if (buyer != null) {
            List<Seller> eligibleSellersList = new ArrayList<>();
            for (Seller seller : sellersList) {
                if (seller.getRate() <= buyer.getRate()) { //sellers whose rates are not greater than requested buyer rate
                    eligibleSellersList.add(seller);
                }
            }

            List<Consumer> outputConsumersList = new ArrayList<>();

            if (!eligibleSellersList.isEmpty()) {

                Collections.sort(eligibleSellersList, new Comparator<Seller>() { //sort based on rate and timestamp
                    @Override
                    public int compare(Seller s1, Seller s2) {
                        if (s1.getRate() == s2.getRate()) {
                            return s1.getTimestamp().compareTo(s2.getTimestamp());
                        } else if (s1.getRate() > s2.getRate()) {
                            return 1;
                        } else {
                            return -1;
                        }
                    }
                });
                
                int buyerAmount = chooseFromEligibleSellers(buyer, eligibleSellersList);

                if (buyerAmount != 0) {
                    outputConsumersList.add(buyer);
                }

            } else {
                outputConsumersList.add(buyer);
            }

            outputConsumersList.addAll(sellersList);

            return getGoldOrderResponse(outputConsumersList);

        } else {
            return ("Unknown exception occured");
        }
    }

    private int chooseFromEligibleSellers(Buyer buyer, List<Seller> eligibleSellersList) {
        int buyerAmount = buyer.getAmount();
        for (Seller seller : eligibleSellersList) {
            if (seller.getAmount() > buyerAmount) { //if seller has more gold in KG
                seller.setAmount(seller.getAmount() - buyerAmount);
                buyerAmount = 0;
                break;
            } else if (seller.getAmount() < buyerAmount) { //if seller has less gold in KG
                buyerAmount = buyerAmount - seller.getAmount();
                seller.setAmount(0);

                sellersList.remove(sellersList.indexOf(seller)); //remove from list
            } else { // if equal
                buyerAmount = 0;
                seller.setAmount(0);
                break;
            }
        }
        buyer.setAmount(buyerAmount);
        return buyerAmount;
    }

    private String getGoldOrderResponse(List<Consumer> outputSellersList) {
        String display = null;
        for (Consumer consumer : outputSellersList) {
            if (display == null) {
                display = consumer.getDisplayString();
            } else {
                display += "\n" + consumer.getDisplayString();
            }
        }
        return display;
    }
}
