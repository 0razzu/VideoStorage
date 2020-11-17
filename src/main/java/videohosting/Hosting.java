package videohosting;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import videohosting.client.Client;
import videohosting.client.PublisherClient;
import videohosting.client.SubscriberClient;
import videohosting.datahub.DataHub;
import videohosting.model.Author;
import videohosting.model.VideoRecording;
import videohosting.service.VideoService;
import videohosting.storage.VideoStorage;


public class Hosting {
    private static final Logger LOGGER = LoggerFactory.getLogger(Hosting.class);
    
    
    public static void main(String[] args) {
        VideoService videoService = new VideoService(new DataHub(new VideoStorage()));
        
        Author blogger = new Author("YetAnotherBlogger");
        Author nationalGeographic = new Author("National Geographic");
        
        VideoRecording tapeVideo = new VideoRecording(
                "Sticking tape to my face",
                "Why not?",
                blogger,
                "file://~/tape.mp4"
        );
        VideoRecording catVideo = new VideoRecording(
                "Teaching my cat to speak",
                "She’s extremely intelligent",
                blogger,
                "file://~/meow.mp4"
        );
        VideoRecording catDocumentary = new VideoRecording(
                "Can cats speak English?",
                "Popular myths about cats’ intelligence",
                nationalGeographic,
                "https://natgeo.com/cats.mp4"
        );
        
        Client bloggerClient = new PublisherClient("YetAnotherBlogger");
        Client everyonesSub = new SubscriberClient("Everyone’s subscriber");
        Client bloggersSub = new SubscriberClient("Blogger’s subscriber");
        Client natGeosSub = new SubscriberClient("National Geographic’s subscriber");
        
        LOGGER.info("REGISTERING AUTHORS & SUBSCRIBING SUBSCRIBERS");
        videoService.registerAuthor(blogger);
        videoService.registerAuthor(nationalGeographic);
        videoService.subscribe(bloggerClient, blogger);
        videoService.subscribe(everyonesSub, blogger);
        videoService.subscribe(bloggersSub, blogger);
        videoService.subscribe(everyonesSub, nationalGeographic);
        videoService.subscribe(natGeosSub, nationalGeographic);
        
        LOGGER.info("PUBLISHING VIDEOS & UNSUBSCRIBING SUBSCRIBERS");
        videoService.publishVideo(tapeVideo);
        videoService.unsubscribe(bloggersSub, blogger);
        videoService.publishVideo(catVideo);
        videoService.publishVideo(catDocumentary);
    }
}
