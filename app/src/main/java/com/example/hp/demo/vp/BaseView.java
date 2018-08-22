package com.example.hp.demo.vp;

import java.util.List;


public interface BaseView<T> {
    void stopRefresh();
    void updateView(List<T> datas);
    /**
     * //0：表示没有数据
     * //1：表示服务没找到
     * //2：表示网络没连接好
     * //3：表示服务异常
     * //4: 连接超时
     * //5: 登陆过期
     * //6: 没有权限
     * @param type
     */
    void showErrorInfo(int type);
}
