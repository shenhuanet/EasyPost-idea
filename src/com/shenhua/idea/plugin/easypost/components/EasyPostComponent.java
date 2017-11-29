package com.shenhua.idea.plugin.easypost.components;

import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionToolbar;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.components.AbstractProjectComponent;
import com.intellij.openapi.project.Project;

import javax.swing.*;

/**
 * Created by shenhua on 2017/11/28.
 * Email shenhuanet@126.com
 *
 * @author shenhua
 */
public class EasyPostComponent extends AbstractProjectComponent {

    private EasyPostComponent(Project project) {
        super(project);
    }

    public static EasyPostComponent get(Project project) {
        return project.getComponent(EasyPostComponent.class);
    }

    public ActionToolbar createToolbar() {
        DefaultActionGroup leftGroup = (DefaultActionGroup) ActionManager.getInstance().getAction("EasyPost.LeftToolbarGroup");
        ActionToolbar leftToolbar = ActionManager.getInstance().createActionToolbar("LeftToolbar", leftGroup, false);
        leftToolbar.setOrientation(SwingConstants.VERTICAL);
        System.out.println("lefttoolbar: " + leftToolbar.toString());
        return leftToolbar;
    }

//    public
}
