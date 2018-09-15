package com.shenhua.idea.plugin.easypost.ui;

import com.beust.jcommander.internal.Sets;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.project.Project;
import com.intellij.ui.components.JBPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

/**
 * 支持tab的content panel
 * Created by shenhua on 2018/9/15.
 * Email shenhuanet@126.com
 *
 * @author shenhua
 */
public class TabbedContentPanel extends JBPanel implements ITabbedWidget {

    private Project mProject;
    private Disposable mDisposable;
    private JComponent mJComponent;
    private JComponent mHistoryComponent;
    private ITabs mTabs;

    public TabbedContentPanel(Project mProject, Disposable mDisposable) {
        super(new BorderLayout());
        this.mProject = mProject;
        this.mDisposable = mDisposable;
    }

    public void toggleHistory() {
        if (mHistoryComponent != null) {
            mHistoryComponent.setVisible(!mHistoryComponent.isVisible());
        }
    }

    @Override
    public void createNewTab() {
        ContentPanel contentPanel = new ContentPanel(mProject, mDisposable);
        mHistoryComponent = contentPanel.getHistoryComponent();
        if (mJComponent == null) {
            mJComponent = contentPanel;
            this.add(mJComponent, BorderLayout.CENTER);
        } else {
            if (mTabs == null) {
                mTabs = setupTabs();
            }
            addTab(contentPanel, mTabs);
        }
    }

    private ITabs setupTabs() {
        ITabsImpl tabs = new ITabsImpl(mProject, mDisposable);
        tabs.setOnTabCloseListener(() -> {
            mJComponent = mTabs.getCurrentComponent();
            if (mJComponent == null) {
                return;
            }
            remove(mTabs.getComponent());
            mTabs = null;
            add(mJComponent, BorderLayout.CENTER);
        });
        this.remove(mJComponent);
        addTab(mJComponent, tabs);
        add(tabs.getComponent(), BorderLayout.CENTER);
        return tabs;
    }

    private void addTab(JComponent jComponent, ITabs tabs) {
        tabs.addTab(jComponent, generateUniqueName("Tab", tabs));
    }

    @Override
    public void closeCurrentTab() {
        mTabs.closeCurrentTab();
    }

    @Override
    public JComponent getComponent() {
        return mJComponent;
    }

    @Override
    public ITabs getTabs() {
        return mTabs;
    }

    private static String generateUniqueName(String suggestedName, ITabs tabs) {
        Set<String> names = Sets.newHashSet();
        for (int i = 0; i < tabs.getTabCount(); i++) {
            names.add(tabs.getTitleAt(i));
        }
        String newSdkName = suggestedName;
        int i = 0;
        while (names.contains(newSdkName)) {
            newSdkName = suggestedName + " (" + (++i) + ")";
        }
        return newSdkName;
    }
}
