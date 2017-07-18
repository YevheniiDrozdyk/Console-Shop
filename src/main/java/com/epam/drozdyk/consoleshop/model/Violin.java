package com.epam.drozdyk.consoleshop.model;

import com.epam.drozdyk.consoleshop.annotation.InstrumentField;
import com.epam.drozdyk.consoleshop.constant.FieldName;
import com.epam.drozdyk.consoleshop.constant.ViolinCategory;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Entity of violin.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 15 Mar 2017
 */
public class Violin extends StringedInstrument {

    @InstrumentField(name = FieldName.VIOLIN_CATEGORY_FIELD)
    private ViolinCategory category;

    public Violin() {

    }

    public Violin(long id, String vendorCode, String producer, int price, int stringCount, ViolinCategory category) {
        super(id, vendorCode, producer, price, stringCount);
        this.category = category;
    }

    public ViolinCategory getCategory() {
        return category;
    }

    public void setCategory(ViolinCategory category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Violin violin = (Violin) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(category, violin.category)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(category)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", getId())
                .append("vendor code", getVendorCode())
                .append("producer", getProducer())
                .append("price", getPrice())
                .append("string count", getStringCount())
                .append("category", category)
                .toString();
    }
}
