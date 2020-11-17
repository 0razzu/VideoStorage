package videohosting.client;


import videohosting.model.VideoRecording;


public interface Client {
    String getName();
    
    void update(VideoRecording recording);
}
