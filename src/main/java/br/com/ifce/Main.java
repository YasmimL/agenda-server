package br.com.ifce;

import br.com.ifce.model.Agenda;
import br.com.ifce.network.rmi.RMIServer;
import br.com.ifce.network.rmi.SyncService;
import br.com.ifce.repository.AgendaRepository;

public class Main {

    public static void main(String[] args) {
        if (args.length < 1) return;
        final var agenda = Agenda.from(args[0]);
        if (agenda == null) return;

        AgendaRepository.newInstance(agenda);
        SyncService.syncSelf();

        RMIServer.getInstance().start();

        System.out.println(agenda.getName() + " running...");
    }
}