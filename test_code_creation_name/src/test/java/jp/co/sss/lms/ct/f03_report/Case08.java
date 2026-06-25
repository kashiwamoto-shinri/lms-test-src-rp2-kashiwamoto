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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 結合テスト レポート機能
 * ケース08
 * @author holy
 */
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース08 受講生 レポート修正(週報) 正常系")
public class Case08 {

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
	@DisplayName("テスト03 提出済の研修日の「詳細」ボタンを押下しセクション詳細画面に遷移")
	void test03() {
		// TODO ここに追加
		driver.findElement(By.xpath("//form[input[@name='sectionId' and @value='2']]//input[@type='submit']")).click();

		WebElement Msg = driver.findElement(By.cssSelector("h2"));
		assertEquals("アルゴリズム、フローチャート 2022年10月2日", Msg.getText());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「確認する」ボタンを押下しレポート登録画面に遷移")
	void test04() {
		// TODO ここに追加
		driver.findElement(By.xpath("//form[input[@name='dailyReportSubmitId' and @value='3']]//input[@type='submit']"))
				.click();

		WebElement Msg = driver.findElement(By.cssSelector("h2"));
		assertEquals("週報【デモ】 2022年10月2日", Msg.getText());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 報告内容を修正して「提出する」ボタンを押下しセクション詳細画面に遷移")
	void test05() {
		// TODO ここに追加

		driver.findElement(By.xpath("//textarea[@name='contentArray[1]']")).sendKeys("週報を修正しました。");

		WheelInput.ScrollOrigin scrollOrigin = WheelInput.ScrollOrigin.fromViewport(10, 10);
		new Actions(driver)
				.scrollFromOrigin(scrollOrigin, 0, 200)
				.perform();

		driver.findElement(By.cssSelector("button.btn.btn-primary[type='submit']")).click();

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(6)
	@DisplayName("テスト06 上部メニューの「ようこそ○○さん」リンクからユーザー詳細画面に遷移")
	void test06() {
		// TODO ここに追加
		driver.findElement(By.linkText("ようこそ受講生ＡＡ１さん")).click();

		WebElement Msg = driver.findElement(By.cssSelector("h2"));
		assertEquals("ユーザー詳細", Msg.getText());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(7)
	@DisplayName("テスト07 該当レポートの「詳細」ボタンを押下しレポート詳細画面で修正内容が反映される")
	void test07() {
		// TODO ここに追加
		WheelInput.ScrollOrigin scrollOrigin = WheelInput.ScrollOrigin.fromViewport(10, 10);
		new Actions(driver)
				.scrollFromOrigin(scrollOrigin, 0, 200)
				.perform();

		driver.findElement(
				By.xpath("//form[input[@name='dailyReportSubmitId' and @value='3']]//input[@value='詳細']")).click();

		getEvidence(new Object() {
		});
	}

}
