package product;

/**
 * Created by Kyra on 19/03/2016.
 */
public class NegatieveVoorraadException extends RuntimeException {
    NegatieveVoorraadException(String message){
        super(message);
    }
}