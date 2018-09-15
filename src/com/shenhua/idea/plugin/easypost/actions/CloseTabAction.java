package com.shenhua.idea.plugin.easypost.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindowManager;
import com.shenhua.idea.plugin.easypost.etc.Constant;
import com.shenhua.idea.plugin.easypost.ui.ITabs;
import com.shenhua.idea.plugin.easypost.ui.TabbedContentPanel;

/**
 * Created by shenhua on 2017-11-29-0029.
 *
 * @author shenhua
 * Email shenhuanet@126.com
 */
public class CloseTabAction extends AnAction {

    private TabbedContentPanel tabbedContentPanel;
    private Project project;

    public void setTabbedContentPanel(TabbedContentPanel tabbedContentPanel) {
        this.tabbedContentPanel = tabbedContentPanel;
    }

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        this.project = anActionEvent.getProject();
        ITabs tabs = tabbedContentPanel.getTabs();
        if (tabs == null || tabs.getTabCount() < 2) {
            close();
        } else {
            tabbedContentPanel.closeCurrentTab();
        }
    }

    private void close() {
        if (project != null) {
            ToolWindowManager.getInstance(project).getToolWindow(Constant.TOOL_WINDOW_ID)
                    .getContentManager().removeAllContents(true);
        }
    }
}
