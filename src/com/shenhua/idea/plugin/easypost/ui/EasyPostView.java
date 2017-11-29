package com.shenhua.idea.plugin.easypost.ui;

import com.intellij.openapi.actionSystem.ActionToolbar;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.shenhua.idea.plugin.easypost.components.EasyPostComponent;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * Created by shenhua on 2017/11/28.
 * Email shenhuanet@126.com
 *
 * @author shenhua
 */
public class EasyPostView implements ToolWindowFactory {

    private JPanel mPanel;

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {

        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();

        SimpleToolWindowPanel panel = new SimpleToolWindowPanel(false, true);
        Content content = contentFactory.createContent(panel, "", false);

        EasyPostComponent component = EasyPostComponent.get(project);
        ActionToolbar toolbar = component.createToolbar();
        panel.setToolbar(toolbar.getComponent());
        panel.setContent(content.getComponent());
        content.setCloseable(true);
        toolWindow.getContentManager().addContent(content);
    }
}
