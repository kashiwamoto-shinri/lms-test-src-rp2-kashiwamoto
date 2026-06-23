package jp.co.sss.lms.ct.f06_login2;

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
 * 結合テスト ログイン機能②
 * ケース15
 * @author holy
 */
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース15 受講生 初回ログイン 利用規約に不同意")
public class Case15 {

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
	@DisplayName("テスト02 DBに初期登録された未ログインの受講生ユーザーでログイン")
	void test02() {
		// TODO ここに追加
		driver.findElement(By.cssSelector("#loginId")).sendKeys("StudentAA02");
		driver.findElement(By.cssSelector("#password")).sendKeys("StudentAA02");

		driver.findElement(By.cssSelector("input[type='submit']")).click();

		WebElement Msg = driver.findElement(By.tagName("h2"));
		assertEquals("利用規約", Msg.getText());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(3)
	@DisplayName("テスト03 「同意します」チェックボックスにチェックをせず「次へ」ボタンを押下")
	void test03() {
		// TODO ここに追加
		driver.findElement(By.cssSelector("button[type='submit']")).click();

		WebElement errorMsg = driver.findElement(By.xpath("//div[@class='error']"));
		assertEquals("セキュリティ規約への同意は必須です。", errorMsg.getText());

		getEvidence(new Object() {
		});
	}

}
