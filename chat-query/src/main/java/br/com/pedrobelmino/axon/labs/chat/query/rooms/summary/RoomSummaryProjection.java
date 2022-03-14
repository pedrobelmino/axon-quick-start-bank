package br.com.pedrobelmino.axon.labs.chat.query.rooms.summary;

import br.com.pedrobelmino.axon.labs.chat.coreapi.AllRoomsQuery;
import br.com.pedrobelmino.axon.labs.chat.coreapi.ParticipantJoinedRoomEvent;
import br.com.pedrobelmino.axon.labs.chat.coreapi.ParticipantLeftRoomEvent;
import br.com.pedrobelmino.axon.labs.chat.coreapi.RoomCreatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoomSummaryProjection {

    private final RoomSummaryRepository roomSummaryRepository;

    public RoomSummaryProjection(RoomSummaryRepository roomSummaryRepository) {
        this.roomSummaryRepository = roomSummaryRepository;
    }

    @QueryHandler
    public List<RoomSummary> handle(AllRoomsQuery query) {
        return roomSummaryRepository.findAll();
    }

}
