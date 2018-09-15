package com.shenhua.idea.plugin.easypost.etc;

import javax.swing.*;

/**
 * Created by shenhua on 2017/12/5.
 * Email shenhuanet@126.com
 *
 * @author shenhua
 */
public interface ITabLayoutCallback {

    /**
     * 创建一个新的tablayout
     */
    void createTab();

    /**
     * 关闭当前tablayout
     */
    void closeTab();

    /**
     * 获取windowTool顶层Component
     *
     * @return JComponent
     */
    JComponent getComponent();
}
