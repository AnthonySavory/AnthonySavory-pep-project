package Controller;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;

import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * TODO: You will need to write your own endpoints and handlers for your
 * controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a
 * controller may be built.
 */
public class SocialMediaController {

    AccountService accountService;
    MessageService messageService;

    public SocialMediaController() {
        this.accountService = new AccountService();
        this.messageService = new MessageService();
    }

    /**
     * In order for the test cases to work, you will need to write the endpoints in
     * the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * 
     * @return a Javalin app object which defines the behavior of the Javalin
     *         controller.
     */

    public Javalin startAPI() {
        Javalin app = Javalin.create();
        // app.get("example-endpoint", this::exampleHandler);
        app.post("/register", this::registerAccountHandler);
        app.post("/login", this::loginAccountHandler);
        app.get("/messages", this::getAllMessagesHandler);
        app.post("/messages", this::createMessageHandle);
        app.get("/messages/{message_id}", this::getMessagesByIdHandler);
        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * 
     * @param context The Javalin Context object manages information about both the
     *                HTTP request and response.
     */
    // private void exampleHandler(Context context) {
    // context.json("sample text");
    // }

    private void registerAccountHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account addedAccount = accountService.UserRegister(account);
        if (addedAccount != null) {
            ctx.json(mapper.writeValueAsString(addedAccount));
        } else {
            ctx.status(400);
        }
    }

    private void loginAccountHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account login = accountService.UserLogin(account);
        if (login != null) {
            ctx.json(login);
        } else {
            ctx.status(401);
        }
    }

    // Messages Handlers

    private void getAllMessagesHandler(Context ctx) {
        ctx.json(messageService.getAllMessages());
    }

    private void createMessageHandle(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
        Message addedMessgae = messageService.insertMessage(message);
        if (addedMessgae != null) {
            ctx.json(mapper.writeValueAsString(addedMessgae));
        } else {
            ctx.status(400);
        }
    }

    private void getMessagesByIdHandler(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("message_id"));

        Message msg = messageService.getMessageById(id);
        if (msg != null) {
            ctx.json(msg);
        } else {
            ctx.json("");
        }
    }

}