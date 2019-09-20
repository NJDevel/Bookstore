package com.trilogyed.bookservice.util.messages;

import java.util.Objects;

public class NoteEntry {

    private int noteId;
    private int bookId;
    private String note;

    public NoteEntry(){
    }

    public NoteEntry(int bookId, String note){};

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoteEntry noteEntry1 = (NoteEntry) o;
        return getNoteId() == noteEntry1.getNoteId() &&
                getBookId() == noteEntry1.getBookId() &&
                getNote().equals(noteEntry1.getNote());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNoteId(), getBookId(), getNote());
    }

    @Override
    public String toString() {
        return "NoteEntry{" +
                "noteId=" + noteId +
                ", bookId=" + bookId +
                ", note='" + note + '\'' +
                '}';
    }
}
