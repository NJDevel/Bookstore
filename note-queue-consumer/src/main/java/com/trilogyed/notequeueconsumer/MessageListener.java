package com.trilogyed.notequeueconsumer;

import com.trilogyed.notequeueconsumer.model.Note;
import com.trilogyed.notequeueconsumer.util.feign.NoteServiceClient;
import com.trilogyed.notequeueconsumer.util.messages.NoteEntry;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {

    @Autowired
    private NoteServiceClient noteClient;

    MessageListener(NoteServiceClient noteClient){
        this.noteClient = noteClient;
    }

    @RabbitListener(queues = NoteQueueConsumerApplication.QUEUE_NAME)
    public void receiveMessage(NoteEntry msg) {
        System.out.println(msg.toString());
        Note note = new Note();
        note.setBookId(msg.getBookId());
        note.setNote(msg.getNote());
        note.setNoteId(msg.getNoteId());

        if(note.getNoteId() == 0){
            noteClient.createNote(note);
        } else
        noteClient.updateNote(note);

    }

}
