package webshop.exception.custom;

import lombok.Getter;

@Getter
public class EntityAlreadyExistException extends RuntimeException {

    public EntityAlreadyExistException(String message){
        super(message);
    }
}
