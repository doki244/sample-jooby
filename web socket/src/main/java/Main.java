
import io.jooby.Jooby;
import io.jooby.ServerOptions;
import io.jooby.WebSocket;
import io.jooby.internal.WebSocketSender;
//import org.jooby.WebSocket;
import org.json.JSONObject;
//import org.jooby.Jooby;

import java.util.HashMap;

public class Main extends Jooby {

    public static void main(String[] args) {
        Jooby j = new Jooby();
        HashMap<WebSocketSender,WebSocket> d = new HashMap<>();
        j.setServerOptions(new ServerOptions()
                .setPort(8585));
        j.ws("/ws", (ctx, configurer) -> {
            configurer.onConnect(ws -> {
                ws.send("Connected");
                //save context and websocketSender of online user
                d.put(new WebSocketSender(ctx,ws),ws);
                System.out.println("Connected "+ ctx.header().get("Sec-WebSocket-Key"));
            });

            configurer.onMessage((ws, message) -> {
                System.out.println(message.value());
                ws.send("message received");
            });

            configurer.onClose((ws, statusCode) -> {
                System.out.println("close");
                //d.removeIf(item -> item.header().get("Sec-WebSocket-Key").equals(ctx.header().get("Sec-WebSocket-Key")));
                System.out.println(d.size());
            });

            configurer.onError((ws, cause) -> {
                System.out.println(cause.getMessage()+cause.toString());
            });
        });
        j.start();
    }
}


