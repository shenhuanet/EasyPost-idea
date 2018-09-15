package com.shenhua.idea.plugin.easypost;

import com.intellij.execution.ui.RunnerLayoutUi;
import com.intellij.execution.ui.layout.PlaceInGrid;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionToolbar;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.components.AbstractProjectComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.openapi.wm.ex.ToolWindowManagerEx;
import com.intellij.openapi.wm.ex.ToolWindowManagerListener;
import com.intellij.ui.components.JBLoadingPanel;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.shenhua.idea.plugin.easypost.actions.AddTabAction;
import com.shenhua.idea.plugin.easypost.actions.CloseTabAction;
import com.shenhua.idea.plugin.easypost.actions.HistoryAction;
import com.shenhua.idea.plugin.easypost.etc.Constant;
import com.shenhua.idea.plugin.easypost.ui.TabbedContentPanel;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

/**
 * Created by shenhua on 2018/9/15.
 * Email shenhuanet@126.com
 *
 * @author shenhua
 */
public class EasyPostComponent extends AbstractProjectComponent implements Disposable {

    public static EasyPostComponent getInstance(@NotNull Project project) {
        return project.getComponent(EasyPostComponent.class);
    }

    protected EasyPostComponent(Project project) {
        super(project);
    }

    public void init(ToolWindow toolWindow) {
        toolWindow.setToHideOnEmptyContent(true);
        createContent(toolWindow);
        ((ToolWindowManagerEx) ToolWindowManager.getInstance(myProject))
                .addToolWindowManagerListener(toolWindowManagerListener);
    }

    private void createContent(ToolWindow toolWindow) {
        RunnerLayoutUi layoutUi = RunnerLayoutUi.Factory.getInstance(myProject)
                .create("", "", "", this);
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();

        JBLoadingPanel loadingPanel = new JBLoadingPanel(new BorderLayout(), myProject);
        loadingPanel.add(layoutUi.getComponent(), BorderLayout.CENTER);
        loadingPanel.setLoadingText("Initializing");
        loadingPanel.startLoading();

        Content loadingContent = contentFactory.createContent(loadingPanel, "", true);
        toolWindow.getContentManager().addContent(loadingContent);

        Content content = layoutUi.createContent("", createToolWindowPanel(), "", null, null);
        layoutUi.addContent(content, 0, PlaceInGrid.center, false);

        loadingPanel.stopLoading();
    }

    private JPanel createToolWindowPanel() {
        SimpleToolWindowPanel panel = new SimpleToolWindowPanel(false);
        TabbedContentPanel tabbedContentPanel = new TabbedContentPanel(myProject, this);
        tabbedContentPanel.createNewTab();
        panel.setContent(tabbedContentPanel);
        panel.setToolbar(createToolbar(tabbedContentPanel).getComponent());
        return panel;
    }

    private ActionToolbar createToolbar(TabbedContentPanel tabbedContentPanel) {
        DefaultActionGroup leftGroup = (DefaultActionGroup) ActionManager.getInstance().getAction("EasyPost.LeftToolbarGroup");
        ActionToolbar toolbar = ActionManager.getInstance().createActionToolbar("toolbar", leftGroup, false);
        ((AddTabAction) toolbar.getActions().get(0)).setTabbedContentPanel(tabbedContentPanel);
        ((CloseTabAction) toolbar.getActions().get(1)).setTabbedContentPanel(tabbedContentPanel);
        ((HistoryAction) toolbar.getActions().get(2)).setTabbedContentPanel(tabbedContentPanel);
        toolbar.setOrientation(SwingConstants.VERTICAL);
        return toolbar;
    }


    private ToolWindowManagerListener toolWindowManagerListener = new ToolWindowManagerListener() {
        @Override
        public void toolWindowRegistered(@NotNull String s) {
        }

        @Override
        public void stateChanged() {
            ToolWindow toolWindow = ToolWindowManager.getInstance(myProject).getToolWindow(Constant.TOOL_WINDOW_ID);
            if (toolWindow != null) {
                if (toolWindow.isVisible() && toolWindow.getContentManager().getContentCount() == 0) {
                    init(toolWindow);
                }
            }
        }
    };

    @Override
    public void dispose() {

    }
}
