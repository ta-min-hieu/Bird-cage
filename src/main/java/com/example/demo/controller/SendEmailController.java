package com.example.demo.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.EmailDTO;
import com.example.demo.DTO.registerDTO;
import com.example.demo.Service.EmailService;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/sendemail")
@CrossOrigin("http://localhost:3000")
public class SendEmailController {
	
	@Autowired
	private EmailService emailService;
	@Autowired
	private static final Random random = new Random();
	
	@PostMapping("/sendEmailVerify")
	public String sendEmail(@RequestBody registerDTO DTO) {
		String token = emailService.findTokenByEmail(DTO.getEmail());
		String text = "Đây là mã xác nhận Email của bạn: \n ";
		emailService.SendEmail(DTO.getEmail(), "Mã xác nhận Email từ BirdCage web", text + token );
		return "Send Succesfully";
	}
	
	@PostMapping("/sendEmailrestpassword")
	public String sendEmail(@RequestBody EmailDTO DTO) throws MessagingException{
		String token = emailService.findTokenByEmail(DTO.getEmail());
		String url = "http://localhost:3000/forgotpassword/?token="+token;
		String text = "<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "<head>\r\n"
				+ "    <meta charset=\"UTF-8\">\r\n"
				+ "    <title>Email Template</title>\r\n"
				+ "    <style>\r\n"
				+ "        /* CSS styles for the email template */\r\n"
				+ "        body {\r\n"
				+ "            font-family: Arial, sans-serif;\r\n"
				+ "            background-color: #f4f4f4;\r\n"
				+ "            margin: 0;\r\n"
				+ "            padding: 0;\r\n"
				+ "        }\r\n"
				+ "\r\n"
				+ "        .container {\r\n"
				+ "            max-width: 600px;\r\n"
				+ "            margin: 0 auto;\r\n"
				+ "            background-color: #ffffff;\r\n"
				+ "            padding: 20px;\r\n"
				+ "            border-radius: 5px;\r\n"
				+ "            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);\r\n"
				+ "        }\r\n"
				+ "        .logo {\r\n"
				+ "            text-align: center;\r\n"
				+ "            margin-bottom: 5px;\r\n"
				+ "            padding: 40px;\r\n"
				+ "        }\r\n"
				+ "        .logo img{\r\n"
				+ "            width: 200px;\r\n"
				+ "        }\r\n"
				+ "\r\n"
				+ "        .info {\r\n"
				+ "            margin: auto\r\n"
				+ "        }\r\n"
				+ "\r\n"
				+ "        .info p {\r\n"
				+ "            margin: 0;\r\n"
				+ "            line-height: 1.5;\r\n"
				+ "        }\r\n"
				+ "        .content {\r\n"
				+ "            margin-bottom: 20px;\r\n"
				+ "        }\r\n"
				+ "\r\n"
				+ "        .content p {\r\n"
				+ "            margin: 0;\r\n"
				+ "            line-height: 1.5;\r\n"
				+ "        }\r\n"
				+ "        .container a {\r\n"
				+ "            display: inline-block;\r\n"
				+ "            padding: 12px 24px;\r\n"
				+ "            font-size: 16px;\r\n"
				+ "            font-weight: bold;\r\n"
				+ "            text-align: end;\r\n"
				+ "            text-decoration: none;\r\n"
				+ "            background-color: #24688A;\r\n"
				+ "            color: white;\r\n"
				+ "            border-radius: 4px;\r\n"
				+ "            border: none;\r\n"
				+ "            transition: background-color 0.3s;\r\n"
				+ "            /* justify-content:  center; */\r\n"
				+ "        }\r\n"
				+ "        .btn:hover {\r\n"
				+ "            background-color: #408bb1;\r\n"
				+ "        }\r\n"
				+ "    </style>\r\n"
				+ "</head>\r\n"
				+ "\r\n"
				+ "<body>\r\n"
				+ "    <script>\r\n"
				+ "        function copyToClipboard() {\r\n"
				+ "            var text = document.getElementById(\"confirmationCode\").innerText;\r\n"
				+ "            navigator.clipboard.writeText(text)\r\n"
				+ "                .then(function () {\r\n"
				+ "                    alert(\"Đã sao chép mã xác nhận vào clipboard!\");\r\n"
				+ "                })\r\n"
				+ "                .catch(function (error) {\r\n"
				+ "                    console.error(\"Lỗi khi sao chép vào clipboard:\", error);\r\n"
				+ "                });\r\n"
				+ "        }\r\n"
				+ "    </script>\r\n"
				+ "    <div class=\"container\">\r\n"
				+ "        <div class=\"logo\" style=\"background-color: #24688A;\">\r\n"
				+ "            <img src=\"https://i.imgur.com/tnjUUA5.png\" title=\"source: imgur.com\" />\r\n"
				+ "        </div>\r\n"
				+ "        <hr style=\"margin: 20px 0\" />\r\n"
				+ "        <div class=\"info\">\r\n"
				+ "            <p>Chào bạn <b><i><span th:text=\"${user_name}\">,</span></i></b>\r\n"
				+ "            <p></p>\r\n"
				+ "            <p>Bạn đã yêu cầu đặt lại mật khẩu cho tài khoản của mình tại <strong>Cavea</strong>.</p>\r\n"
				+ "            <br>\r\n"
				+ "            <p id=\"confirmationCode\"><strong>Mã Xác Nhận:</strong> [<strong><span th:text=\"${token}\"> </span></strong>]\r\n"
				+ "            </p>\r\n"
				+ "            <br>\r\n"
				+ "            <p>Mã thông báo của bạn sẽ hết hạn vào lúc:</p>\r\n"
				+ "            <p><strong>Thời gian:</strong> <strong><span th:text=\"${getExpired_verification_code}\"> </span></strong></p>\r\n"
				+ "            <br>\r\n"
				+ "        </div>\r\n"
				+ "        <br>\r\n"
				+ "        <p>Để tiếp tục quá trình đặt lại mật khẩu, vui lòng nhấp vào liên kết bên dưới và nhập mã xác nhận khi được yêu\r\n"
				+ "            cầu</p>\r\n"
				+ "            <!-- ###################################################### -->\r\n"
				+ "        <div style=\"display: flex; justify-content: center;\">\r\n"
				+ "            <a href='"+url+"'>Thay đổi mật khẩu</a>\r\n"
				+ "        </div>\r\n"
				+ "        <br>\r\n"
				+ "        <div class=\"content\">\r\n"
				+ "            <p> <i>Vui lòng đặt lại mật khẩu của bạn trước khi thời gian này kết thúc.</i></p>\r\n"
				+ "            <br>\r\n"
				+ "            <p>Nếu bạn không yêu cầu đặt lại mật khẩu này, vui lòng bỏ qua email này.</p>\r\n"
				+ "            <p>Nếu bạn tin rằng có ai đó đang cố gắng truy cập vào tài khoản của bạn, vui lòng liên hệ với chúng tôi\r\n"
				+ "                ngay lập tức để được trợ giúp.</p>\r\n"
				+ "            <br>\r\n"
				+ "            <p>Trân trọng. Cavea</p>\r\n"
				+ "        </div>\r\n"
				+ "        <br>\r\n"
				+ "        <div class=\"info\" style=\"background-color: #24688A; height: 25px;\">\r\n"
				+ "        </div>\r\n"
				+ "        <div style=\"display: flex; justify-content: space-between;\">\r\n"
				+ "            <div>\r\n"
				+ "                <p style=\"font-size: 25px;\">Cavea</p>\r\n"
				+ "                <p style=\"font-size: 10px;\">© 2023 Cavea Team - FPTU\r\n"
				+ "                    HCM Campus</p>\r\n"
				+ "\r\n"
				+ "                <p style=\"font-size: 10px;\">Địa chỉ: Đường D1 Khu Công\r\n"
				+ "                    nghệ cao, <br>\r\n"
				+ "                    Phường Long Thạnh Mỹ, Thủ Đức, TP. Hồ Chí Minh.</p>\r\n"
				+ "                <img width=\"20px\"\r\n"
				+ "                    src=\"https://th.bing.com/th/id/R.8eb68c7065e5057c9314f335d1bcb4a6?rik=jN4%2f3c2j9%2fNquA&riu=http%3a%2f%2fpluspng.com%2fimg-png%2ftwitter-png-twitter-icon-1600.png&ehk=cfVuY5vaxmKssnSiLi2qsApgVuOQ2a3R0fEel7T0mJk%3d&risl=&pid=ImgRaw&r=0\"\r\n"
				+ "                    alt=\"\">\r\n"
				+ "                <img width=\"20px\"\r\n"
				+ "                    src=\"https://cdn2.iconfinder.com/data/icons/black-white-social-media/32/online_social_media_facebook-512.png\"\r\n"
				+ "                    alt=\"\">\r\n"
				+ "            </div>\r\n"
				+ "            <div>\r\n"
				+ "                <p style=\"font-size: 25px;\">Hỗ Trợ</p>\r\n"
				+ "                <p style=\"font-size: 10px;\">Trung tâm hỗ trợ</p>\r\n"
				+ "                <p style=\"font-size: 10px;\">Liên hệ trực tiếp</p>\r\n"
				+ "            </div>\r\n"
				+ "        </div>\r\n"
				+ "    </div>\r\n"
				+ "</body>\r\n"
				+ "</html>";
		emailService.SendEmailByHTML(DTO.getEmail(),"BirdCage Web",text );
		return "Send SuccesFully";
	}
}
