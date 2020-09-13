package mail;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.MailAuth;

@WebServlet("/send")
public class MailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// FROM
		//	보내는 사람 정보
		final String FROM = "보내는 메일"; // <<------------------------------ 수정해서 사용
		final String FROMNAME = "보내는 사람"; // <<------------------------------ 수정해서 사용

		// TO
		//	받는 사람 정보
		final String TO = "받는 메일"; // <<------------------------------ 수정해서 사용

		// 메일 제목
		final String SUBJECT = "메일 타이틀"; // <<------------------------------ 수정해서 사용

		// 메일 본문
		//	<p>태그 내부에 텍스트 내용에 입력
		final String BODY = String.join(
				"<h1>메일 컨텐트</h1>",
				"<p>내용 내용 내용</p>"); // <<------------------------------ 수정해서 사용

		// 인증 객체
		//	보내는 사람의 실제 구글 계정 입력
		Authenticator auth = new MailAuth("보내는 메일", "비밀번호"); // <<------------------------------ 수정해서 사용

		
		
		
		// 연결 설정
		Properties props = System.getProperties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");
		
		// 메일 세션 객체 생성
		Session session = Session.getDefaultInstance(props, auth);

		// 메시지 생성
		MimeMessage msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress(FROM, FROMNAME));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
			msg.setSubject(SUBJECT);
			msg.setContent(BODY, "text/html;charset=utf-8");

			System.out.println("Sending...");

			//메시지 보내기
			Transport.send(msg);
			
			System.out.println("Email sent!");

		} catch (NoSuchProviderException e) {
			e.printStackTrace();
			
		} catch (MessagingException e) {
			e.printStackTrace();
			
			System.out.println("The email was not sent.");
			System.out.println("Error message: " + e.getMessage());
			
		} 			
	}
}
