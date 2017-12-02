package pay4free.in.rajasthantourism.My;

/**
 * Created by AAKASH on 03-12-2017.
 */

public class SimiSimi {
    public String response,id,msg;
    public int result;


    public SimiSimi(String response, String id, String msg, int result) {
        this.response = response;
        this.id = id;
        this.msg = msg;
        this.result = result;
    }

    public SimiSimi() {

    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
