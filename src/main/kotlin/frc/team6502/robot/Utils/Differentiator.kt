package frc.team6502.robot.Utils

import edu.wpi.first.wpilibj.RobotBase.isReal
import edu.wpi.first.wpilibj.Timer

class Differentiator {

    private var lastFPGA = getTime()
    private var lastValue: Double? = null

    /**
     * Return the rate of change of value in units per second
     */
    fun calculate(value: Double): Double {
        val v = if (lastValue != null) (value - lastValue!!) / (getTime() - lastFPGA) else 0.0
        lastValue = value
        lastFPGA = getTime()
        return v
    }

    private fun getTime(): Double {
        return if (isReal()) {
            Timer.getFPGATimestamp()
        } else {
            System.currentTimeMillis() / 1000.0
        }
    }
}