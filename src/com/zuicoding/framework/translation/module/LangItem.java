package com.zuicoding.framework.translation.module;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by <a href="mailto:stephen.linicoding@gmail.com">Stephen.lin</a> on 2018/1/18.
 * <p>
 * <p>
 * </p>
 */
public class LangItem implements Serializable {

    private static final long serialVersionUID = -6546361867456757258L;
    private String code;
    private String name;
    private boolean selected;
    public LangItem() {
    }

    public LangItem(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LangItem item = (LangItem) o;
        return Objects.equals(code, item.code) &&
                Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {

        return code.hashCode();
    }
}
