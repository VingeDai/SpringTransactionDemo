package com.zs.spring.demo1;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @Description:转账案例的业务层实现类
 */
public class AccountServiceImpl implements AccountService {

	// 注入转账的DAO
	private AccountDao accountDao;

	// 注入事务管理的模板
	private TransactionTemplate transactionTemplate;

	/**
	 * @param out
	 *            :转出账号
	 * @param in
	 *            :转入账号
	 * @param money
	 *            :转账金额
	 */
	@Override
	public void transfer(final String out, final String in, final Double money) {

		// 未经事务控制的业务处理操作，如果过程中出异常，则导致前面的操作能完成，后面的不能，即转账成功但未收到转账款
		// accountDao.outMoney(out, money);
		// int i = 1/0;
		// accountDao.inMoney(in, money);

		transactionTemplate.execute(new TransactionCallbackWithoutResult() {

			@Override
			protected void doInTransactionWithoutResult(
					TransactionStatus transactionStatus) {
				accountDao.outMoney(out, money);
				// int i = 1 / 0;//事务控制，即出现异常，该段内代码都执行失效
				accountDao.inMoney(in, money);
			}
		});
	}

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

}
