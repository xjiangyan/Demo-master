package com.example.hp.demo.bean;

public class RYBasicInfo {

    /**
     * da : {"byzk":"未服兵役","csrq":"19460902","cym":"小明","hjdQh":"浙江省安吉县","hjdXxdz":"浙江省安吉县梅溪村陈桥自然村50号","hyzk":"已婚","jgQh":"浙江省安吉县","mz":"汉族","sjly":"A330523194609021618-00020-de426a18ff0f368f845fcba41a8cbbcd","validated":true,"whcd":"小学","xb":"男","xm":"洪凤鸣","yxx":"","zjhm":"A330523194609021618"}
     */

    private RYBasic da;

    public RYBasic getDa() {
        return da;
    }

    public void setDa(RYBasic da) {
        this.da = da;
    }

    public static class RYBasic {
        /**
         * byzk : 未服兵役
         * csrq : 19460902
         * cym : 小明
         * hjdQh : 浙江省安吉县
         * hjdXxdz : 浙江省安吉县梅溪村陈桥自然村50号
         * hyzk : 已婚
         * jgQh : 浙江省安吉县
         * mz : 汉族
         * sjly : A330523194609021618-00020-de426a18ff0f368f845fcba41a8cbbcd
         * validated : true
         * whcd : 小学
         * xb : 男
         * xm : 洪凤鸣
         * yxx :
         * zjhm : A330523194609021618
         */

        private String byzk;
        private String csrq;
        private String cym;
        private String hjdQh;
        private String hjdXxdz;
        private String hyzk;
        private String jgQh;
        private String mz;
        private String sjly;
        private boolean validated;
        private String whcd;
        private String xb;
        private String xm;
        private String yxx;
        private String zjhm;

        public String getByzk() {
            return byzk;
        }

        public void setByzk(String byzk) {
            this.byzk = byzk;
        }

        public String getCsrq() {
            return csrq;
        }

        public void setCsrq(String csrq) {
            this.csrq = csrq;
        }

        public String getCym() {
            return cym;
        }

        public void setCym(String cym) {
            this.cym = cym;
        }

        public String getHjdQh() {
            return hjdQh;
        }

        public void setHjdQh(String hjdQh) {
            this.hjdQh = hjdQh;
        }

        public String getHjdXxdz() {
            return hjdXxdz;
        }

        public void setHjdXxdz(String hjdXxdz) {
            this.hjdXxdz = hjdXxdz;
        }

        public String getHyzk() {
            return hyzk;
        }

        public void setHyzk(String hyzk) {
            this.hyzk = hyzk;
        }

        public String getJgQh() {
            return jgQh;
        }

        public void setJgQh(String jgQh) {
            this.jgQh = jgQh;
        }

        public String getMz() {
            return mz;
        }

        public void setMz(String mz) {
            this.mz = mz;
        }

        public String getSjly() {
            return sjly;
        }

        public void setSjly(String sjly) {
            this.sjly = sjly;
        }

        public boolean isValidated() {
            return validated;
        }

        public void setValidated(boolean validated) {
            this.validated = validated;
        }

        public String getWhcd() {
            return whcd;
        }

        public void setWhcd(String whcd) {
            this.whcd = whcd;
        }

        public String getXb() {
            return xb;
        }

        public void setXb(String xb) {
            this.xb = xb;
        }

        public String getXm() {
            return xm;
        }

        public void setXm(String xm) {
            this.xm = xm;
        }

        public String getYxx() {
            return yxx;
        }

        public void setYxx(String yxx) {
            this.yxx = yxx;
        }

        public String getZjhm() {
            return zjhm;
        }

        public void setZjhm(String zjhm) {
            this.zjhm = zjhm;
        }
    }
}
