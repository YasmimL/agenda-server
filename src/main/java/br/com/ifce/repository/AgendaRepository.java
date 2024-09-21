package br.com.ifce.repository;

import br.com.ifce.model.Agenda;
import br.com.ifce.model.Contact;
import br.com.ifce.network.rmi.SyncService;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AgendaRepository {

    private final Agenda agenda;

    private final Set<Contact> contacts = new HashSet<>();

    private static AgendaRepository INSTANCE;

    public static void newInstance(Agenda agenda) {
        INSTANCE = new AgendaRepository(agenda);
    }

    public static AgendaRepository getInstance() {
        return INSTANCE;
    }

    private AgendaRepository(Agenda agenda) {
        this.agenda = agenda;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void add(Contact contact) {
        this.contacts.add(contact);
        SyncService.syncAgendas();
    }

    public void remove(Contact contact) {
        this.contacts.remove(contact);
        SyncService.syncAgendas();
    }

    public List<Contact> getAll() {
        return this.contacts.stream()
            .sorted(Comparator.comparing(Contact::name))
            .toList();
    }

    public void update(Contact contact, String phoneNumber) {
        this.contacts.remove(contact);
        this.contacts.add(new Contact(contact.name(), phoneNumber));
        SyncService.syncAgendas();
    }

    public void replaceAll(Set<Contact> contacts) {
        this.contacts.clear();
        this.contacts.addAll(contacts);
    }
}
