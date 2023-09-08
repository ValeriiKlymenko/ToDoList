package com.klimjavadev.todolist.models.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

public class ToDoDto {
    private long id;

    @NotBlank(message = "The 'title' cannot be empty")
    private String title;

    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    private long owner;

    public ToDoDto() {
    }

    public ToDoDto(long id, String title, LocalDateTime createdAt, long owner) {
        this.id = id;
        this.title = title;
        this.createdAt = createdAt;
        this.owner = owner;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public long getOwner() {
        return owner;
    }

    public void setOwner(long owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDoDto toDoDto = (ToDoDto) o;
        return id == toDoDto.id && owner == toDoDto.owner && Objects.equals(title, toDoDto.title) && Objects.equals(createdAt, toDoDto.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, createdAt, owner);
    }

    @Override
    public String toString() {
        return "ToDoDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", createdAt=" + createdAt +
                ", owner=" + owner +
                '}';
    }
}
