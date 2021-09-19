package org.schors.cyprus.car;

import org.slyrack.telegrambots.annotations.Controller;
import org.slyrack.telegrambots.annotations.MiddleHandler;
import org.slyrack.telegrambots.session.Session;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class SessionConfigurer {

    private final BotProperties botProperties;

    public SessionConfigurer(BotProperties botProperties) {
        this.botProperties = botProperties;
    }

    @MiddleHandler
    public void configureSession(final Update update, final Session session) {
        if (session == null) return;

        if (!session.containsAttribute("chat-id"))
            Util.getChatId(update)
                    .ifPresent(chatId -> session.setAttribute("chat-id", String.valueOf(chatId)));

        if (!session.containsAttribute("user"))
            Util.getUser(update)
                    .ifPresent(user -> session.setAttribute("user", user));

        if (!session.containsAttribute("questions")) {
            List<Map> questions = new ArrayList<>();
            questions.addAll(botProperties.getQuestions());
            session.setAttribute("questions", questions);
        }

        if (!session.containsAttribute("answers")) {
            session.setAttribute("answers", new ArrayList<Answer>());
        }

        if (!session.containsAttribute("farewell")) {
            session.setAttribute("farewell", botProperties.getFarewell());
        }

        if (!session.containsAttribute("greeting")) {
            session.setAttribute("greeting", botProperties.getGreeting());
        }

        if (!session.containsAttribute("cancel")) {
            session.setAttribute("cancel", botProperties.getCancel());
        }

        if (!session.containsAttribute("admins")) {
            session.setAttribute("admins", botProperties.getAdmins());
        }

    }
}
