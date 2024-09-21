package br.com.ifce.network.rmi;

import br.com.ifce.model.Agenda;
import br.com.ifce.repository.AgendaRepository;

import java.rmi.Naming;
import java.util.HashSet;

public class SyncService {

    private SyncService() {
    }

    public static void syncAgendas() {
        final var repo = AgendaRepository.getInstance();
        final var agenda = repo.getAgenda();
        Agenda.getAllExcept(agenda)
            .forEach(it -> {
                try {
                    final var service = (AgendaService) Naming.lookup(it + "/" + AgendaService.name());
                    service.sync(new HashSet<>(repo.getAll()));
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            });
    }

    public static void syncSelf() {
        final var repo = AgendaRepository.getInstance();
        final var agenda = repo.getAgenda();
        for (var it : Agenda.getAllExcept(agenda)) {
            try {
                final var service = (AgendaService) Naming.lookup(it + "/" + AgendaService.name());
                repo.replaceAll(new HashSet<>(service.getAll()));
                break;
            } catch (Exception ignored) {
            }
        }
    }
}
