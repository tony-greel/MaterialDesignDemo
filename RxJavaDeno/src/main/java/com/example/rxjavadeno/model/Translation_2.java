package com.example.rxjavadeno.model;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

public class Translation_2 {


    /**
     * status : 1
     * content : {"from":"en-EU","to":"zh-CN","vendor":"baidu","out":"尓好世界<br/>","err_ no":0}
     */

    private int status;
    private ContentBean content;

    //定义 输出返回数据 的方法
    public void show() {
        Log.d("RxJava", content.out );
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ContentBean getContent() {
        return content;
    }

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public static class ContentBean {
        /**
         * from : en-EU
         * to : zh-CN
         * vendor : baidu
         * out : 尓好世界<br/>
         * err_ no : 0
         */

        private String from;
        private String to;
        private String vendor;
        private String out;
        @SerializedName("err_ no")
        private int _$Err_No113; // FIXME check this code

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public String getVendor() {
            return vendor;
        }

        public void setVendor(String vendor) {
            this.vendor = vendor;
        }

        public String getOut() {
            return out;
        }

        public void setOut(String out) {
            this.out = out;
        }

        public int get_$Err_No113() {
            return _$Err_No113;
        }

        public void set_$Err_No113(int _$Err_No113) {
            this._$Err_No113 = _$Err_No113;
        }
    }
}
