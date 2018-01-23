package com.zuicoding.framework.translation.ui;

import java.awt.Component;

import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import com.zuicoding.framework.translation.module.LangItem;

/**
 * Created by <a href="mailto:stephen.linicoding@gmail.com">Stephen.lin</a> on 2018/1/19.
 * <p>
 * <p>
 * </p>
 */
public class LangComboBoxRenderer extends BasicComboBoxRenderer {

    @Override
    public Component getListCellRendererComponent(JList list, Object value,
                                                  int index,
                                                  boolean isSelected,
                                                  boolean cellHasFocus) {
        if (value != null){
            LangItem item = (LangItem) value;
            setText(item.getName());
        }

        return this;
    }
}
