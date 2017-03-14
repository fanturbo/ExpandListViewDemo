package com.demo.expandlistviewdemo;

import java.util.List;

public class GrandFather {

    String cname = "GrandFather";
    List<Father> fatherList;

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public List<Father> getFatherList() {
        return fatherList;
    }

    public void setFatherList(List<Father> fatherList) {
        this.fatherList = fatherList;
    }

    public static class Father {
        public String name = "Father";
        public String id = "1";
        public List<Son> sonList;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<Son> getSonList() {
            return sonList;
        }

        public void setSonList(List<Son> sonList) {
            this.sonList = sonList;
        }
    }

    public static class Son {
        private String name = "Son";

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
