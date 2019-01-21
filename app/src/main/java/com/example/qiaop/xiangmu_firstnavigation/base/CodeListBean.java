package com.example.qiaop.xiangmu_firstnavigation.base;

public class CodeListBean {
    /**
     * code : 200
     * ret : success
     * data : twhg
     */

    private int code;
    private String ret;
    private String data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CodeListBean{" +
                "code=" + code +
                ", ret='" + ret + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
