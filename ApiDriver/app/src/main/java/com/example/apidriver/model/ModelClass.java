package com.example.apidriver.model;

import java.util.ArrayList;

public class ModelClass {

    public MRDataTable MRData;

    public ModelClass(MRDataTable mrData) {
        this.MRData = mrData;
    }

    public static class MRDataTable {
        public DriverTable DriverTable;

        public MRDataTable(DriverTable driverTable) {
            this.DriverTable = driverTable;
        }

        public static class DriverTable {
            public ArrayList<DriversTable> Drivers;

            public DriverTable(ArrayList<DriversTable> drivers) {
                this.Drivers = drivers;
            }

            public ArrayList<DriversTable> getDrivers() {
                return Drivers;
            }

            public void setDrivers(ArrayList<DriversTable> drivers) {
                this.Drivers = drivers;
            }

            public static class DriversTable {

                public String driverId, url, givenName, familyName, dateOfBirth, nationality;


                public DriversTable(String driverId, String url, String givenName, String familyName, String dateOfBirth, String nationality) {
                    this.driverId = driverId;
                    this.url = url;
                    this.givenName = givenName;
                    this.familyName = familyName;
                    this.dateOfBirth = dateOfBirth;
                    this.nationality = nationality;
                }


                public String getDriverId() {
                    return driverId;
                }

                public void setDriverId(String driverId) {
                    this.driverId = driverId;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getGivenName() {
                    return givenName;
                }

                public void setGivenName(String givenName) {
                    this.givenName = givenName;
                }

                public String getFamilyName() {
                    return familyName;
                }

                public void setFamilyName(String familyName) {
                    this.familyName = familyName;
                }

                public String getDateOfBirth() {
                    return dateOfBirth;
                }

                public void setDateOfBirth(String dateOfBirth) {
                    this.dateOfBirth = dateOfBirth;
                }

                public String getNationality() {
                    return nationality;
                }

                public void setNationality(String nationality) {
                    this.nationality = nationality;
                }
            }

        }
    }


}
