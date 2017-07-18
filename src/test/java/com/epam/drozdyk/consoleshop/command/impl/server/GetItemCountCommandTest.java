package com.epam.drozdyk.consoleshop.command.impl.server;

import com.epam.drozdyk.consoleshop.service.InstrumentService;
import com.epam.drozdyk.consoleshop.view.ServerCommandView;
import com.epam.drozdyk.consoleshop.view.View;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetItemCountCommandTest {
    @InjectMocks
    private GetItemCountCommand command;
    @Mock
    private InstrumentService instrumentService;

    @Test
    public void execute() {
        final int count = 10;
        when(instrumentService.getInstrumentCount()).thenReturn(count);
        final View expected = new ServerCommandView(new StringBuilder("count:").append(count).toString());
        final View actual = command.execute("");

        assertEquals(expected, actual);
    }
}