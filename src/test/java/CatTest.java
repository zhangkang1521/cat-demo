import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.dianping.cat.message.ForkedTransaction;
import com.dianping.cat.message.Transaction;
import org.junit.Test;

public class CatTest {

	@Test
	public void test1() throws Exception {
		Transaction transaction = Cat.newTransaction("URL-9", "user/list");
		transaction.setStatus(Transaction.SUCCESS);
		transaction.complete(); //
		// 使用 TcpSocketSender 线程进行消息发送，所以等待
		System.in.read();
	}
}
