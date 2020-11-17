package videohosting.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class VideoRecording {
    private String name;
    private String description;
    private Author author;
    private String filePath;
    
    
    @Override
    public String toString() {
        return "VideoRecording{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", author=" + author +
                ", filePath='" + filePath + '\'' +
                '}';
    }
}
