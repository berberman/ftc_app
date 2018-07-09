package org.firstinspires.ftc.robotcontroller.internal.filter

import org.firstinspires.ftc.robotcore.internal.opmode.ClassFilter
import org.firstinspires.ftc.robotcore.internal.opmode.ClassManager

object ClassFilterProvider : ClassFilterAdapter() {
	override fun filterClass(clazz: Class<*>) {
		if (clazz.isAnnotationPresent(Filter::class.java) &&
				ClassFilterAdapter::class.java.isAssignableFrom(clazz))
			ClassManager.getInstance().registerFilter(clazz.newInstance() as ClassFilter)
	}
}