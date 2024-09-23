package br.com.ifce;

import br.com.ifce.model.Agenda;
import br.com.ifce.network.rmi.RMIServer;
import br.com.ifce.network.rmi.SyncService;
import br.com.ifce.repository.AgendaRepository;

public class Main {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please provide a valid agenda (Ex.: agenda1, agenda2, agenda3)");
            return;
        }
        final var agenda = Agenda.from(args[0]);
        if (agenda == null) {
            System.out.println("Please provide a valid agenda (Ex.: agenda1, agenda2, agenda3)");
            return;
        }

        AgendaRepository.newInstance(agenda);
        SyncService.syncSelf();

        RMIServer.getInstance().start();

        System.out.println(agenda.getName() + " running...");
    }
}