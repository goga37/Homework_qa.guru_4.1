import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class SoftAssertionsJUnit5SearchTest {
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
    }
    @Test
    void JUnit5SearchTest(){
        open("https://github.com");
        $(".search-input").click();
        $("#query-builder-test").setValue("selenide").pressEnter();
        $("[data-testid=results-list]").$(".prc-Link-Link-85e08").click();
        $("#repository-container-header").shouldHave(text("selenide / selenide"));
        $(".UnderlineNav-body").$(byText("Wiki")).click();
        $("#wiki-pages-box").$(byText("Show 3 more pages…")).click();
        $("#wiki-pages-box").$(byText("SoftAssertions")).click();
        $("#wiki-content").shouldHave(text("@ExtendWith({SoftAssertsExtension.class})\n" +
                "class Tests {\n" +
                "  @Test\n" +
                "  void test() {\n" +
                "    Configuration.assertionMode = SOFT;\n" +
                "    open(\"page.html\");\n" +
                "\n" +
                "    $(\"#first\").should(visible).click();\n" +
                "    $(\"#second\").should(visible).click();\n" +
                "  }\n" +
                "}"));
    }
}
