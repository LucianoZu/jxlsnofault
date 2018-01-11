# jxlsnofault
A simple class to avoid Failed to evaluate property IllegalStateException from JXLS SimpleExporter

IterableWrapper wraps an object that JXLS could use to render an Excel.
Since it's an Iterable object, JXLS doesn't raise an exception if some prop isn't valuable.

See JXLS GridCommand class used from SimpleExporter https://bitbucket.org/leonate/jxls/src/65fb5b705a70a8d8d3971e2abb8ec665d47434eb/src/main/java/org/jxls/command/GridCommand.java?at=r2.4.3&fileviewer=file-view-default


At row 163 if the object is Iterable then the evaluation is more simple otherwise al line 190 PropertyUtils.getProperty() may throw an Exception
for instance when you can to access to a child object property when the child object is null.
see row 197 and 199 with its message: "Failed to evaluate property ...  of row object of class ..."

With IterableWrapper you can avoid exceptions like this one:
java.lang.IllegalStateException: Failed to evaluate property secondChild.name of row object of class zu.jxls.jxlsnofault.ParentBean
	at org.jxls.command.GridCommand.processBody(GridCommand.java:199)
	at org.jxls.command.GridCommand.applyAt(GridCommand.java:142)
	at org.jxls.area.XlsArea.applyAt(XlsArea.java:171)
	at org.jxls.template.SimpleExporter.gridExport(SimpleExporter.java:61)
	at zu.jxls.jxlsnofault.IterableWrapperTest.shouldRaiseAnException(IterableWrapperTest.java:18)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
	at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:78)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:57)
	at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
	at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
	at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
	at org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:86)
	at org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:459)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:678)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:382)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:192)
Caused by: org.apache.commons.beanutils.NestedNullException: Null property value for 'secondChild.name' on bean class 'class zu.jxls.jxlsnofault.ParentBean'
	at org.apache.commons.beanutils.PropertyUtilsBean.getNestedProperty(PropertyUtilsBean.java:793)
	at org.apache.commons.beanutils.PropertyUtilsBean.getProperty(PropertyUtilsBean.java:884)
	at org.apache.commons.beanutils.PropertyUtils.getProperty(PropertyUtils.java:464)
	at org.jxls.command.GridCommand.processBody(GridCommand.java:190)
	... 27 more

See IterableWrapperTest JUnit test cases