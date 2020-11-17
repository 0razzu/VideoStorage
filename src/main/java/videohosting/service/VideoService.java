package videohosting.service;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import videohosting.client.Client;
import videohosting.datahub.DataHub;
import videohosting.model.Author;
import videohosting.model.VideoRecording;
import videohosting.storage.VideoStorage;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


@RequiredArgsConstructor
public class VideoService {
    private final Logger LOGGER = LoggerFactory.getLogger(VideoStorage.class);
    private final DataHub dataHub;
    private final Map<Author, Set<Client>> subscribers = new HashMap<>();
    
    
    public void registerAuthor(Author author) {
        LOGGER.info("Registering {}", author);
        
        subscribers.put(author, new HashSet<>());
    }
    
    
    public void subscribe(Client client, Author author) {
        LOGGER.info("Subscribing {} to {}", client, author);
        
        subscribers.get(author).add(client);
    }
    
    
    public void unsubscribe(Client client, Author author) {
        LOGGER.info("Unsubscribing {} from {}", client, author);
        
        subscribers.get(author).remove(client);
    }
    
    
    public void publishVideo(VideoRecording recording) {
        LOGGER.info("Publishing {}", recording);
        
        recording.setFilePath(dataHub.saveVideoRecording(recording));
        
        for (Client subscriber: subscribers.get(recording.getAuthor()))
            subscriber.update(recording);
    }
}
