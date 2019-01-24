package com.example.qiaop.xiangmu_firstnavigation.entity;

import java.util.List;

public class DownListBean {

    /**
     * cursor : 153051324002c98bdb6db04b1e839c22d36ef19ca2
     * newList : [{"imageListThumb":["http://cn.ttfly.com/file/upload/201806/15/0512296412328.jpg"],"layoutType":"1","newsId":"dd5cbfef9a204af38e04ef97a58e0c5a","origin":"","pageviews":9211,"publishTime":"2018-07-04","title":"不看不知道，一看吓一跳的2018珠海航展又来了！！"},{"imageListThumb":["http://www.lyunx.com/data/attachment/portal/201807/02/185906wtibxz84ov66ai6d.jpg.thumb.jpg"],"layoutType":"2","newsId":"80edd8bb70d94133ac8cab321c1c7a35","origin":"","pageviews":5966,"publishTime":"2018-07-02","title":"关于航空工业对外开放有关政策解读"},{"imageListThumb":["http://www.lyunx.com/data/attachment/portal/201807/02/184632ywcbr3rujqbin7j7.jpg.thumb.jpg"],"layoutType":"2","newsId":"d52b14aa65df4833893e512f9bb8a139","origin":"","pageviews":2128,"publishTime":"2018-07-02","title":"雄安航空要来了！南航拟出资100亿元设立子公司"},{"imageListThumb":["http://www.lyunx.com/data/attachment/portal/201807/02/145543wgvrmzkglg568tw5.jpg.thumb.jpg"],"layoutType":"2","newsId":"cb4eea0d92fb43ebb9fe9306efae606f","origin":"","pageviews":1083,"publishTime":"2018-07-02","title":"女子邂逅\"机长\"被骗60余万 意外怀孕要转3万作惩罚"},{"imageListThumb":["http://www.lyunx.com/data/attachment/portal/201807/02/144601ehnu2h6rr2juhrxe.jpg.thumb.jpg"],"layoutType":"2","newsId":"42cd467f82d14d3a905686ea75e4362b","origin":"","pageviews":1027,"publishTime":"2018-07-02","title":"镇雄通用机场空域使用方案军地协调会召开"},{"imageListThumb":["http://www.lyunx.com/data/attachment/portal/201807/02/143849f7zx1i1xz187iqob.jpg.thumb.jpg"],"layoutType":"2","newsId":"0547641ccf2749259d2723ca3505926b","origin":"","pageviews":892,"publishTime":"2018-07-02","title":"2020年我国通用机场将超500个 部分地区将县县通机场"},{"imageListThumb":["http://www.lyunx.com/data/attachment/portal/201807/02/143349n3u5d53z573im3es.jpg.thumb.jpg"],"layoutType":"2","newsId":"02c98bdb6db04b1e839c22d36ef19ca2","origin":"","pageviews":1135,"publishTime":"2018-07-02","title":"美国加州野火失控延烧 超过100辆消防车和10多架直升机参与灭火"}]
     */

    private String cursor;
    private List<NewListBean> newList;

    public String getCursor() {
        return cursor;
    }

    public void setCursor(String cursor) {
        this.cursor = cursor;
    }

    public List<NewListBean> getNewList() {
        return newList;
    }

    public void setNewList(List<NewListBean> newList) {
        this.newList = newList;
    }

    @Override
    public String toString() {
        return "DownListBean{" +
                "cursor='" + cursor + '\'' +
                ", newList=" + newList +
                '}';
    }

    public static class NewListBean {
        /**
         * imageListThumb : ["http://cn.ttfly.com/file/upload/201806/15/0512296412328.jpg"]
         * layoutType : 1
         * newsId : dd5cbfef9a204af38e04ef97a58e0c5a
         * origin :
         * pageviews : 9211
         * publishTime : 2018-07-04
         * title : 不看不知道，一看吓一跳的2018珠海航展又来了！！
         */

        private String layoutType;
        private String newsId;
        private String origin;
        private int pageviews;
        private String publishTime;
        private String title;
        private List<String> imageListThumb;

        public String getLayoutType() {
            return layoutType;
        }

        public void setLayoutType(String layoutType) {
            this.layoutType = layoutType;
        }

        public String getNewsId() {
            return newsId;
        }

        public void setNewsId(String newsId) {
            this.newsId = newsId;
        }

        public String getOrigin() {
            return origin;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }

        public int getPageviews() {
            return pageviews;
        }

        public void setPageviews(int pageviews) {
            this.pageviews = pageviews;
        }

        public String getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(String publishTime) {
            this.publishTime = publishTime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getImageListThumb() {
            return imageListThumb;
        }

        public void setImageListThumb(List<String> imageListThumb) {
            this.imageListThumb = imageListThumb;
        }

        @Override
        public String toString() {
            return "NewListBean{" +
                    "layoutType='" + layoutType + '\'' +
                    ", newsId='" + newsId + '\'' +
                    ", origin='" + origin + '\'' +
                    ", pageviews=" + pageviews +
                    ", publishTime='" + publishTime + '\'' +
                    ", title='" + title + '\'' +
                    ", imageListThumb=" + imageListThumb +
                    '}';
        }
    }
}
