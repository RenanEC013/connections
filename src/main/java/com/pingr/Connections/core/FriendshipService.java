package com.pingr.Connections.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class FriendshipService {
    private final FriendshipRepository repo;
    private final ProducerService producer;

    @Autowired
    public FriendshipService(FriendshipRepository repo, ProducerService producer) {
        this.repo = repo;
        this.producer = producer;
    }


    public Friendship createFriendship(Account acc1, Account acc2) throws IllegalArgumentException {
        try {
            Friendship friendship = new Friendship();
            friendship.setAcc1(acc1);
            friendship.setAcc2(acc2);
            this.repo.save(friendship);
            this.producer.emitFriendshipCreatedEvent(friendship);
            return friendship;
        } catch(Exception e) {
            throw new IllegalArgumentException("Friendship creation violates restrictions");
        }
    }

    public void deleteFriendship(Long id, Account acc1, Account acc2) {
        Friendship friendship = this.repo.getById(id);
        this.repo.deleteById(id);
        this.producer.emitFriendshipRemovedEvent(friendship);

        Set<Integer> friendshipsAcc1 = acc1.getFriendships();
        // Remove a ocorrência da amizade para a primeira conta
        boolean remocaoAcc1 = friendshipsAcc1.remove(id);

        Set<Integer> friendshipsAcc2 = acc2.getFriendships();
        // Remove a ocorrência da amizade para a segunda conta
        boolean remocaoAcc2 = friendshipsAcc2.remove(id);
    }

    // Retorna o número de amizades para uma determinada conta
    public Integer getNumberFriends(Account acc) {
        return acc.getFriendships().size();
    }
}
