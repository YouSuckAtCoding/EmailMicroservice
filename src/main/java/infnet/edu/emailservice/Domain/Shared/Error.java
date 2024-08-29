package infnet.edu.emailservice.Domain.Shared;

public class Error {

    public String Code ;
    public String Message;

    public Error(String code, String message)
    {
        Code = code;
        Message = message;
    }

    public static Error None() { return new Error("", ""); }
    public static Error NullValue() {return new Error("Error.NullValue", "The specified result value is null.");}
    
    public static boolean IsNone(Error error)
    {
        if(error.Code == None().Code && error.Message == None().Message)
            return true;
        return false;
    }


    

}
