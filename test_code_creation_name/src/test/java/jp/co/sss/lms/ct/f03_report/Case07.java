package jp.co.sss.lms.ct.f03_report;

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
 * 結合テスト レポート機能
 * ケース07
 * @author holy
 */
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース07 受講生 レポート新規登録(日報) 正常系")
public class Case07 {

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
		driver.findElement(By.cssSelector("#loginId")).sendKeys("StudentAA03");
		driver.findElement(By.cssSelector("#password")).sendKeys("Student03");

		driver.findElement(By.cssSelector("input[type='submit']")).click();

		WebElement Msg = driver.findElement(By.cssSelector("li small"));
		assertEquals("ようこそ受講生ＡＡ３さん", Msg.getText());

		WebElement Msg2 = driver.findElement(By.cssSelector("h2"));
		assertEquals("DEMOコース 2022年10月1日(土)～2022年10月31日(月)", Msg2.getText());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(3)
	@DisplayName("テスト03 未提出の研修日の「詳細」ボタンを押下しセクション詳細画面に遷移")
	void test03() {
		// TODO ここに追加
		driver.findElement(By.xpath("//form[input[@name='sectionId' and @value='3']]//input[@type='submit']")).click();

		WebElement Msg = driver.findElement(By.cssSelector("h2"));
		assertEquals("Java概要 2022年10月5日", Msg.getText());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「提出する」ボタンを押下しレポート登録画面に遷移")
	void test04() {
		// TODO ここに追加
		driver.findElement(By.cssSelector("input[type='submit']")).click();

		WebElement Msg = driver.findElement(By.cssSelector("h2"));
		assertEquals("日報【デモ】 2022年10月5日", Msg.getText());

		getEvidence(new Object() {
		});

	}

	@Test
	@Order(5)
	@DisplayName("テスト05 報告内容を入力して「提出する」ボタンを押下し確認ボタン名が更新される")
	void test05() {
		// TODO ここに追加
		driver.findElement(By.cssSelector("textarea")).sendKeys("日報を提出します。");

		driver.findElement(By.cssSelector("button[type='submit']")).click();

		WebElement Msg = driver.findElement(By.cssSelector("input[type='submit'].btn.btn-default"));
		assertEquals("提出済み日報【デモ】を確認する", Msg.getAttribute("value"));

		getEvidence(new Object() {
		});
	}

}
