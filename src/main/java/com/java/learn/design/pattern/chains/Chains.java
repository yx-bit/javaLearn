package com.java.learn.design.pattern.chains;

public class Chains {
    public static void main(String[] args) {
        Request request = new Request(true, true);
        RequestHandler requestHandler = new RequestHandler(new LoginHandler(null));
        boolean process = requestHandler.process(request);
        System.out.println(process);
    }
}


class Request {
    private boolean isOK;
    private boolean isSuccess;

    public Request(boolean isOK, boolean isSuccess) {
        this.isOK = isOK;
        this.isSuccess = isSuccess;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public boolean isOK() {
        return isOK;
    }

    public void setOK(boolean OK) {
        isOK = OK;
    }
}

abstract class Handler {
    Handler next;

    public Handler(Handler next) {
        this.next = next;
    }

    public Handler getNext() {
        return next;
    }

    public void setNext(Handler next) {
        this.next = next;
    }

    abstract boolean process(Request request);
}

class LoginHandler extends Handler {
    public LoginHandler(Handler next) {
        super(next);
    }

    @Override
    boolean process(Request request) {
        System.out.println("Login处理中");
        if (request.isSuccess()) {
            Handler next = getNext();
            if (next == null) {
                return true;
            }
            if (next.process(request)) {
                return true;
            }
        }
        return false;
    }
}
class RequestHandler extends Handler {
    public RequestHandler(Handler next) {
        super(next);
    }

    @Override
    boolean process(Request request) {
        System.out.println("处理中");
        if (request.isOK()) {
            Handler next = getNext();
            if (next == null) {
                return true;
            }
            if (next.process(request)) {
                return true;
            }
        }
        return false;
    }
}