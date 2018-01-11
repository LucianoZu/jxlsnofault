# jxlsnofault
A simple class to avoid Failed to evaluate property IllegalStateException from JXLS SimpleExporter

IterableWrapper wraps an object that JXLS could use to render an Excel.
Since it's an Iterable object, JXLS doesn't raise an exception if some prop isn't valuable.

See JXLS GridCommand class used from SimpleExporter https://bitbucket.org/leonate/jxls/src/65fb5b705a70a8d8d3971e2abb8ec665d47434eb/src/main/java/org/jxls/command/GridCommand.java?at=r2.4.3&fileviewer=file-view-default


At row 163 if the object is Iterable then the evaluation is more simple otherwise al line 190 PropertyUtils.getProperty() may throw an Exception
for instance when you can to access to a child object property when the child object is null.
see row 197 and 199 with its message: "Failed to evaluate property ...  of row object of class ..."

