package com.shenhua.idea.plugin.easypost.ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.shenhua.idea.plugin.easypost.EasyPostComponent;
import org.jetbrains.annotations.NotNull;


/**
 * Created by shenhua on 2017/11/28.
 * Email shenhuanet@126.com
 *
 * @author shenhua
 */
public class EasyPostView implements ToolWindowFactory {

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        EasyPostComponent easyPostComponent = EasyPostComponent.getInstance(project);
        easyPostComponent.init(toolWindow);
    }
}
