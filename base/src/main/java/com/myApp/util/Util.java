package com.myApp.util;

import com.myApp.exception.ApplicationException;
import com.myApp.exception.ApplicationExceptionCause;
import org.springframework.stereotype.Component;

@Component
public class Util {

    public void checkId(long id) {
        if (id <= 0 )
            throw new ApplicationException(ApplicationExceptionCause.ID_NOT_ALLOWED);
    }

    public void checkNumber(double number) {
        if (number < 0)
            throw new ApplicationException(ApplicationExceptionCause.QUANT_NOT_ALLOWED);
    }
}
