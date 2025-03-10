package lk.ijse.party_creation.advisor;

import lk.ijse.party_creation.util.ResponseUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@CrossOrigin
public class AppWideExceptionHandler {
    public ResponseUtil exceptionHandler(Exception ex){
        return new ResponseUtil(500,ex.getMessage(),null);
    }
}
