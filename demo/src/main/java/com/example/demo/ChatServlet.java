package com.example.demo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import com.example.demo.ChatMessage;
import com.example.demo.ChatUser;
@WebServlet(name = "ChatServlet",urlPatterns = "/this-is-my-servlet")
public class ChatServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    // Карта текущих пользователей
    protected HashMap<String, ChatUser> activeUsers;
    // Список сообщений чата
    protected ArrayList<ChatMessage> messages;
    @SuppressWarnings("unchecked")
    public void init() throws ServletException {
// Вызвать унаследованную от HttpServlet версию init()
        super.init();
// Извлечь из контекста карту пользователей и список сообщений
        activeUsers = (HashMap<String, ChatUser>) getServletContext().getAttribute("activeUsers");
        messages = (ArrayList<ChatMessage>) getServletContext().getAttribute("messages");
// Если карта пользователей не определена ...
        if (activeUsers==null)
        {
// Создать новую карту
            activeUsers = new HashMap<String, ChatUser>();
            ///добавить системного бота
            String name = "System";
            String sessionId = "1111";
            ChatUser aUser = new ChatUser(name,Calendar.getInstance().getTimeInMillis(), sessionId);
// Так как одновременно выполняются запросы
// от множества пользователей
// то необходима синхронизация на ресурсе
            synchronized (activeUsers)
            {
                activeUsers.put("System", aUser);
            }


            // Поместить карту в контекст сервлета,
            // чтобы другие сервлеты могли до него добраться
            getServletContext().setAttribute("activeUsers", activeUsers);
        }
// Если список сообщений не определѐн ...
        if (messages==null)
        {
// Создать новый список
            messages = new ArrayList<ChatMessage>(100);
// Поместить его в контекст сервлета,
// чтобы другие сервлеты могли до него добрать
            getServletContext().setAttribute("messages", messages);
        }
    }
}

