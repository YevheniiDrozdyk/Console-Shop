package com.epam.drozdyk.consoleshop.builder.impl.reflect;

import com.epam.drozdyk.consoleshop.constant.FieldName;
import com.epam.drozdyk.consoleshop.constant.GuitarType;
import com.epam.drozdyk.consoleshop.generator.InstrumentGenerator;
import com.epam.drozdyk.consoleshop.model.Guitar;
import com.epam.drozdyk.consoleshop.model.Instrument;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReflectInstrumentBuilderTest {
    private static final int TEST_INT = 1000;
    private static final String TEST_STRING = "TestString";

    @InjectMocks
    private ReflectInstrumentBuilder builder;
    @Mock
    private InstrumentGenerator generator;

    private Guitar expected;

    @Before
    public void setUp() {
        builder = new ReflectInstrumentBuilder(generator, Guitar.class);
        expected = new Guitar(TEST_INT, TEST_STRING, TEST_STRING, TEST_INT, TEST_INT, GuitarType.ACOUSTIC);
    }

    @Test
    public void testBuildInstrumentThroughReflection() {
        when(generator.generateInt(FieldName.ID_FIELD)).thenReturn(TEST_INT);
        when(generator.generateInt(FieldName.PRICE_FIELD)).thenReturn(TEST_INT);
        when(generator.generateInt(FieldName.STRING_COUNT_FIELD)).thenReturn(TEST_INT);
        when(generator.generateString(FieldName.VENDOR_CODE_FIELD)).thenReturn(TEST_STRING);
        when(generator.generateString(FieldName.PRODUCER_FIELD)).thenReturn(TEST_STRING);
        when(generator.generateGuitarType(FieldName.GUITAR_TYPE_FIELD)).thenReturn(GuitarType.ACOUSTIC);

        final Instrument actual = builder.buildInstrument();

        assertEquals(expected, actual);
    }
}
