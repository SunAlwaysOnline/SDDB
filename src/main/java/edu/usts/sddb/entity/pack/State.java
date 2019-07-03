package edu.usts.sddb.entity.pack;

public class State {
    private boolean success;
    private String info;

    public State() {
    }

    public State(boolean success, String info) {
        this.success = success;
        this.info = info;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }


    @Override
    public String toString() {
        return "State{" +
                "success=" + success +
                ", info='" + info + '\'' +
                '}';
    }
}
