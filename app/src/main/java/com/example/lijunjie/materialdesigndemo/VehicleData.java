package com.example.lijunjie.materialdesigndemo;

public class VehicleData {

    /**
     * state : ok
     * date : 2018-08-08 08:32:48
     * place : {"CarInformationSerial":"6170775417","CarRemoteLockSts":0,"PositionX":"27.920727882115788","PositionY":"112.92904707062686","Speed":0,"Gear":1100,"Engine":0,"Dcdc":0,"Acc":0,"EMRev":24000,"EMHeat":24000,"Mileage":0,"AirCon":0,"Compressor":0,"PTC":0,"AirFan":0,"Demist":0,"Baojing":"0,0,0,0,0,0,0,0,0,0,0,0","Bms":"3922,3918,3921,3930,3925,3919,3925,3928,3925,3924,3916,3928,3924,3925,3922,3924,3922,3909,3910,3921,3918,3922,3921,3924,3925,3924,3919,3918,3925,3921,3915,3910,3913,3921,3918,3919,3919,3916,3898,3921,3915,3907,3916,3913,3919,3924,3925,3901,3915,3919,3922,3921,3919,3924,3924,3924,3921,3919,3919,3918,3925,3918,3907,3918,3919,3922,3921,3924,3918,3921,3925,3921,3921,3921,3918,3925,3919,3919,3919,3924,3922,3895,3916,3919,69,69,69,69,69,69,69,69","DateTime":"20180808 07:51:47","Date":"20180808"}
     */

    private String state;
    private String date;
    private PlaceBean place;

