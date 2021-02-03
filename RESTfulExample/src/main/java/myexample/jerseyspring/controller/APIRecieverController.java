package myexample.jerseyspring.controller;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import myexample.jerseyspring.dto.Udun;

@RequestMapping("/APITransport")
@Controller
public class APIRecieverController {

	@Autowired
	RestTemplate restTemplate;

	@RequestMapping(value="recieverClient")
    public ModelAndView recieverClient(Model model) {
        ModelAndView mv = new ModelAndView("APIRecieverView");

//HTTP通信設定、URLを指定する
//ポストメソッドならここにリクエストボディをいれる
//HTTPヘッダーも入れる事が出来る
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080")
        		.path("/APITransport/APIAccessParametors");

        String result = "";
//HTTP通信を試みる
            try {
                result = target.request().get(String.class);
            } catch (BadRequestException e) {
                System.out.println("受信に失敗しました＞＜");
                throw e;
            }
//受信したjsonをそのままビューに送る。
//今回はjacksonでラップしない
            System.out.println("rcv mdg : " + result);
        mv.addObject("APIMessage", result);
        mv.addObject("udun", new Udun());
        mv.setViewName("APIRecieverView");
        return mv;
    }

	@RequestMapping("restClient")
	public String recieverRestClient(Model model) {
		String url="http://localhost:8080/APITransport/APIAccessParametors";

		Udun result = restTemplate.getForObject(url, Udun.class);

		model.addAttribute("APIMessage", "");
		model.addAttribute("udun", result);
		return "APIRecieverView";
	}
}
