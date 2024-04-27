package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.Objects;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class CourseContent {
    
@Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int video_id;

    @NotEmpty(message = "Please enter a title for the video")
    private String video_title;

    @NotEmpty(message = "Please enter the playlist of the video")
    private String video_playlist;

    @NotEmpty(message = "Please enter a description for the video")
    private String video_description;

    @NotEmpty(message = "Please select thumbnail for the video")
    private String video_thumbnail;

    @NotEmpty(message = "Please upload the video")
    private String video_play;


    
    @OneToOne
    @JoinColumn(name = "course_id") 
    private Courses course;



    public CourseContent() {
    }

    public CourseContent(int video_id, String video_title, String video_playlist, String video_description, MultipartFile video_thumbnail, MultipartFile video_play, Courses course) {
        this.video_id = video_id;
        this.video_title = video_title;
        this.video_playlist = video_playlist;
        this.video_description = video_description;
        this.video_thumbnail = video_thumbnail;
        this.video_play = video_play;
        this.course = course;
    }

    public int getVideo_id() {
        return this.video_id;
    }

    public void setVideo_id(int video_id) {
        this.video_id = video_id;
    }

    public String getVideo_title() {
        return this.video_title;
    }

    public void setVideo_title(String video_title) {
        this.video_title = video_title;
    }

    public String getVideo_playlist() {
        return this.video_playlist;
    }

    public void setVideo_playlist(String video_playlist) {
        this.video_playlist = video_playlist;
    }

    public String getVideo_description() {
        return this.video_description;
    }

    public void setVideo_description(String video_description) {
        this.video_description = video_description;
    }

    public MultipartFile getVideo_thumbnail() {
        return this.video_thumbnail;
    }

    public void setVideo_thumbnail(MultipartFile video_thumbnail) {
        this.video_thumbnail = video_thumbnail;
    }

    public MultipartFile getVideo_play() {
        return this.video_play;
    }

    public void setVideo_play(MultipartFile video_play) {
        this.video_play = video_play;
    }

    public Courses getCourse() {
        return this.course;
    }

    public void setCourse(Courses course) {
        this.course = course;
    }

    public CourseContent video_id(int video_id) {
        setVideo_id(video_id);
        return this;
    }

    public CourseContent video_title(String video_title) {
        setVideo_title(video_title);
        return this;
    }

    public CourseContent video_playlist(String video_playlist) {
        setVideo_playlist(video_playlist);
        return this;
    }

    public CourseContent video_description(String video_description) {
        setVideo_description(video_description);
        return this;
    }

    public CourseContent video_thumbnail(MultipartFile video_thumbnail) {
        setVideo_thumbnail(video_thumbnail);
        return this;
    }

    public CourseContent video_play(MultipartFile video_play) {
        setVideo_play(video_play);
        return this;
    }

    public CourseContent course(Courses course) {
        setCourse(course);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CourseContent)) {
            return false;
        }
        CourseContent courseContent = (CourseContent) o;
        return video_id == courseContent.video_id && Objects.equals(video_title, courseContent.video_title) && Objects.equals(video_playlist, courseContent.video_playlist) && Objects.equals(video_description, courseContent.video_description) && Objects.equals(video_thumbnail, courseContent.video_thumbnail) && Objects.equals(video_play, courseContent.video_play) && Objects.equals(course, courseContent.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(video_id, video_title, video_playlist, video_description, video_thumbnail, video_play, course);
    }

    @Override
    public String toString() {
        return "{" +
            " video_id='" + getVideo_id() + "'" +
            ", video_title='" + getVideo_title() + "'" +
            ", video_playlist='" + getVideo_playlist() + "'" +
            ", video_description='" + getVideo_description() + "'" +
            ", video_thumbnail='" + getVideo_thumbnail() + "'" +
            ", video_play='" + getVideo_play() + "'" +
            ", course='" + getCourse() + "'" +
            "}";
    }
    
    
}
