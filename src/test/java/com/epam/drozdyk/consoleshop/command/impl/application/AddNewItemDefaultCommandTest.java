package com.epam.drozdyk.consoleshop.command.impl.application;

import com.epam.drozdyk.consoleshop.builder.BuilderContainer;
import com.epam.drozdyk.consoleshop.builder.InstrumentBuilder;
import com.epam.drozdyk.consoleshop.builder.impl.reflect.ReflectInstrumentBuilder;
import com.epam.drozdyk.consoleshop.constant.GuitarType;
import com.epam.drozdyk.consoleshop.constant.ViolinCategory;
import com.epam.drozdyk.consoleshop.exception.IllegalUserInputException;
import com.epam.drozdyk.consoleshop.generator.impl.AutomaticInstrumentGenerator;
import com.epam.drozdyk.consoleshop.model.Guitar;
import com.epam.drozdyk.consoleshop.model.Instrument;
import com.epam.drozdyk.consoleshop.model.Violin;
import com.epam.drozdyk.consoleshop.service.InstrumentService;
import com.epam.drozdyk.consoleshop.view.AvailableInstrumentsView;
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
public class AddNewItemDefaultCommandTest {
    private static final String BUILDER_KEY = "Guitar";
    private static final String GUITAR_VENDOR_CODE = "F-300";

    @InjectMocks
    private AddNewItemDefaultCommand command;
    @Mock
    private BuilderContainer builderContainer;
    @Mock
    private InstrumentService instrumentService;
    private InstrumentBuilder builder;
    private Instrument guitar;
    private HashMap<String, Instrument> instruments;

    @Before
    public void setUp() {
        builder = new ReflectInstrumentBuilder(new AutomaticInstrumentGenerator(), Guitar.class);
        guitar = new Guitar(1, GUITAR_VENDOR_CODE, "german", 1000, 5, GuitarType.ACOUSTIC);
        instruments = new LinkedHashMap<>();
        instruments.put(GUITAR_VENDOR_CODE, guitar);
    }

    @Test
    public void executeWithRealInstrument() {
        when(builderContainer.getBuilder(BUILDER_KEY)).thenReturn(builder);

        final String vendorCode = "F-200";
        final Instrument violin = new Violin(2, vendorCode, "usa", 2000, 6, ViolinCategory.ARTISANS);
        instruments.put(vendorCode, violin);
        when(instrumentService.getInstruments()).thenReturn(instruments);

        final View expected = new AvailableInstrumentsView(instruments);
        final View actual = command.execute(BUILDER_KEY);

        assertEquals(expected, actual);
    }

    @Test(expected = IllegalUserInputException.class)
    public void executeWithoutArguments() {
        command.execute();
    }
}