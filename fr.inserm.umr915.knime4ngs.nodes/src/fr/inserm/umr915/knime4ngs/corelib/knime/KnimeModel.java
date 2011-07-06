package fr.inserm.umr915.knime4ngs.corelib.knime;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface KnimeModel {
	String name();
	String shortDesc();
	String longDesc();
	int countIn() default 0;
	int countOut() default 0;
}
