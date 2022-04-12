
import io.jooby.Jooby;
import io.jooby.ServerOptions;
//import io.jooby.WebSocket;
//import io.jooby.internal.WebSocketSender;
//import org.jooby.WebSocket;
import org.json.JSONObject;


public class Main extends Jooby {

    public static void main(String[] args) {
        Jooby j = new Jooby();
        j.setServerOptions(new ServerOptions()
                .setPort(8585));
        j.get("/get", ctx -> {
            ctx.setResponseCode(200);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("hi", "hello");
            return jSONObject;
        });
        j.post("/post", ctx -> {
            System.out.println(ctx.body().value());
            return ctx.header().value();
        });
        j.start();
    }
}


