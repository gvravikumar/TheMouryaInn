package com.gvrk.four.themouryainn;

/**
 * Created by G V RAVI KUMAR on 6/2/2018.
 */

public class reservations_model {
    public String roomType, roomPrice, roomBookStartDate , roomBookEndDate, paymentStatus, offerApplied;
    public reservations_model(String roomType, String roomPrice, String roomBookStartDate , String roomBookEndDate, String paymentStatus, String offerApplied) {
        this.roomType = roomType;
        this.roomPrice = roomPrice;
        this.roomBookStartDate = roomBookStartDate;
        this.roomBookEndDate = roomBookEndDate;
        this.paymentStatus = paymentStatus;
        this.offerApplied = offerApplied;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getRoomPrice() {
        return roomPrice;
    }

    public String getRoomBookStartDate() {
        return roomBookStartDate;
    }

    public String getRoomBookEndDate() {
        return roomBookEndDate;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public String getOfferApplied() {
        return offerApplied;
    }
}
