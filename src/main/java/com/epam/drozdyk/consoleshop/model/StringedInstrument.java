package com.epam.drozdyk.consoleshop.model;

import com.epam.drozdyk.consoleshop.annotation.InstrumentField;
import com.epam.drozdyk.consoleshop.constant.FieldName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Entity of stringed instrument.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 13 Mar 2017
 */
public class StringedInstrument extends Instrument {

    @InstrumentField(name = FieldName.STRING_COUNT_FIELD)
    private int stringCount;

    public StringedInstrument() {

    }

    public StringedInstrument(long id, String vendorCode, String producer, int price, int stringCount) {
        super(id, vendorCode, producer, price);
        this.stringCount = stringCount;
    }

    public int getStringCount() {
        return stringCount;
    }

    public void setStringCount(int stringCount) {
        this.stringCount = stringCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        StringedInstrument that = (StringedInstrument) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(stringCount, that.stringCount)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(stringCount)
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
                .toString();
    }
}
