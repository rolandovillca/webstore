package webshop.exception.custom;

import lombok.Getter;

@Getter
public class EntityNotFoundException extends RuntimeException {

    private String message;

    public EntityNotFoundException(String message){
        this.message = message;
    }

}
