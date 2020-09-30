package com.lvmama.framework.demo;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.dianping.cat.message.ForkedTransaction;
import com.dianping.cat.message.Transaction;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class DemoServlet extends HttpServlet {


	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Transaction transaction = Cat.newTransaction("URL", "user/list");

		final ForkedTransaction forkedTransaction = Cat.newForkedTransaction("forkHello5", "xx");
		new Thread(new Runnable() {
			public void run() {
				forkedTransaction.fork(); // 新的context
				forkedTransaction.setStatus(Transaction.SUCCESS);
				forkedTransaction.complete(); // context remove
			}
		}).start();

		transaction.setStatus(Transaction.SUCCESS);
		transaction.complete(); //

		req.getRequestDispatcher("/WEB-INF/view/demo.jsp").forward(req, resp);
	}
}
