package com.zyk.quarterdemo.beans;

import java.util.List;

/**
 * 作者：张玉轲
 * 时间：2017/11/28
 */

public class TalkBean {

    /**
     * msg : 获取段子列表成功
     * code : 0
     * data : [{"commentNum":null,"content":"nonunion你弄您","createTime":"2017-11-26T16:34:08","jid":31,"praiseNum":null,"shareNum":null,"uid":71,"user":{"age":null,"appkey":null,"appsecret":null,"createtime":"2017-11-28T10:54:55","email":null,"fans":null,"follow":null,"gender":0,"icon":"https://www.zhaoapi.cn/images/71.jpg","latitude":null,"longitude":null,"mobile":"18612991023","money":0,"nickname":"123456","password":"111111","praiseNum":null,"token":"58231D3E963D88765BB0130473BD3636","uid":71,"userId":null,"username":"18612991023"}},{"commentNum":null,"content":"nonunion你弄您","createTime":"2017-11-26T16:32:43","jid":30,"praiseNum":null,"shareNum":null,"uid":71,"user":{"age":null,"appkey":null,"appsecret":null,"createtime":"2017-11-28T10:54:55","email":null,"fans":null,"follow":null,"gender":0,"icon":"https://www.zhaoapi.cn/images/71.jpg","latitude":null,"longitude":null,"mobile":"18612991023","money":0,"nickname":"123456","password":"111111","praiseNum":null,"token":"58231D3E963D88765BB0130473BD3636","uid":71,"userId":null,"username":"18612991023"}},{"commentNum":null,"content":"nonunion你弄您","createTime":"2017-11-26T16:31:54","jid":29,"praiseNum":null,"shareNum":null,"uid":71,"user":{"age":null,"appkey":null,"appsecret":null,"createtime":"2017-11-28T10:54:55","email":null,"fans":null,"follow":null,"gender":0,"icon":"https://www.zhaoapi.cn/images/71.jpg","latitude":null,"longitude":null,"mobile":"18612991023","money":0,"nickname":"123456","password":"111111","praiseNum":null,"token":"58231D3E963D88765BB0130473BD3636","uid":71,"userId":null,"username":"18612991023"}},{"commentNum":null,"content":"nini","createTime":"2017-11-26T16:31:28","jid":28,"praiseNum":null,"shareNum":null,"uid":71,"user":{"age":null,"appkey":null,"appsecret":null,"createtime":"2017-11-28T10:54:55","email":null,"fans":null,"follow":null,"gender":0,"icon":"https://www.zhaoapi.cn/images/71.jpg","latitude":null,"longitude":null,"mobile":"18612991023","money":0,"nickname":"123456","password":"111111","praiseNum":null,"token":"58231D3E963D88765BB0130473BD3636","uid":71,"userId":null,"username":"18612991023"}},{"commentNum":null,"content":"nini","createTime":"2017-11-26T16:28:24","jid":27,"praiseNum":null,"shareNum":null,"uid":71,"user":{"age":null,"appkey":null,"appsecret":null,"createtime":"2017-11-28T10:54:55","email":null,"fans":null,"follow":null,"gender":0,"icon":"https://www.zhaoapi.cn/images/71.jpg","latitude":null,"longitude":null,"mobile":"18612991023","money":0,"nickname":"123456","password":"111111","praiseNum":null,"token":"58231D3E963D88765BB0130473BD3636","uid":71,"userId":null,"username":"18612991023"}},{"commentNum":null,"content":"nini","createTime":"2017-11-26T16:28:17","jid":26,"praiseNum":null,"shareNum":null,"uid":71,"user":{"age":null,"appkey":null,"appsecret":null,"createtime":"2017-11-28T10:54:55","email":null,"fans":null,"follow":null,"gender":0,"icon":"https://www.zhaoapi.cn/images/71.jpg","latitude":null,"longitude":null,"mobile":"18612991023","money":0,"nickname":"123456","password":"111111","praiseNum":null,"token":"58231D3E963D88765BB0130473BD3636","uid":71,"userId":null,"username":"18612991023"}},{"commentNum":null,"content":"nini","createTime":"2017-11-26T16:28:16","jid":25,"praiseNum":null,"shareNum":null,"uid":71,"user":{"age":null,"appkey":null,"appsecret":null,"createtime":"2017-11-28T10:54:55","email":null,"fans":null,"follow":null,"gender":0,"icon":"https://www.zhaoapi.cn/images/71.jpg","latitude":null,"longitude":null,"mobile":"18612991023","money":0,"nickname":"123456","password":"111111","praiseNum":null,"token":"58231D3E963D88765BB0130473BD3636","uid":71,"userId":null,"username":"18612991023"}},{"commentNum":null,"content":"nini","createTime":"2017-11-26T16:28:15","jid":24,"praiseNum":null,"shareNum":null,"uid":71,"user":{"age":null,"appkey":null,"appsecret":null,"createtime":"2017-11-28T10:54:55","email":null,"fans":null,"follow":null,"gender":0,"icon":"https://www.zhaoapi.cn/images/71.jpg","latitude":null,"longitude":null,"mobile":"18612991023","money":0,"nickname":"123456","password":"111111","praiseNum":null,"token":"58231D3E963D88765BB0130473BD3636","uid":71,"userId":null,"username":"18612991023"}},{"commentNum":null,"content":"nini","createTime":"2017-11-26T16:28:15","jid":23,"praiseNum":null,"shareNum":null,"uid":71,"user":{"age":null,"appkey":null,"appsecret":null,"createtime":"2017-11-28T10:54:55","email":null,"fans":null,"follow":null,"gender":0,"icon":"https://www.zhaoapi.cn/images/71.jpg","latitude":null,"longitude":null,"mobile":"18612991023","money":0,"nickname":"123456","password":"111111","praiseNum":null,"token":"58231D3E963D88765BB0130473BD3636","uid":71,"userId":null,"username":"18612991023"}},{"commentNum":null,"content":"nini","createTime":"2017-11-26T16:28:15","jid":22,"praiseNum":null,"shareNum":null,"uid":71,"user":{"age":null,"appkey":null,"appsecret":null,"createtime":"2017-11-28T10:54:55","email":null,"fans":null,"follow":null,"gender":0,"icon":"https://www.zhaoapi.cn/images/71.jpg","latitude":null,"longitude":null,"mobile":"18612991023","money":0,"nickname":"123456","password":"111111","praiseNum":null,"token":"58231D3E963D88765BB0130473BD3636","uid":71,"userId":null,"username":"18612991023"}}]
     */

