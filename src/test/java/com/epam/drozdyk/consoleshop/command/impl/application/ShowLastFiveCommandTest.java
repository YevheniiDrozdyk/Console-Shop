package com.epam.drozdyk.consoleshop.command.impl.application;

import com.epam.drozdyk.consoleshop.constant.GuitarType;
import com.epam.drozdyk.consoleshop.constant.ViolinCategory;
import com.epam.drozdyk.consoleshop.model.Guitar;
import com.epam.drozdyk.consoleshop.model.Instrument;
import com.epam.drozdyk.consoleshop.model.Violin;
import com.epam.drozdyk.consoleshop.service.LastFiveService;
import com.epam.drozdyk.consoleshop.view.LastFiveView;
import com.epam.drozdyk.consoleshop.view.View;
import com.epam.drozdyk.consoleshop.wrapper.LastFive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShowLastFiveCommandTest {
    @InjectMocks
    private ShowLastFiveCommand command;
    @Mock
    private LastFiveService lastFiveService;
    private LastFive lastFive;

    @Before
    public void setUp() {
        Instrument guitar1 = new Guitar(1, "F-100", "ua", 100, 3, GuitarType.ACOUSTIC);
        Instrument violin1 = new Violin(2, "F-200", "ru", 200, 4, ViolinCategory.ARTISANS);
        Instrument guitar2 = new Guitar(3, "F-300", "usa", 300, 5, GuitarType.ACOUSTIC);
        Instrument violin2 = new Violin(4, "F-400", "de", 400, 6, ViolinCategory.ARTISANS);
        Instrument guitar3 = new Guitar(5, "F-500", "fr", 500, 7, GuitarType.ACOUSTIC);

        lastFive = new LastFive();
        lastFive.getLastFiveMap().put("F-100", guitar1);
        lastFive.getLastFiveMap().put("F-200", violin1);
        lastFive.getLastFiveMap().put("F-300", guitar2);
        lastFive.getLastFiveMap().put("F-400", violin2);
        lastFive.getLastFiveMap().put("F-500", guitar3);
    }

    @Test
    public void execute() {
        when(lastFiveService.getLastFive()).thenReturn(lastFive);
        final View expected = new LastFiveView(lastFive);
        final View actual = command.execute();

        assertEquals(expected, actual);
    }
}