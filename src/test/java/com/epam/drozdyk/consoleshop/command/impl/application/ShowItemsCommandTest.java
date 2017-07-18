package com.epam.drozdyk.consoleshop.command.impl.application;

import com.epam.drozdyk.consoleshop.constant.GuitarType;
import com.epam.drozdyk.consoleshop.constant.Message;
import com.epam.drozdyk.consoleshop.constant.ViolinCategory;
import com.epam.drozdyk.consoleshop.model.Guitar;
import com.epam.drozdyk.consoleshop.model.Instrument;
import com.epam.drozdyk.consoleshop.model.Violin;
import com.epam.drozdyk.consoleshop.service.InstrumentService;
import com.epam.drozdyk.consoleshop.view.AvailableInstrumentsView;
import com.epam.drozdyk.consoleshop.view.ErrorView;
import com.epam.drozdyk.consoleshop.view.View;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.LinkedHashMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShowItemsCommandTest {
    private static final String GUITAR_VENDOR_CODE = "V-100";
    private static final String VIOLIN_VENDOR_CODE = "V-200";

    @InjectMocks
    private ShowItemsCommand command;
    @Mock
    private InstrumentService instrumentService;
    private HashMap<String, Instrument> instruments;

    @Before
    public void setUp() {
        Instrument guitar = new Guitar(1, GUITAR_VENDOR_CODE, "german", 1000, 5, GuitarType.ACOUSTIC);
        Instrument violin = new Violin(2, VIOLIN_VENDOR_CODE, "usa", 2000, 6, ViolinCategory.ARTISANS);
        instruments = new LinkedHashMap<>();
        instruments.put(GUITAR_VENDOR_CODE, guitar);
        instruments.put(VIOLIN_VENDOR_CODE, violin);
    }

    @Test
    public void execute() {
        when(instrumentService.getInstruments()).thenReturn(instruments);
        final View expected = new AvailableInstrumentsView(instruments);
        final View actual = command.execute();

        assertEquals(expected, actual);
    }

    @Test
    public void executeWithEmptyInstrumentSource() {
        when(instrumentService.getInstruments()).thenReturn(new LinkedHashMap<>());
        final View expected = new ErrorView(Message.ERROR_MESSAGE_NO_AVAILABLE_INSTRUMENTS);
        final View actual = command.execute();

        assertEquals(expected, actual);
    }
}