package videohosting.client;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import videohosting.model.VideoRecording;


@AllArgsConstructor
@Data
public class SubscriberClient implements Client {
    private final Logger LOGGER = LoggerFactory.getLogger(SubscriberClient.class);
    private String name;
    
    
    @Override
    public void update(VideoRecording recording) {
        LOGGER.info("Hey, {}!", name);
        LOGGER.info("{} has published a new video «{}»!", recording.getAuthor().getName(), recording.getName());
        LOGGER.info("Click to watch: {}", recording.getFilePath());
    }
    
    
    @Override
    public String toString() {
        return "SubscriberClient{" +
                "name='" + name + '\'' +
                '}';
    }
}
