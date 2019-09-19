package com.trilogyed.notequeueconsumer.util.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "note-service")
public interface NoteServiceClient {


}
