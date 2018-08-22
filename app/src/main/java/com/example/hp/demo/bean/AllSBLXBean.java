package com.example.hp.demo.bean;

import java.util.List;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class AllSBLXBean {
    /**
     * ID : 4ACF7BFBD84F47A6978712A434DD0A84
     * TEXT : 自动感知设备
     * child : [{"ID":"E15A73D04120477F963CC66356B6238F","TEXT":"人群识别","child":[{"ID":"F5332ECF9848448084CDC3EEDFA6B2CD","TEXT":"枪机"},{"ID":"A1900FD527234D6EA661504B93F2EBB3","TEXT":"球机"}]},{"ID":"6F699310F5E942988B170DE3A0BDDB3D","TEXT":"人脸识别","child":[{"ID":"47FC2AE32DDB4DC1880543683E8E93C6","TEXT":"枪机"},{"ID":"E193A9BDF27D44C7886CC7E24272F4DD","TEXT":"全局"}]},{"ID":"970318BE8485490DB445A250966E01B8","TEXT":"射频采集","child":[{"ID":"5DBA28346F8F4DDC9EF8C8583896887E","TEXT":"有线"},{"ID":"DCC0A497B7D64A84A22CAB1A2439E2E8","TEXT":"无线"}]},{"ID":"D0B5262D4D2C465CA74E676813FF25AC","TEXT":"自动感应器","child":[{"ID":"C9027DDAF2764F4DAA157AA7D4807429","TEXT":"消防专线"},{"ID":"15360BFB39734B80875404D604384CA4","TEXT":"互联网"},{"ID":"2FA085F564734329923020EC685FA904","TEXT":"电话线"}]},{"ID":"E423297BF1134F839253ACA52AD053DF","TEXT":"车脸识别","child":[{"ID":"460CD9007C0D4490B5699CBBA046A2EB","TEXT":"小区"},{"ID":"BB0D204A9FB34446803E40B6265AEA74","TEXT":"停车场"}]},{"ID":"A03C6ED509ED490FA057EB92AB6E1E12","TEXT":"视频监控","child":[{"ID":"ECA2B7ED24D2451A85C3B10CAEC77580","TEXT":"枪机"},{"ID":"9AF80A89835F4570BBE5EDDDA9AE2FCF","TEXT":"球机"},{"ID":"DEF0543C9EA647EFA02CE4F27BAC8083","TEXT":"鱼眼"}]},{"ID":"1E345F8355BC4B82BD98AB3897369635","TEXT":"MAC采集","child":[{"ID":"2F6E3D70141B479FB85B0D6E59A2F960","TEXT":"非经"},{"ID":"4D2E9BCF2019497AAA08BB4E9805876E","TEXT":"独立"},{"ID":"ADB602D18C4546A7B8FDFDAEDCA5503D","TEXT":"二合一"}]}]
     */

    private String ID;
    private String TEXT;
    private List<ChildBeanX> child;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTEXT() {
        return TEXT;
    }

    public void setTEXT(String TEXT) {
        this.TEXT = TEXT;
    }

    public List<ChildBeanX> getChild() {
        return child;
    }

    public void setChild(List<ChildBeanX> child) {
        this.child = child;
    }

    public static class ChildBeanX {
        /**
         * ID : E15A73D04120477F963CC66356B6238F
         * TEXT : 人群识别
         * child : [{"ID":"F5332ECF9848448084CDC3EEDFA6B2CD","TEXT":"枪机"},{"ID":"A1900FD527234D6EA661504B93F2EBB3","TEXT":"球机"}]
         */

        private String ID;
        private String TEXT;
        private List<ChildBean> child;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getTEXT() {
            return TEXT;
        }

        public void setTEXT(String TEXT) {
            this.TEXT = TEXT;
        }

        public List<ChildBean> getChild() {
            return child;
        }

        public void setChild(List<ChildBean> child) {
            this.child = child;
        }

        public static class ChildBean {
            /**
             * ID : F5332ECF9848448084CDC3EEDFA6B2CD
             * TEXT : 枪机
             */

            private String ID;
            private String TEXT;

            public String getID() {
                return ID;
            }

            public void setID(String ID) {
                this.ID = ID;
            }

            public String getTEXT() {
                return TEXT;
            }

            public void setTEXT(String TEXT) {
                this.TEXT = TEXT;
            }
        }
    }
}