    @Override
    public String toString() {
        return "VehicleData{" +
                "state='" + state + '\'' +
                ", date='" + date + '\'' +
                ", place=" + place +
                '}';
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public PlaceBean getPlace() {
        return place;
    }

    public void setPlace(PlaceBean place) {
        this.place = place;
    }

    public static class PlaceBean {
        /**
         * CarInformationSerial : 6170775417
         * CarRemoteLockSts : 0
         * PositionX : 27.920727882115788
         * PositionY : 112.92904707062686
         * Speed : 0.0
         * Gear : 1100
         * Engine : 0
         * Dcdc : 0
         * Acc : 0
         * EMRev : 24000
         * EMHeat : 24000
         * Mileage : 0
         * AirCon : 0
         * Compressor : 0
         * PTC : 0
         * AirFan : 0
         * Demist : 0
         * Baojing : 0,0,0,0,0,0,0,0,0,0,0,0
         * Bms : 3922,3918,3921,3930,3925,3919,3925,3928,3925,3924,3916,3928,3924,3925,3922,3924,3922,3909,3910,3921,3918,3922,3921,3924,3925,3924,3919,3918,3925,3921,3915,3910,3913,3921,3918,3919,3919,3916,3898,3921,3915,3907,3916,3913,3919,3924,3925,3901,3915,3919,3922,3921,3919,3924,3924,3924,3921,3919,3919,3918,3925,3918,3907,3918,3919,3922,3921,3924,3918,3921,3925,3921,3921,3921,3918,3925,3919,3919,3919,3924,3922,3895,3916,3919,69,69,69,69,69,69,69,69
         * DateTime : 20180808 07:51:47
         * Date : 20180808
         */

        private String CarInformationSerial;
        private int CarRemoteLockSts;
        private String PositionX;
        private String PositionY;
        private double Speed;
        private int Gear;
        private int Engine;
        private int Dcdc;
        private int Acc;
        private int EMRev;
        private int EMHeat;
        private int Mileage;
        private int AirCon;
        private int Compressor;
        private int PTC;
        private int AirFan;
        private int Demist;
        private String Baojing;
        private String Bms;
        private String DateTime;
        private String Date;

        @Override
        public String toString() {
            return "PlaceBean{" +
                    "CarInformationSerial='" + CarInformationSerial + '\'' +
                    ", CarRemoteLockSts=" + CarRemoteLockSts +
                    ", PositionX='" + PositionX + '\'' +
                    ", PositionY='" + PositionY + '\'' +
                    ", Speed=" + Speed +
                    ", Gear=" + Gear +
                    ", Engine=" + Engine +
                    ", Dcdc=" + Dcdc +
                    ", Acc=" + Acc +
                    ", EMRev=" + EMRev +
                    ", EMHeat=" + EMHeat +
                    ", Mileage=" + Mileage +
                    ", AirCon=" + AirCon +
                    ", Compressor=" + Compressor +
                    ", PTC=" + PTC +
                    ", AirFan=" + AirFan +
                    ", Demist=" + Demist +
                    ", Baojing='" + Baojing + '\'' +
                    ", Bms='" + Bms + '\'' +
                    ", DateTime='" + DateTime + '\'' +
                    ", Date='" + Date + '\'' +
                    '}';
        }

        public String getCarInformationSerial() {
            return CarInformationSerial;
        }

        public void setCarInformationSerial(String CarInformationSerial) {
            this.CarInformationSerial = CarInformationSerial;
        }

        public int getCarRemoteLockSts() {
            return CarRemoteLockSts;
        }

        public void setCarRemoteLockSts(int CarRemoteLockSts) {
            this.CarRemoteLockSts = CarRemoteLockSts;
        }

        public String getPositionX() {
            return PositionX;
        }

        public void setPositionX(String PositionX) {
            this.PositionX = PositionX;
        }

        public String getPositionY() {
            return PositionY;
        }

        public void setPositionY(String PositionY) {
            this.PositionY = PositionY;
        }

        public double getSpeed() {
            return Speed;
        }

        public void setSpeed(double Speed) {
            this.Speed = Speed;
        }

        public int getGear() {
            return Gear;
        }

        public void setGear(int Gear) {
            this.Gear = Gear;
        }

        public int getEngine() {
            return Engine;
        }

        public void setEngine(int Engine) {
            this.Engine = Engine;
        }

        public int getDcdc() {
            return Dcdc;
        }

        public void setDcdc(int Dcdc) {
            this.Dcdc = Dcdc;
        }

        public int getAcc() {
            return Acc;
        }

        public void setAcc(int Acc) {
            this.Acc = Acc;
        }

        public int getEMRev() {
            return EMRev;
        }

        public void setEMRev(int EMRev) {
            this.EMRev = EMRev;
        }

        public int getEMHeat() {
            return EMHeat;
        }

        public void setEMHeat(int EMHeat) {
            this.EMHeat = EMHeat;
        }

        public int getMileage() {
            return Mileage;
        }

        public void setMileage(int Mileage) {
            this.Mileage = Mileage;
        }

        public int getAirCon() {
            return AirCon;
        }

        public void setAirCon(int AirCon) {
            this.AirCon = AirCon;
        }

        public int getCompressor() {
            return Compressor;
        }

        public void setCompressor(int Compressor) {
            this.Compressor = Compressor;
        }

        public int getPTC() {
            return PTC;
        }

        public void setPTC(int PTC) {
            this.PTC = PTC;
        }

        public int getAirFan() {
            return AirFan;
        }

        public void setAirFan(int AirFan) {
            this.AirFan = AirFan;
        }

        public int getDemist() {
            return Demist;
        }

        public void setDemist(int Demist) {
            this.Demist = Demist;
        }

        public String getBaojing() {
            return Baojing;
        }

        public void setBaojing(String Baojing) {
            this.Baojing = Baojing;
        }

        public String getBms() {
            return Bms;
        }

        public void setBms(String Bms) {
            this.Bms = Bms;
        }

        public String getDateTime() {
            return DateTime;
        }

        public void setDateTime(String DateTime) {
            this.DateTime = DateTime;
        }

        public String getDate() {
            return Date;
        }

        public void setDate(String Date) {
            this.Date = Date;
        }
    }
}
