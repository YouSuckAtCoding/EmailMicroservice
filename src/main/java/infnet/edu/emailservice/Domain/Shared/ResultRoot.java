package infnet.edu.emailservice.Domain.Shared;


public class ResultRoot {

    public boolean IsSuccess;
    public static Error Error;

    public boolean IsSuccess() {
        return IsSuccess;
    }

    public Error getError() {
        return Error;
    }

    public ResultRoot(boolean isSuccess, Error error) throws Exception {
       
        if (!isSuccess && infnet.edu.emailservice.Domain.Shared.Error.IsNone(error)) {
            throw new Exception();
        }
        if (isSuccess && !infnet.edu.emailservice.Domain.Shared.Error.IsNone(error)) {
            throw new Exception();
        }

        IsSuccess = isSuccess;
        Error = error;
    }

    public static <T> ResultRoot Success() throws Exception{
        return new ResultRoot(true, infnet.edu.emailservice.Domain.Shared.Error.None());
    }
    public static <T> ResultRoot Failure(Error error) throws Exception{
        return new ResultRoot(false, error);
    }    
    
}
