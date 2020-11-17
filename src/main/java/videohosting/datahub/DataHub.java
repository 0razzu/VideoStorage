package videohosting.datahub;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import videohosting.model.Author;
import videohosting.model.VideoRecording;
import videohosting.storage.VideoStorage;

import java.util.HashMap;
import java.util.Map;


@RequiredArgsConstructor
public class DataHub {
    private final Logger LOGGER = LoggerFactory.getLogger(DataHub.class);
    private final VideoStorage videoStorage;
    private final Map<String, VideoRecording> videoRecordings = new HashMap<>();
    
    
    public String saveVideoRecording(VideoRecording recording) {
        LOGGER.info("Saving {}", recording);
        
        String newFilePath = videoStorage.saveRecording(recording.getFilePath());
        VideoRecording recordingCopy = new VideoRecording(
                recording.getName(),
                recording.getDescription(),
                new Author(recording.getAuthor().getName()),
                newFilePath
        );
        videoRecordings.put(newFilePath, recordingCopy);
        
        return newFilePath;
    }
    
    
    public VideoRecording getVideoRecording(String filePath) {
        LOGGER.info("Getting video recording from {}", filePath);
        
        return videoRecordings.get(filePath);
    }
}
