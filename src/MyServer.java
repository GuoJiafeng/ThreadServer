import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MyServer {
	//定义保存索欧的Socket的ArrayList 
	public static ArrayList<Socket> socketList =  new ArrayList<Socket>();
	
	public static void main(String[] args) throws IOException{
		ServerSocket ss = new ServerSocket(23333);
		
		while(true){
			//此行代码会被阻塞，讲一直等待被人的连接
			Socket s= ss.accept();
			socketList.add(s);
			//每当客户端连接后启动一条线程为该客户端服务
			new Thread(new ServerTherad(s)).start();
		}
	}

}
