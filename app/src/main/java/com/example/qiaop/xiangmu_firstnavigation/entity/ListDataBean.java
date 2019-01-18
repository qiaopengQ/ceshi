package com.example.qiaop.xiangmu_firstnavigation.entity;

import java.util.List;

public class ListDataBean {
    private List<NewsChannelListBean> newsChannelList;

    public List<NewsChannelListBean> getNewsChannelList() {
        return newsChannelList;
    }

    public void setNewsChannelList(List<NewsChannelListBean> newsChannelList) {
        this.newsChannelList = newsChannelList;
    }

    @Override
    public String toString() {
        return "ListDataBean{" +
                "newsChannelList=" + newsChannelList +
                '}';
    }

    public static class NewsChannelListBean {
        /**
         * channelId : 0
         * channelName : 资讯
         */

        private String channelId;
        private String channelName;

        public String getChannelId() {
            return channelId;
        }

        public void setChannelId(String channelId) {
            this.channelId = channelId;
        }

        public String getChannelName() {
            return channelName;
        }

        public void setChannelName(String channelName) {
            this.channelName = channelName;
        }

        @Override
        public String toString() {
            return "NewsChannelListBean{" +
                    "channelId='" + channelId + '\'' +
                    ", channelName='" + channelName + '\'' +
                    '}';
        }
    }
}
