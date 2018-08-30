package com.example.rxjavadeno.bean;

public class UserLog {

    /**
     * code : 1000
     * msg : success
     * datas : {"id":33,"userId":"38e7f786f8234f4282c789b6ae9c7c01","headPortrait":null,"gender":null,"phoneNo":null,"type":"1","email":"tzeufg49578@chacuo.net","account":"test","password":"test","createdTime":1535074731193,"loginWay":"web","birth":null,"autograph":"这家伙很懒，没有写个性签名哦。"}
     */

    private int code;
    private String msg;
    private DatasBean datas;

    @Override
    public String toString() {
        return "UserLog{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", datas=" + datas +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * id : 33
         * userId : 38e7f786f8234f4282c789b6ae9c7c01
         * headPortrait : null
         * gender : null
         * phoneNo : null
         * type : 1
         * email : tzeufg49578@chacuo.net
         * account : test
         * password : test
         * createdTime : 1535074731193
         * loginWay : web
         * birth : null
         * autograph : 这家伙很懒，没有写个性签名哦。
         */

        private int id;
        private String userId;
        private Object headPortrait;
        private Object gender;
        private Object phoneNo;
        private String type;
        private String email;
        private String account;
        private String password;
        private long createdTime;
        private String loginWay;
        private Object birth;
        private String autograph;

        @Override
        public String toString() {
            return "DatasBean{" +
                    "id=" + id +
                    ", userId='" + userId + '\'' +
                    ", headPortrait=" + headPortrait +
                    ", gender=" + gender +
                    ", phoneNo=" + phoneNo +
                    ", type='" + type + '\'' +
                    ", email='" + email + '\'' +
                    ", account='" + account + '\'' +
                    ", password='" + password + '\'' +
                    ", createdTime=" + createdTime +
                    ", loginWay='" + loginWay + '\'' +
                    ", birth=" + birth +
                    ", autograph='" + autograph + '\'' +
                    '}';
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public Object getHeadPortrait() {
            return headPortrait;
        }

        public void setHeadPortrait(Object headPortrait) {
            this.headPortrait = headPortrait;
        }

        public Object getGender() {
            return gender;
        }

        public void setGender(Object gender) {
            this.gender = gender;
        }

        public Object getPhoneNo() {
            return phoneNo;
        }

        public void setPhoneNo(Object phoneNo) {
            this.phoneNo = phoneNo;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public long getCreatedTime() {
            return createdTime;
        }

        public void setCreatedTime(long createdTime) {
            this.createdTime = createdTime;
        }

        public String getLoginWay() {
            return loginWay;
        }

        public void setLoginWay(String loginWay) {
            this.loginWay = loginWay;
        }

        public Object getBirth() {
            return birth;
        }

        public void setBirth(Object birth) {
            this.birth = birth;
        }

        public String getAutograph() {
            return autograph;
        }

        public void setAutograph(String autograph) {
            this.autograph = autograph;
        }
    }
}
