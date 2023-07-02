package Service;

import Model.Message;
import DAO.MessageDAO;

import java.util.List;

public class MessageService {
    MessageDAO messageDAO;

    public MessageService() {
        messageDAO = new MessageDAO();
    }

    public MessageService(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    public Message insertMessage(Message message) {
        if (message.message_text == "")
            return null;
        String testmessage = message.getMessage_text();
        if (testmessage.length() > 255)
            return null;
        return messageDAO.insertMessage(message);
    }

    public Message getMessageById(int id) {
        return messageDAO.getMessageById(id);
    }

    public Message updateMessage(int id, Message message) {
        if (message.message_text == "")
            return null;

        return messageDAO.getMessageById(id);

    }

}
