package jp.co.sss.lms.ct.f02_faq;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 結合テスト よくある質問機能
 * ケース04
 * @author holy
 */
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース04 よくある質問画面への遷移")
public class Case04 {

	private static WebDriver driver;

	/** 前処理 */
	@BeforeAll
	static void before() {
		createDriver();
		driver = webDriver;
	}

	/** 後処理 */
	@AfterAll
	static void after() {
		closeDriver();
	}

	@Test
	@Order(1)
	@DisplayName("テスト01 トップページURLでアクセス")
	void test01() {
		// TODO ここに追加
		driver.get("http://localhost:8080/lms");

		WebElement Msg = driver.findElement(By.tagName("h2"));
		assertEquals("ログイン", Msg.getText());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(2)
	@DisplayName("テスト02 初回ログイン済みの受講生ユーザーでログイン")
	void test02() {
		// TODO ここに追加
		driver.findElement(By.cssSelector("#loginId")).sendKeys("StudentAA01");
		driver.findElement(By.cssSelector("#password")).sendKeys("Student01");

		driver.findElement(By.cssSelector("input[type='submit']")).click();

		WebElement Msg = driver.findElement(By.cssSelector("li small"));
		assertEquals("ようこそ受講生ＡＡ１さん", Msg.getText());

		WebElement Msg2 = driver.findElement(By.cssSelector("h2"));
		assertEquals("DEMOコース 2022年10月1日(土)～2022年10月31日(月)", Msg2.getText());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(3)
	@DisplayName("テスト03 上部メニューの「ヘルプ」リンクからヘルプ画面に遷移")
	void test03() {
		// TODO ここに追加
		driver.findElement(By.linkText("機能")).click();
		driver.findElement(By.linkText("ヘルプ")).click();

		WebElement Msg = driver.findElement(By.cssSelector("h2"));
		assertEquals("ヘルプ", Msg.getText());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「よくある質問」リンクからよくある質問画面を別タブに開く")
	void test04() {
		// TODO ここに追加
		driver.findElement(By.linkText("よくある質問")).click();

		Object[] windowHandles = driver.getWindowHandles().toArray();
		driver.switchTo().window((String) windowHandles[1]);

		WebElement Msg = driver.findElement(By.cssSelector("h2"));
		assertEquals("よくある質問", Msg.getText());

		getEvidence(new Object() {
		});
	}

}
