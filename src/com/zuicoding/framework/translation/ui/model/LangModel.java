package com.zuicoding.framework.translation.ui.model;

import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

import com.zuicoding.framework.translation.module.LangItem;

/**
 * Created by <a href="mailto:stephen.linicoding@gmail.com">Stephen.lin</a> on 2018/1/18.
 * <p>
 * <p>
 * </p>
 */
public class LangModel implements ComboBoxModel<LangItem> {

    private List<LangItem> data;
    private LangItem item;
    public LangModel(List<LangItem> data) {
        if (data == null){
            throw new IllegalArgumentException("data == null");
        }
        this.data = data;

    }

    @Override
    public Object getSelectedItem() {
        return item;
    }

    @Override
    public int getSize() {
        return data.size();
    }

    @Override
    public LangItem getElementAt(int index) {
        return data.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {

    }

    @Override
    public void removeListDataListener(ListDataListener l) {

    }

    @Override
    public void setSelectedItem(Object item) {
        this.item = (LangItem)item;

    }



}
