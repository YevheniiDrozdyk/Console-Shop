package com.epam.drozdyk.consoleshop.model;

import com.epam.drozdyk.consoleshop.annotation.InstrumentField;
import com.epam.drozdyk.consoleshop.constant.FieldName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * Abstract wrapper of instrument.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 13 Mar 2017
 */
public abstract class Instrument implements Serializable {

    @InstrumentField(name = FieldName.ID_FIELD)
    private long id;
    @InstrumentField(name = FieldName.VENDOR_CODE_FIELD)
    private String vendorCode;
    @InstrumentField(name = FieldName.PRODUCER_FIELD)
    private String producer;
    @InstrumentField(name = FieldName.PRICE_FIELD)
    private int price;

    public Instrument() {

    }

    public Instrument(long id, String vendorCode, String producer, int price) {
        this.id = id;
        this.vendorCode = vendorCode;
        this.price = price;
        this.producer = producer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Instrument that = (Instrument) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(price, that.price)
                .append(vendorCode, that.vendorCode)
                .append(producer, that.producer)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(vendorCode)
                .append(producer)
                .append(price)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("vendorCode", vendorCode)
                .append("producer", producer)
                .append("price", price)
                .toString();
    }
}
