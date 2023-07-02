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
        if (message.message_text.length() >= 255)
            return null;
        return messageDAO.insertMessage(message);
    }

    public Message getMessageById(int id) {
        return messageDAO.getMessageById(id);
    }

    public Message updateMessage(int id, Message message) {
        if (message.message_text == "")
            return null;
        if (message.message_text.length() >= 255)
            return null;
        messageDAO.updateMessage(id, message);
        return messageDAO.getMessageById(id);

    }

    public Message deleteMessage(int id) {
        Message test = messageDAO.getMessageById(id);
        messageDAO.deleteMessage(id);
        if (messageDAO.getMessageById(id) == test)
            return null;
        return messageDAO.getMessageById(id);

    }

}
