package com.shinhan.myapp.chat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

//@Service  ...�ʼ��ƴ�
//@Service ������̼��� �ش� Ŭ������ �����ϸ�,�� ���� ����� ���õ� ����������Ŭ �̺�Ʈ�� ó���ϵ��� �ϴµ� �ʼ����� ����

//�������� ���� �ǽð� ����� ����� �����ϰ� �ϴ� ���� ���̵��� ������
@ServerEndpoint("/chatserver") 
public class WebSocketServer {

	// ���� ä�� ������ ������ Ŭ���̾�Ʈ(WebSocket Session) ���
	private static List<Session> list = new ArrayList<Session>();
	
	@OnOpen //�� ������ ����Ǹ� ȣ��Ǵ� �̺�Ʈ
	public void handleOpen(Session session) {
		list.add(session); // ������ ����		 
	}
	@OnClose
	// �� ������ ������ ȣ��Ǵ� �̺�Ʈ
	public void handleClose(Session session) {
	    list.remove(session);
	}
	@OnError
	//�� ���� ������ ���� �߻��ϴ� �̺�Ʈ
	public void handleError(Throwable t) {

	}

	@OnMessage //Ŭ���̾�Ʈ���� ���� ������ �޽����� ������ ȣ��Ǵ� �̺�Ʈ
	public void handleMessage(String msg, Session session) {
		// �α����� ��: 1#������
		// ��ȭ �� ��: 2������#�޼���
		int index = msg.indexOf("#", 2);
		String no = msg.substring(0, 1);
		String user = msg.substring(2, index);
		String txt = msg.substring(index + 1);
		if (no.equals("1")) {
			// ������ ���� > 1#�ƹ���
			for (Session s : list) {
				if (s != session) { // ���� �����ڰ� �ƴ� ������ �����
					try {
						s.getBasicRemote().sendText("1#" + user + "#" +txt);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} else if (no.equals("2")) {
			// ������ �޼����� ����
			for (Session s : list) {
				if (s != session) { // ���� �����ڰ� �ƴ� ������ �����
					try {
						s.getBasicRemote().sendText("2#" + user + ":" + txt);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	
}