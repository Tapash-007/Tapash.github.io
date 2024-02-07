package com.example.teams;

import java.util.ArrayList;

public class DataClass {
    ArrayList<Data> data;

  support support;


  public static class Data {

      private String Id, abbreviation, city, conference, division, full_name, name;

      public Data(String id, String abbreviation, String city, String conference, String division, String full_name, String name) {
          this.Id = id;
          this.abbreviation = abbreviation;
          this.city = city;
          this.conference = conference;
          this.division = division;
          this.full_name = full_name;
          this.name = name;
      }



      public String getId() {
          return Id;
      }

      public void setId(String id) {
          this.Id = id;
      }

      public String getAbbreviation() {
          return abbreviation;
      }

      public void setAbbreviation(String abbreviation) {
          this.abbreviation = abbreviation;
      }

      public String getCity() {
          return city;
      }

      public void setCity(String city) {
          this.city = city;
      }

      public String getConference() {
          return conference;
      }

      public void setConference(String conference) {
          this.conference = conference;
      }

      public String getDivision() {
          return division;
      }

      public void setDivision(String division) {
          this.division = division;
      }

      public String getFull_name() {
          return full_name;
      }

      public void setFull_name(String full_name) {
          this.full_name = full_name;
      }

      public String getName() {
          return name;
      }

      public void setName(String name) {
          this.name = name;
      }
  }

  public static class support {

      private String url,text;

      public support(String url, String text) {
          this.url = url;
          this.text = text;
      }
  }


}


