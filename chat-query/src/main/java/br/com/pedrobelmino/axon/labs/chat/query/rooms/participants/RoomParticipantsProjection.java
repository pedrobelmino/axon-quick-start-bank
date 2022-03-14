package br.com.pedrobelmino.axon.labs.chat.query.rooms.participants;

import br.com.pedrobelmino.axon.labs.chat.coreapi.ParticipantJoinedRoomEvent;
import br.com.pedrobelmino.axon.labs.chat.coreapi.ParticipantLeftRoomEvent;
import br.com.pedrobelmino.axon.labs.chat.coreapi.RoomParticipantsQuery;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class RoomParticipantsProjection {

    private final RoomParticipantsRepository repository;

    public RoomParticipantsProjection(RoomParticipantsRepository repository) {
        this.repository = repository;
    }

    @QueryHandler
    public List<String> handle(RoomParticipantsQuery query) {
        return repository.findRoomParticipantsByRoomId(query.getRoomId())
                         .stream()
                         .map(RoomParticipant::getParticipant).sorted().collect(toList());
    }

}
