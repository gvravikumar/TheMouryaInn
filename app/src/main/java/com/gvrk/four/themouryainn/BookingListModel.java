package com.gvrk.four.themouryainn;

/**
 * Created by G V RAVI KUMAR on 6/6/2018.
 */

public class BookingListModel {

    private String roomtype_bookinglistmodel;
    private String roomprice_bookinglistmodel;
    private String roomstartingdate_bookinglistmodel;
    private String roomenddate_bookinglistmodel;
    private String roompaid_bookinglistmodel;
    private String roomoffersapplied_bookinglistmodel;
    private int roomimage_bookinglistmodel;

    public BookingListModel(String roomtype_bookinglistmodel, String roomprice_bookinglistmodel, String roomstartingdate_bookinglistmodel, String roomenddate_bookinglistmodel,
                            String roompaid_bookinglistmodel, String roomoffersapplied_bookinglistmodel, int roomimage_bookinglistmodel) {
        this.roomtype_bookinglistmodel = roomtype_bookinglistmodel;
        this.roomprice_bookinglistmodel = roomprice_bookinglistmodel;
        this.roomstartingdate_bookinglistmodel = roomstartingdate_bookinglistmodel;
        this.roomenddate_bookinglistmodel = roomenddate_bookinglistmodel;
        this.roompaid_bookinglistmodel = roompaid_bookinglistmodel;
        this.roomoffersapplied_bookinglistmodel = roomoffersapplied_bookinglistmodel;
        this.roomimage_bookinglistmodel = roomimage_bookinglistmodel;
    }

    public String getRoomtype_bookinglistmodel() {
        return roomtype_bookinglistmodel;
    }

    public String getRoomprice_bookinglistmodel() {
        return roomprice_bookinglistmodel;
    }

    public String getRoomstartingdate_bookinglistmodel() {
        return roomstartingdate_bookinglistmodel;
    }

    public String getRoomenddate_bookinglistmodel() {
        return roomenddate_bookinglistmodel;
    }

    public String getRoompaid_bookinglistmodel() {
        return roompaid_bookinglistmodel;
    }

    public String getRoomoffersapplied_bookinglistmodel() {
        return roomoffersapplied_bookinglistmodel;
    }

    public int getRoomimage_bookinglistmodel() {
        return roomimage_bookinglistmodel;
    }
}