    private String msg;
    private String code;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * commentNum : null
         * content : nonunion你弄您
         * createTime : 2017-11-26T16:34:08
         * jid : 31
         * praiseNum : null
         * shareNum : null
         * uid : 71
         * user : {"age":null,"appkey":null,"appsecret":null,"createtime":"2017-11-28T10:54:55","email":null,"fans":null,"follow":null,"gender":0,"icon":"https://www.zhaoapi.cn/images/71.jpg","latitude":null,"longitude":null,"mobile":"18612991023","money":0,"nickname":"123456","password":"111111","praiseNum":null,"token":"58231D3E963D88765BB0130473BD3636","uid":71,"userId":null,"username":"18612991023"}
         */

        private Object commentNum;
        private String content;
        private String createTime;
        private int jid;
        private Object praiseNum;
        private Object shareNum;
        private int uid;
        private UserBean user;

        public Object getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(Object commentNum) {
            this.commentNum = commentNum;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getJid() {
            return jid;
        }

        public void setJid(int jid) {
            this.jid = jid;
        }

        public Object getPraiseNum() {
            return praiseNum;
        }

        public void setPraiseNum(Object praiseNum) {
            this.praiseNum = praiseNum;
        }

        public Object getShareNum() {
            return shareNum;
        }

        public void setShareNum(Object shareNum) {
            this.shareNum = shareNum;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean {
            /**
             * age : null
             * appkey : null
             * appsecret : null
             * createtime : 2017-11-28T10:54:55
             * email : null
             * fans : null
             * follow : null
             * gender : 0
             * icon : https://www.zhaoapi.cn/images/71.jpg
             * latitude : null
             * longitude : null
             * mobile : 18612991023
             * money : 0
             * nickname : 123456
             * password : 111111
             * praiseNum : null
             * token : 58231D3E963D88765BB0130473BD3636
             * uid : 71
             * userId : null
             * username : 18612991023
             */

            private Object age;
            private Object appkey;
            private Object appsecret;
            private String createtime;
            private Object email;
            private Object fans;
            private Object follow;
            private int gender;
            private String icon;
            private Object latitude;
            private Object longitude;
            private String mobile;
            private int money;
            private String nickname;
            private String password;
            private Object praiseNum;
            private String token;
            private int uid;
            private Object userId;
            private String username;

            public Object getAge() {
                return age;
            }

            public void setAge(Object age) {
                this.age = age;
            }

            public Object getAppkey() {
                return appkey;
            }

            public void setAppkey(Object appkey) {
                this.appkey = appkey;
            }

            public Object getAppsecret() {
                return appsecret;
            }

            public void setAppsecret(Object appsecret) {
                this.appsecret = appsecret;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public Object getEmail() {
                return email;
            }

            public void setEmail(Object email) {
                this.email = email;
            }

            public Object getFans() {
                return fans;
            }

            public void setFans(Object fans) {
                this.fans = fans;
            }

            public Object getFollow() {
                return follow;
            }

            public void setFollow(Object follow) {
                this.follow = follow;
            }

            public int getGender() {
                return gender;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public Object getLatitude() {
                return latitude;
            }

            public void setLatitude(Object latitude) {
                this.latitude = latitude;
            }

            public Object getLongitude() {
                return longitude;
            }

            public void setLongitude(Object longitude) {
                this.longitude = longitude;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public int getMoney() {
                return money;
            }

            public void setMoney(int money) {
                this.money = money;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public Object getPraiseNum() {
                return praiseNum;
            }

            public void setPraiseNum(Object praiseNum) {
                this.praiseNum = praiseNum;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public Object getUserId() {
                return userId;
            }

            public void setUserId(Object userId) {
                this.userId = userId;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }
        }
    }
}
