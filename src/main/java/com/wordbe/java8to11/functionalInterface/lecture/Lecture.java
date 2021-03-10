package com.wordbe.java8to11.functionalInterface.lecture;

import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter @Setter
public class Lecture {

    private Integer id;
    private String title;
    private boolean closed;

    private Progress progress;

    public Lecture(Integer id, String title, boolean closed) {
        this.id = id;
        this.title = title;
        this.closed = closed;
    }

    public Optional<Progress> getProgress() {
        return Optional.ofNullable(progress);
    }

    public void setProgress(Optional<Progress> progress) {
        if (progress != null) {
            progress.ifPresent(p -> this.progress = p);
        }
    }
}
