package myexample.jerseyspring.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("api")
public class WebAptController {

	@RequestMapping("hello")
	private String hello() {
		return "SpringBoot!";
	}

	// パスパラメータ
	@RequestMapping("test/{param}")
	private String testPathVariable(@PathVariable String param) {
		return "受け取ったパスパラメータ" + param;
	}

	// リクエストパラメータ
	// test?param=*** で受け取る
	@RequestMapping("test")
	private String testRequestParam(@RequestParam() String param) {
		return "受け取ったリクエストパラメータ" + param;
	}

	// リクエストボディ
	@RequestMapping(value = "test", method = RequestMethod.POST)
	private String testRequestBody(@RequestBody String body) {
		return "受け取ったリクエストボディ" + body;
	}

	public static class HogeMogeBean{
		protected String getHoge() {
			return hoge;
		}

		protected int getMoge() {
			return moge;
		}

		private String hoge;
		private int moge;

		HogeMogeBean(String hoge, int moge){
			this.hoge = hoge;
			this.moge = moge;
		}
	}

	@RequestMapping("hogemoge")
	public HogeMogeBean hogemoge() {
		return new HogeMogeBean("ほげ", 1234);
	}

	@RequestMapping(value = "hogemoge2", produces = MediaType.APPLICATION_JSON_VALUE)
	public String string() throws Exception{
		HogeMogeBean bean = new HogeMogeBean("もげ", 297);
		String json = new ObjectMapper().writeValueAsString(bean);
		return json;
	}
}
