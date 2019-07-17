package hangar.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class HangarException {


    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class HangarNotFoundException extends RuntimeException {

        public HangarNotFoundException() {
            super("There is not hangar");
        }

        public HangarNotFoundException(Long id) {
            super(String.format("The hangar %d doesn't exist", id));
        }
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    public static class HangarExistException extends RuntimeException {

        public HangarExistException() { super("Hangar already exist"); }

        public HangarExistException(Long id) {
            super(String.format("The hangar %d already exist", id));
        }
    }

}
