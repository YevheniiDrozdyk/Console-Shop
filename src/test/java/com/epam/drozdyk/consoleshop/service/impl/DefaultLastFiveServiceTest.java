package com.epam.drozdyk.consoleshop.service.impl;

import com.epam.drozdyk.consoleshop.constant.GuitarType;
import com.epam.drozdyk.consoleshop.constant.ViolinCategory;
import com.epam.drozdyk.consoleshop.model.Guitar;
import com.epam.drozdyk.consoleshop.model.Instrument;
import com.epam.drozdyk.consoleshop.model.Violin;
import com.epam.drozdyk.consoleshop.repository.LastFiveRepository;
import com.epam.drozdyk.consoleshop.wrapper.LastFive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultLastFiveServiceTest {
    private static final String PRODUCER_UA = "ua";
    private static final String PRODUCER_RU = "ru";

    @InjectMocks
    private DefaultLastFiveService lastFiveService;
    @Mock
    private LastFiveRepository lastFiveRepository;
    private LastFive lastFive;

    @Before
    public void setUp() {
        Instrument guitar1 = new Guitar(1, "F-100", PRODUCER_UA, 100, 3, GuitarType.ACOUSTIC);
        Instrument violin1 = new Violin(2, "F-200", PRODUCER_RU, 200, 4, ViolinCategory.ARTISANS);
        Instrument guitar2 = new Guitar(3, "F-300", PRODUCER_UA, 300, 5, GuitarType.ACOUSTIC);
        Instrument violin2 = new Violin(4, "F-400", PRODUCER_RU, 400, 6, ViolinCategory.ARTISANS);
        Instrument guitar3 = new Guitar(5, "F-500", PRODUCER_UA, 500, 7, GuitarType.ACOUSTIC);

        lastFive = new LastFive();
        lastFive.getLastFiveMap().put("F-100", guitar1);
        lastFive.getLastFiveMap().put("F-200", violin1);
        lastFive.getLastFiveMap().put("F-300", guitar2);
        lastFive.getLastFiveMap().put("F-400", violin2);
        lastFive.getLastFiveMap().put("F-500", guitar3);
    }

    @Test
    public void testGetLastFive() {
        when(lastFiveRepository.getLastFive()).thenReturn(lastFive);
        final LastFive actual = lastFiveService.getLastFive();

        assertEquals(lastFive, actual);
    }

    @Test
    public void testPutInstrumentToLastFive() {
        final String vendorCode = "F-600";
        Instrument violin3 = new Violin(6, vendorCode, PRODUCER_RU, 1000, 6, ViolinCategory.ARTISANS);
        lastFive.put(vendorCode, violin3);
        lastFiveService.putInstrumentToLastFive(vendorCode, violin3);

        verify(lastFiveRepository).putInstrumentToLastFive(vendorCode, violin3);

        when(lastFiveRepository.getLastFive()).thenReturn(lastFive);
        final LastFive actual = lastFiveService.getLastFive();

        assertEquals(lastFive, actual);
    }
}
