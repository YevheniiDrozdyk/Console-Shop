package com.epam.drozdyk.consoleshop.view;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * An abstract view that displays after command execution.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 30 Mar 2017
 */
public abstract class View {
    private String message;

    protected View() {

    }

    protected View(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        View view = (View) o;

        return new EqualsBuilder()
                .append(message, view.message)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(message)
                .toHashCode();
    }
}
