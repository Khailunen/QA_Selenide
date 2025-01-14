package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.nio.channels.FileChannel;
import java.nio.channels.Selector;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static java.time.Duration.*;

public class RegistrationFormTest {
    public String generateDate (int days, String pattern){
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    void registrationValidData () {
        String planingDate = generateDate (3,"dd.MM.yyyy");
        Selenide.open("http://localhost:9999/");
        $("[placeholder='Город']").setValue("Москва").selectRadio("Москва");
        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(planingDate);
        $("[name='name']").setValue("Иванов Иван");
        $("[name='phone']").setValue("+70123456789");
        $("[data-test-id='agreement']").click();
        $$(".button").find(Condition.exactText("Забронировать")).click();
        $(Selectors.withText("Успешно")).shouldBe(visible,Duration.ofSeconds(15));
    }
}
