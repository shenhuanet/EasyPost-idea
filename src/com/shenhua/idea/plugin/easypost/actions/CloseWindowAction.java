package com.shenhua.idea.plugin.easypost.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindowManager;
import com.shenhua.idea.plugin.easypost.etc.Constant;

/**
 * Created by shenhua on 2018/9/15.
 * Email shenhuanet@126.com
 *
 * @author shenhua
 */
public class CloseWindowAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        Project project = anActionEvent.getProject();
        if (project != null) {
            ToolWindowManager.getInstance(project).getToolWindow(Constant.TOOL_WINDOW_ID)
                    .getContentManager().removeAllContents(true);
        }
    }
}
