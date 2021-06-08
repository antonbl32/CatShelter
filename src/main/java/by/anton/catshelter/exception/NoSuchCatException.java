package by.anton.catshelter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoSuchCatException extends RuntimeException {
    public NoSuchCatException(String s) {
        super(s);
    }
}
