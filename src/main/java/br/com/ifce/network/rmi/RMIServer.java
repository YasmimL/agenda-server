package br.com.ifce.network.rmi;

import br.com.ifce.repository.AgendaRepository;

import java.rmi.Naming;

public class RMIServer {

    private static final RMIServer INSTANCE = new RMIServer();

    public static RMIServer getInstance() {
        return INSTANCE;
    }

    private RMIServer() {
    }

    public void start() {
        try {
            final var agenda = AgendaRepository.getInstance().getAgenda().getName();
            Naming.rebind(agenda + "/" + AgendaService.name(), new AgendaServiceImpl());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
