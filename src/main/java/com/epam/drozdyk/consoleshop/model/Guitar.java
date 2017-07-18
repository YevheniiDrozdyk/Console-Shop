package com.epam.drozdyk.consoleshop.model;

import com.epam.drozdyk.consoleshop.annotation.InstrumentField;
import com.epam.drozdyk.consoleshop.constant.FieldName;
import com.epam.drozdyk.consoleshop.constant.GuitarType;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Entity of guitar.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 13 Mar 2017
 */
public class Guitar extends StringedInstrument {

    @InstrumentField(name = FieldName.GUITAR_TYPE_FIELD)
    private GuitarType type;

    public Guitar() {

    }

    public Guitar(long id, String vendorCode, String producer, int price, int stringCount, GuitarType type) {
        super(id, vendorCode, producer, price, stringCount);
        this.type = type;
    }

    public GuitarType getType() {
        return type;
    }

    public void setType(GuitarType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Guitar guitar = (Guitar) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(type, guitar.type)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(type)
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
                .append("type", type)
                .toString();
    }
}
