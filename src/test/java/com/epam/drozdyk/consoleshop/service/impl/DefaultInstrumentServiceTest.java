package com.epam.drozdyk.consoleshop.service.impl;

import com.epam.drozdyk.consoleshop.constant.GuitarType;
import com.epam.drozdyk.consoleshop.constant.ViolinCategory;
import com.epam.drozdyk.consoleshop.model.Guitar;
import com.epam.drozdyk.consoleshop.model.Instrument;
import com.epam.drozdyk.consoleshop.model.Violin;
import com.epam.drozdyk.consoleshop.repository.InstrumentRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultInstrumentServiceTest {
    private static final String GUITAR_VENDOR_CODE = "F-100";
    private static final String VIOLIN_VENDOR_CODE = "F-200";

    @InjectMocks
    private DefaultInstrumentService instrumentService;
    @Mock
    private InstrumentRepository instrumentRepository;
    private HashMap<String, Instrument> instruments;
    private Instrument guitar;
    private Instrument violin;

    @Before
    public void setUp() {
        guitar = new Guitar(1, GUITAR_VENDOR_CODE, "german", 1000, 5, GuitarType.ACOUSTIC);
        violin = new Violin(2, VIOLIN_VENDOR_CODE, "usa", 2000, 6, ViolinCategory.ARTISANS);
        instruments = new HashMap<>();
        instruments.put(GUITAR_VENDOR_CODE, guitar);
        instruments.put(VIOLIN_VENDOR_CODE, violin);
    }

    @Test
    public void testGetInstrumentByExistentVendorCode() {
        when(instrumentRepository.getInstrument(VIOLIN_VENDOR_CODE)).thenReturn(violin);
        final Instrument actual = instrumentService.getInstrument(VIOLIN_VENDOR_CODE);

        assertEquals(violin, actual);
    }

    @Test
    public void testGetInstrumentByNonexistentVendorCode() {
        when(instrumentRepository.getInstrument(GUITAR_VENDOR_CODE)).thenReturn(null);
        final Instrument actual = instrumentService.getInstrument(GUITAR_VENDOR_CODE);

        assertEquals(null, actual);
    }

    @Test
    public void testGetInstruments() {
        when(instrumentRepository.getInstruments()).thenReturn(instruments);
        final HashMap<String, Instrument> actual = instrumentService.getInstruments();

        assertEquals(instruments, actual);
    }

    @Test
    public void testPutNewInstrument() {
        final String vendorCode = "F-300";
        Guitar guitar = new Guitar(3, vendorCode, "ukr", 3000, 7, GuitarType.ACOUSTIC);
        instrumentService.putInstrument(vendorCode, guitar);

        verify(instrumentRepository).putInstrument(vendorCode, guitar);
    }

    @Test
    public void testGetInstrumentCount() {
        final int expected = 10;
        when(instrumentRepository.getInstrumentCount()).thenReturn(expected);
        final int actual = instrumentService.getInstrumentCount();

        assertEquals(expected, actual);
    }

    @Test
    public void testIsInstrumentExistWhenPassNonexistentVendorCode() {
        when(instrumentRepository.getInstrument(GUITAR_VENDOR_CODE)).thenReturn(null);
        boolean condition = instrumentService.isInstrumentExist(GUITAR_VENDOR_CODE);

        assertFalse(condition);
    }

    @Test
    public void testIsInstrumentExistWhenPassExistentVendorCode() {
        when(instrumentRepository.getInstrument(GUITAR_VENDOR_CODE)).thenReturn(guitar);
        boolean condition = instrumentService.isInstrumentExist(GUITAR_VENDOR_CODE);

        assertTrue(condition);
    }
}
