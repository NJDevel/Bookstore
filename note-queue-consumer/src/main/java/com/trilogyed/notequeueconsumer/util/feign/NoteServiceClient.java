package com.trilogyed.notequeueconsumer.util.feign;

import com.trilogyed.notequeueconsumer.model.Note;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "note-service")
public interface NoteServiceClient {

    @RequestMapping(value = "/notes", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Note createNote(@RequestBody Note note);

    @RequestMapping(value = "/notes/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String updateNote(@PathVariable("id") int noteId, @RequestBody Note note);

}
