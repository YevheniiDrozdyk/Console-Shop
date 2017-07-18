package com.epam.drozdyk.consoleshop.context;

import com.epam.drozdyk.consoleshop.builder.BuilderContainer;
import com.epam.drozdyk.consoleshop.command.CommandController;
import com.epam.drozdyk.consoleshop.repository.CartRepository;
import com.epam.drozdyk.consoleshop.repository.InstrumentRepository;
import com.epam.drozdyk.consoleshop.repository.LastFiveRepository;
import com.epam.drozdyk.consoleshop.repository.OrderRepository;
import com.epam.drozdyk.consoleshop.repository.impl.DefaultCartRepository;
import com.epam.drozdyk.consoleshop.repository.impl.DefaultInstrumentRepository;
import com.epam.drozdyk.consoleshop.repository.impl.DefaultLastFiveRepository;
import com.epam.drozdyk.consoleshop.repository.impl.DefaultOrderRepository;
import com.epam.drozdyk.consoleshop.service.CartService;
import com.epam.drozdyk.consoleshop.service.InstrumentService;
import com.epam.drozdyk.consoleshop.service.LastFiveService;
import com.epam.drozdyk.consoleshop.service.OrderService;
import com.epam.drozdyk.consoleshop.service.impl.DefaultCartService;
import com.epam.drozdyk.consoleshop.service.impl.DefaultInstrumentService;
import com.epam.drozdyk.consoleshop.service.impl.DefaultLastFiveService;
import com.epam.drozdyk.consoleshop.service.impl.DefaultOrderService;
import com.epam.drozdyk.consoleshop.util.io.impl.FileInstrumentSerializer;
import com.epam.drozdyk.consoleshop.util.localization.Localization;

/**
 * Contains dependencies of the application.
 *
 * @author Yevhenii Drozdyk
 * @version 2.0 4 Apr 2017
 */
public class Context {
    private final static String SERIALIZATION_FILE = "instruments.txt";

    private static Context context;

    private final FileInstrumentSerializer fileInstrumentSerializer;

    private final InstrumentService instrumentService;
    private final CartService cartService;
    private final OrderService orderService;
    private final LastFiveService lastFiveService;

    private BuilderContainer builderContainer;

    private Localization localization;

    private CommandController serverController;

    private Context() {
        fileInstrumentSerializer = new FileInstrumentSerializer(SERIALIZATION_FILE);

        InstrumentRepository instrumentRepository = new DefaultInstrumentRepository(fileInstrumentSerializer);
        CartRepository cartRepository = new DefaultCartRepository();
        OrderRepository orderRepository = new DefaultOrderRepository();
        LastFiveRepository lastFiveRepository = new DefaultLastFiveRepository();

        instrumentService = new DefaultInstrumentService(instrumentRepository);
        cartService = new DefaultCartService(cartRepository);
        orderService = new DefaultOrderService(orderRepository);
        lastFiveService = new DefaultLastFiveService(lastFiveRepository);
    }

    public static Context getInstance() {
        if (context == null) {
            context = new Context();
        }

        return context;
    }

    public FileInstrumentSerializer getFileInstrumentSerializer() {
        return fileInstrumentSerializer;
    }

    public InstrumentService getInstrumentService() {
        return instrumentService;
    }

    public CartService getCartService() {
        return cartService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public LastFiveService getLastFiveService() {
        return lastFiveService;
    }

    public BuilderContainer getBuilderContainer() {
        return builderContainer;
    }

    public void setBuilderContainer(BuilderContainer builderContainer) {
        this.builderContainer = builderContainer;
    }

    public Localization getLocalization() {
        return localization;
    }

    public void setLocalization(Localization localization) {
        this.localization = localization;
    }

    public CommandController getServerController() {
        return serverController;
    }

    public void setServerController(CommandController serverController) {
        this.serverController = serverController;
    }
}
