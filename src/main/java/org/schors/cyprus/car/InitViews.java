package org.schors.cyprus.car;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slyrack.telegrambots.annotations.SessionAtr;
import org.slyrack.telegrambots.annotations.View;
import org.slyrack.telegrambots.annotations.ViewController;
import org.slyrack.telegrambots.session.Session;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ViewController
public class InitViews {
    private Logger logger = LoggerFactory.getLogger(InitViews.class);

    @SneakyThrows
    @View("mashinkov")
    public void mashinkov(final AbsSender sender,
                          @SessionAtr("chat-id") String chatId) {
        sender.execute(SendMessage.builder()
                .text("Вы продаете машинков?")
                .chatId(chatId)
                .replyMarkup(InlineKeyboardMarkup.builder()
                        .keyboardRow(List.of(InlineKeyboardButton.builder()
                                .text("Нет, только показываем")
                                .callbackData("no")
                                .build()))
                        .build())
                .build());
    }

    @View("none")
    public void none() {
    }

    @SneakyThrows
    @View("showQuestion")
    public void showQuestion(final AbsSender sender,
                             @SessionAtr("chat-id") String chatId,
                             @SessionAtr("current") Map question) {
        String text = (String) question.get("question");
        sender.execute(SendMessage.builder()
                .parseMode(ParseMode.HTML)
                .text(text)
                .chatId(chatId)
                .build());
    }

    @SneakyThrows
    @View("showSingleInline")
    public void showSingleInline(final AbsSender sender,
                                 @SessionAtr("chat-id") String chatId,
                                 @SessionAtr("current") Map question) {
        String text = (String) question.get("question");
        Map<String, String> answer = (Map) question.get("answer");

        SendMessage.SendMessageBuilder builder = SendMessage.builder();
        builder.parseMode(ParseMode.HTML).text(text).chatId(chatId);
        InlineKeyboardMarkup.InlineKeyboardMarkupBuilder inlineKeyboardMarkupBuilder = InlineKeyboardMarkup.builder();
        Util.getTuples(answer.values(), 2)
                .forEach(tuple -> inlineKeyboardMarkupBuilder
                        .keyboardRow(tuple.map(s -> InlineKeyboardButton.builder()
                                        .text(s)
                                        .callbackData(s)
                                        .build())
                                .collect(Collectors.toList())));
        builder.replyMarkup(inlineKeyboardMarkupBuilder.build());
        sender.execute(builder.build());
    }

    @SneakyThrows
    @View("showMultiInline")
    public void showMultiInline(final AbsSender sender,
                                @SessionAtr("chat-id") String chatId,
                                @SessionAtr("current") Map question) {
        String text = (String) question.get("question");
        Map<String, String> answers = (Map) question.get("answers");

        SendMessage.SendMessageBuilder builder = SendMessage.builder();
        builder.parseMode(ParseMode.HTML).text(text).chatId(chatId);
        InlineKeyboardMarkup.InlineKeyboardMarkupBuilder inlineKeyboardMarkupBuilder = InlineKeyboardMarkup.builder();
        Util.getTuples(answers.values(), 2)
                .forEach(tuple -> inlineKeyboardMarkupBuilder
                        .keyboardRow(tuple.map(s -> InlineKeyboardButton.builder()
                                        .text(s)
                                        .callbackData(s)
                                        .build())
                                .collect(Collectors.toList())));
        inlineKeyboardMarkupBuilder
                .keyboardRow(List.of(InlineKeyboardButton
                        .builder()
                        .text("Done")
                        .callbackData("done")
                        .build()));
        builder.replyMarkup(inlineKeyboardMarkupBuilder.build());
        sender.execute(builder.build());
    }

    @SneakyThrows
    @View("done")
    public void done(final AbsSender sender,
                     final Session session,
                     @SessionAtr("chat-id") String chatId,
                     @SessionAtr("farewell") String farewell,
                     @SessionAtr("answers") List<Answer> answers,
                     @SessionAtr("user") User user) {
        logger.debug("done");

        sender.execute(SendMessage.builder().text(farewell).chatId(chatId).build());
        sender.execute(SendMessage.builder().chatId(chatId).text("Запрос от: @" + user.getUserName()).build());

        StringBuilder sb = new StringBuilder();
        answers.forEach(answer -> sb
                .append(answer.getQuestion())
                .append("\n<b>")
                .append(answer.getAnswers().stream().reduce((s, s2) -> s.concat(", ").concat(s2)).get())
                .append("</b>\n\n"));
        sender.execute(SendMessage.builder().parseMode(ParseMode.HTML).text(sb.toString()).chatId(chatId).build());
        session.stop();
    }

    @SneakyThrows
    @View("cancel")
    public void cancel(final AbsSender sender,
                       @SessionAtr("chat-id") String chatId,
                       @SessionAtr("cancel") String cancel) {
        logger.debug("cancel");
        sender.execute(SendMessage.builder().text(cancel).chatId(chatId).build());
    }

    @SneakyThrows
    @View("help")
    public void help(final AbsSender sender,
                     @SessionAtr("chat-id") String chatId,
                     @SessionAtr("greeting") String greeting) {
        logger.debug("help");

        sender.execute(SendMessage.builder().text(greeting).chatId(chatId).build());
    }


}
