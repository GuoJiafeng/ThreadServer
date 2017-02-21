import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.nio.Buffer;
import java.util.Iterator;



public class ServerTherad implements Runnable {

	//定义当前线程所处理的Socket
	Socket s = null;
	//该线程所处理的Socket所对应的的输入流
	BufferedReader br = null;
	
	public ServerTherad(Socket s)  throws IOException{
		this.s = s;
		//初始化该Socket对应的输入流
		br = new BufferedReader(new InputStreamReader(s.getInputStream(), "utf-8"));
		
		
	}

	@Override
	public void run() {
		try {
			String content = null;
			//采取循环不断从Socket中读取客户端发送过来的数据
			while((content = readFromClient())!=null){
				//遍历SocketList中的每个Socket
				//讲读到的内容向每个Socket发送一次
				
				for(Iterator<Socket> it = MyServer.socketList.iterator();it.hasNext();){
					Socket s = it.next();
					try {
					OutputStream os = s.getOutputStream();
						os.write((content+"\n").getBytes("utf-8"));
					} catch (Exception e) {
						e.printStackTrace();
						//删除该Socket
						System.out.println(MyServer.socketList);
					}
				}
			}
			
		} catch (Exception e) {
		e.printStackTrace();
		}
		
	}
	
	public String readFromClient(){
		try {
			return br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			//删除该Socket
			MyServer.socketList.remove(s);
		}
		
		
		return null;
		
	}

}
