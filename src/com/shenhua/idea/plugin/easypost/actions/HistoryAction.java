package com.shenhua.idea.plugin.easypost.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.shenhua.idea.plugin.easypost.ui.TabbedContentPanel;

/**
 * 历史记录
 * Created by shenhua on 2017/11/28.
 * Email shenhuanet@126.com
 *
 * @author shenhua
 */
public class HistoryAction extends AnAction {

    private TabbedContentPanel tabbedContentPanel;

    public void setTabbedContentPanel(TabbedContentPanel tabbedContentPanel) {
        this.tabbedContentPanel = tabbedContentPanel;
    }

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        tabbedContentPanel.toggleHistory();
    }
}
