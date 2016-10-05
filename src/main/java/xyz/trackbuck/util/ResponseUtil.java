package xyz.trackbuck.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by lex on 29.09.16.
 */
public class ResponseUtil {
    public static ResponseEntity getNotFound(String message){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    };
    public static ResponseEntity  getBadRequest(String message){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    public static ResponseEntity  getOkRequest(Object o){
        return new ResponseEntity(o, HttpStatus.OK);
    }
}
