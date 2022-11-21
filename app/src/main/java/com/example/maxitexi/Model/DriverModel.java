package com.example.maxitexi.Model;

import java.util.List;

public class DriverModel {
    List<DriverData> ResultData = null;
    boolean ResultStatus;
    String ResultMessage;

    public DriverModel() {
    }

    public List<DriverData> getResultData() {
        return ResultData;
    }

    public void setResultData(List<DriverData> resultData) {
        ResultData = resultData;
    }

    public boolean isResultStatus() {
        return ResultStatus;
    }

    public void setResultStatus(boolean resultStatus) {
        ResultStatus = resultStatus;
    }

    public String getResultMessage() {
        return ResultMessage;
    }

    public void setResultMessage(String resultMessage) {
        ResultMessage = resultMessage;
    }

   public static class DriverData{
        int DriverID;
        String DriverName;
        String EmailID;
        String MobileNo;

        public DriverData(int driverID, String driverName, String emailID, String mobileNo) {
            DriverID = driverID;
            DriverName = driverName;
            EmailID = emailID;
            MobileNo = mobileNo;
        }

        public int getDriverID() {
            return DriverID;
        }

        public void setDriverID(int driverID) {
            DriverID = driverID;
        }

        public String getDriverName() {
            return DriverName;
        }

        public void setDriverName(String driverName) {
            DriverName = driverName;
        }

        public String getEmailID() {
            return EmailID;
        }

        public void setEmailID(String emailID) {
            EmailID = emailID;
        }

        public String getMobileNo() {
            return MobileNo;
        }

        public void setMobileNo(String mobileNo) {
            MobileNo = mobileNo;
        }
    }
}


