package com.epam.drozdyk.consoleshop.command.impl.server;

import com.epam.drozdyk.consoleshop.constant.GuitarType;
import com.epam.drozdyk.consoleshop.constant.Message;
import com.epam.drozdyk.consoleshop.model.Guitar;
import com.epam.drozdyk.consoleshop.model.Instrument;
import com.epam.drozdyk.consoleshop.service.InstrumentService;
import com.epam.drozdyk.consoleshop.view.ErrorView;
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
public class GetItemInfoCommandTest {
    @InjectMocks
    private GetItemInfoCommand command;
    @Mock
    private InstrumentService instrumentService;

    @Test
    public void execute() {
        final String vendorCode = "F-100";
        final Instrument guitar = new Guitar(1, vendorCode, "ua", 1000, 6, GuitarType.ACOUSTIC);
        final View expected = new ServerCommandView(
                new StringBuilder("producer:")
                        .append(guitar.getProducer())
                        .append(", price:")
                        .append(guitar.getPrice())
                        .toString());
        when(instrumentService.isInstrumentExist(vendorCode)).thenReturn(true);
        when(instrumentService.getInstrument(vendorCode)).thenReturn(guitar);
        final View actual = command.execute(vendorCode);

        assertEquals(expected, actual);
    }

    @Test
    public void executeWithoutParameters() {
        final View expected = new ErrorView(Message.ERROR_ILLEGAL_ARGS);
        final View actual = command.execute();

        assertEquals(expected, actual);
    }

    @Test
    public void executeWithNonexistentItem() {
        final String vendorCode = "F-300";
        when(instrumentService.isInstrumentExist(vendorCode)).thenReturn(false);
        final View expected = new ErrorView(Message.ERROR_NO_SUCH_INSTRUMENT);
        final View actual = command.execute(vendorCode);

        assertEquals(expected, actual);
    }

}