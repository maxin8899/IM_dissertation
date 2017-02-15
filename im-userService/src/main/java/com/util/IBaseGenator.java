package com.util;

import java.awt.*;

/**
 * Created by haizhi on 2017/2/8.
 */
public interface IBaseGenator {
    /**
     * 将hash字符串转换为bool二维6*5数组
     *
     * @param hash
     * @return
     */
    public boolean[][] getBooleanValueArray(String hash);


    /**
     * 获取图片背景色
     *
     * @return
     */
    public Color getBackgroundColor();


    /**
     * 获取图案前景色
     *
     * @return
     */
    public Color getForegroundColor();
}