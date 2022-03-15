package br.com.pedrobelmino.axon.labs.chat.restapi;

import br.com.pedrobelmino.axon.labs.chat.query.rooms.messages.ChatMessage;
import br.com.pedrobelmino.axon.labs.chat.coreapi.AllRoomsQuery;
import br.com.pedrobelmino.axon.labs.chat.coreapi.RoomMessagesQuery;
import br.com.pedrobelmino.axon.labs.chat.coreapi.RoomParticipantsQuery;
import br.com.pedrobelmino.axon.labs.chat.query.rooms.summary.RoomSummary;
import org.axonframework.messaging.responsetypes.MultipleInstancesResponseType;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.SubscriptionQueryResult;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.Future;

import static org.axonframework.messaging.responsetypes.ResponseTypes.instanceOf;
import static org.axonframework.messaging.responsetypes.ResponseTypes.multipleInstancesOf;


@RestController
public class QueryController {

    private final QueryGateway queryGateway;

    public QueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping("rooms")
    public Future<List<RoomSummary>> listRooms() {
        return queryGateway.query(new AllRoomsQuery(), new MultipleInstancesResponseType<>(RoomSummary.class));
    }

    @GetMapping("/rooms/{roomId}/participants")
    public Future<List<String>> participantsInRoom(@PathVariable String roomId) {
        return queryGateway.query(new RoomParticipantsQuery(roomId), new MultipleInstancesResponseType<>(String.class));
    }

    @GetMapping("/rooms/{roomId}/messages")
    public Future<List<ChatMessage>> roomMessages(@PathVariable String roomId) {
        return queryGateway.query(new RoomMessagesQuery(roomId), new MultipleInstancesResponseType<>(ChatMessage.class));
    }


}
