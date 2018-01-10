package org.tiny.db;

import org.tiny.base.Procedure;
import org.tiny.base.Transaction;

public class TransactionThread implements Runnable{
	Procedure p;
	
	public TransactionThread (Procedure p) {
		this.p = p;
	}
	
	@Override
	public void run() {
		Transaction t = Transaction.create();
		
		try {
			if (p.process()) {
				t.commit();
			} else {
				// may need do nothing
				// rollback?
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Transaction.end();
		}
	} 
}
