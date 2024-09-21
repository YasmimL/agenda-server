package br.com.ifce.network.rmi;

import br.com.ifce.model.Contact;
import br.com.ifce.repository.AgendaRepository;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Set;

public class AgendaServiceImpl extends UnicastRemoteObject implements AgendaService {

    protected AgendaServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public void add(Contact contact) {
        AgendaRepository.getInstance().add(contact);
    }

    @Override
    public void remove(Contact contact) {
        AgendaRepository.getInstance().remove(contact);
    }

    @Override
    public List<Contact> getAll() {
        return AgendaRepository.getInstance().getAll();
    }

    @Override
    public void update(Contact contact, String phoneNumber) {
        AgendaRepository.getInstance().update(contact, phoneNumber);
    }

    @Override
    public void sync(Set<Contact> contacts) {
        AgendaRepository.getInstance().replaceAll(contacts);
    }
}
