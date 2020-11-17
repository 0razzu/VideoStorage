package videohosting.storage;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import videohosting.model.VideoRecording;

import java.util.UUID;


public class VideoStorage {
    private final Logger LOGGER = LoggerFactory.getLogger(VideoStorage.class);
    
    
    public String saveRecording(String path) {
        LOGGER.info("Saving file from {}", path);
        
        return UUID.randomUUID().toString() + ".mp4";
    }
}
