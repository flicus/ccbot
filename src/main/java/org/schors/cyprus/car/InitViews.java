package org.schors.cyprus.car;

import lombok.SneakyThrows;
import org.slyrack.telegrambots.annotations.SessionAtr;
import org.slyrack.telegrambots.annotations.View;
import org.slyrack.telegrambots.annotations.ViewController;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.util.List;

@ViewController
public class InitViews {

    @SneakyThrows
    @View("start")
    public void start(final AbsSender sender, @SessionAtr("chat-id") String chatId) {

        sender.execute(SendMessage.builder()
                .text("hello")
                .chatId(chatId)
                .replyMarkup(InlineKeyboardMarkup.builder()
                        .keyboardRow(List.of(InlineKeyboardButton.builder()
                                .text("Оплата")
                                .callbackData("Оплата")
                                .build()))
                        .keyboardRow(List.of(InlineKeyboardButton.builder()
                                .text("Тарифы")
                                .callbackData("Тарифы")
                                .build()))
                        .keyboardRow(List.of(InlineKeyboardButton.builder()
                                .text("Интернет не работает")
                                .callbackData("Интернет не работает")
                                .build()))
                        .keyboardRow(List.of(InlineKeyboardButton.builder()
                                .text("Соединить с оператором")
                                .callbackData("Соединить с оператором")
                                .build()))
                        .build())
                .build());

    }
}
