package JUnit;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.UnknownHostException;

import org.junit.Before;
import org.junit.Test;

import protocol.Msg.LoginMsg;

import client.SocketClientTest;

import server.NetworkMessage;

/**
 * �Ե�½���ܵĲ��ԣ�Ҫ�ȿ���������
 * @author Feng
 *
 */
public class TestLogin {
	public SocketClientTest client;

	@Before
	public void init() throws UnknownHostException, IOException {
		client = new SocketClientTest();
		client.link();
	}

	/**
	 * ���Ե�½����
	 * 
	 * @author Feng
	 * @throws IOException
	 * @throws UnknownHostException
	 */
	@Test
	public void testLogin() throws UnknownHostException, IOException {
		System.out.println("Start Test Login!");
		byte[] resultBytes = client.testLogin_JUint("a", "aa");
		LoginMsg.LoginRsp responseObject = LoginMsg.LoginRsp.parseFrom(NetworkMessage.getMessageObjectBytes(resultBytes));
		assertEquals(responseObject.getResultCode().toString(), LoginMsg.LoginRsp.ResultCode.SUCCESS.toString());

		resultBytes = client.testLogin_JUint("aa", "aa");
		responseObject = LoginMsg.LoginRsp.parseFrom(NetworkMessage.getMessageObjectBytes(resultBytes));
		assertEquals(responseObject.getResultCode().toString(), LoginMsg.LoginRsp.ResultCode.FAIL.toString());

		resultBytes = client.testLogin_JUint("a", "aaa");
		responseObject = LoginMsg.LoginRsp.parseFrom(NetworkMessage.getMessageObjectBytes(resultBytes));
		assertEquals(responseObject.getResultCode().toString(), LoginMsg.LoginRsp.ResultCode.FAIL.toString());
	}
}