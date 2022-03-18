package br.com.pedrobelmino.axon.labs.chat.query.rooms.summary;

import br.com.pedrobelmino.axon.labs.chat.coreapi.AllRoomsQuery;
import br.com.pedrobelmino.axon.labs.chat.coreapi.ParticipantJoinedRoomEvent;
import br.com.pedrobelmino.axon.labs.chat.coreapi.ParticipantLeftRoomEvent;
import br.com.pedrobelmino.axon.labs.chat.coreapi.RoomCreatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoomSummaryProjection {

    private final RoomSummaryRepository roomSummaryRepository;

    public RoomSummaryProjection(RoomSummaryRepository roomSummaryRepository) {
        this.roomSummaryRepository = roomSummaryRepository;
    }

    @EventHandler
    public void on(RoomCreatedEvent event) throws Exception  {
        roomSummaryRepository.save(new RoomSummary(event.getRoomId(), event.getName()));
    }

    @EventHandler
    public void on(ParticipantJoinedRoomEvent event) {
        roomSummaryRepository.getOne(event.getRoomId()).addParticipant();
    }

    @EventHandler
    public void on(ParticipantLeftRoomEvent event) {
        roomSummaryRepository.getOne(event.getRoomId()).removeParticipant();
    }
}
