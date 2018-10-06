本例实现了使用不同事务方式实现转账操作

Spring将事务管理分成了两类:
	* 编程式事务管理
		* 手动编写代码进行事务管理.(很少使用)
	* 声明式事务管理:
		* 基于TransactionProxyFactoryBean的方式.(很少使用)
			* 需要为每个进行事务管理的类,配置一个TransactionProxyFactoryBean进行增强.
		* 基于AspectJ的XML方式.(经常使用)
			* 一旦配置好之后,类上不需要添加任何东西
		* 基于注解方式.(经常使用)
			* 配置简单,需要在业务层上添加一个@Transactional的注解.