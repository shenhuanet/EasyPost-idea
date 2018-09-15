package com.shenhua.idea.plugin.easypost.ui;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.project.Project;
import com.intellij.ui.JBColor;
import com.intellij.ui.JBSplitter;
import com.intellij.ui.components.JBPanel;

import javax.swing.*;

/**
 * Created by shenhua on 2018/9/15.
 * Email shenhuanet@126.com
 *
 * @author shenhua
 */
public class ContentPanel extends JBSplitter {

    private JBSplitter mContentSplitter;
    private Project mProject;
    private Disposable mDisposable;
    private JComponent mHistoryComponent;

    public ContentPanel(Project mProject, Disposable mDisposable) {
        this.mProject = mProject;
        this.mDisposable = mDisposable;

        mContentSplitter = new JBSplitter();
        mContentSplitter.setProportion(0.7f);
        mContentSplitter.setFirstComponent(createFirstComponent());
        mContentSplitter.setSecondComponent(createSecondComponent());

        this.setOrientation(true);
        this.setProportion(0.6f);
        this.setFirstComponent(mContentSplitter);
        this.setSecondComponent(createHistoryComponent());
    }

    private JComponent createFirstComponent() {
        JBPanel requestPanel = new JBPanel();
        return requestPanel;
    }

    private JComponent createSecondComponent() {
        JBPanel responsePanel = new JBPanel();
        responsePanel.setBackground(JBColor.WHITE);
        return responsePanel;
    }

    private JComponent createHistoryComponent() {
        mHistoryComponent = new JBPanel();
        mHistoryComponent.setVisible(false);
        mHistoryComponent.setBackground(JBColor.WHITE);
        return mHistoryComponent;
    }

    public JComponent getHistoryComponent() {
        return mHistoryComponent;
    }
}
