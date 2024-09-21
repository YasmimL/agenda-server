package br.com.ifce.network.rmi;

import br.com.ifce.repository.AgendaRepository;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ExportException;

public class RMIServer {

    private final Registry registry;

    private static final int REGISTRY_PORT = 1099;

    private static final RMIServer INSTANCE = new RMIServer();

    public static RMIServer getInstance() {
        return INSTANCE;
    }

    private RMIServer() {
        try {
            Registry registry;
            try {
                registry = LocateRegistry.createRegistry(REGISTRY_PORT);
            } catch (ExportException e) {
                registry = LocateRegistry.getRegistry(REGISTRY_PORT);
            }
            this.registry = registry;
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void start() {
        try {
            final var agenda = AgendaRepository.getInstance().getAgenda().getName();
            this.registry.rebind(agenda + "/" + AgendaService.name(), new AgendaServiceImpl());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
