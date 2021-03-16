package frc.team6502.robot.Subsystems

import com.revrobotics.CANSparkMax
import com.revrobotics.CANSparkMaxLowLevel
import com.revrobotics.SparkMax
import edu.wpi.first.wpilibj.controller.PIDController
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward
import edu.wpi.first.wpilibj.geometry.Rotation2d
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import edu.wpi.first.wpilibj2.command.SubsystemBase
import frc.team6502.robot.Commands.Drive.DefaultDrive
import frc.team6502.robot.Constants
import frc.team6502.robot.RobotContainer
import kotlin.math.PI


object Drivetrain : SubsystemBase() {
    // ---------- motors ----------
    private val leftDrive = CANSparkMax(Constants.LEFT_DRIVE_PORT, CANSparkMaxLowLevel.MotorType.kBrushless).apply {
        restoreFactoryDefaults()
        idleMode = CANSparkMax.IdleMode.kCoast
        setSmartCurrentLimit(40)
    }
    private val rightDrive = CANSparkMax(Constants.RIGHT_DRIVE_PORT, CANSparkMaxLowLevel.MotorType.kBrushless).apply {
        restoreFactoryDefaults()
        idleMode = CANSparkMax.IdleMode.kCoast
        setSmartCurrentLimit(40)
    }


    // ---------- controllers ----------
    // handles the conversion between v/omega and left/right velocities
    val kinematics = DifferentialDriveKinematics(Constants.DRIVE_TRACK_WIDTH)

    // keeps track of where the robot is on the field
    val odometry = DifferentialDriveOdometry(Rotation2d())

    // feedforward - estimator for what voltage should be applied
    private val leftFeedforward = SimpleMotorFeedforward(Constants.DRIVE_KS_L, Constants.DRIVE_KV_L, Constants.DRIVE_KA_L)
    private val rightFeedforward = SimpleMotorFeedforward(Constants.DRIVE_KS_R, Constants.DRIVE_KV_R, Constants.DRIVE_KA_R)

    // PID - error controllers
    private val leftPID = PIDController(Constants.DRIVE_P, Constants.DRIVE_I, Constants.DRIVE_D)
    private val rightPID = PIDController(Constants.DRIVE_P, Constants.DRIVE_I, Constants.DRIVE_D)

    // encoders - measure velocity of motors
    private val leftEncoder = leftDrive.encoder.apply {
        velocityConversionFactor = Constants.DRIVE_GEAR_RATIO * (Constants.DRIVE_WHEEL_RADIUS * 2 * PI) // why did divide by 9.9 on 2020?
        positionConversionFactor = (Constants.DRIVE_WHEEL_RADIUS * 2 * PI)
    }
    private val rightEncoder = rightDrive.encoder.apply {
        velocityConversionFactor = Constants.DRIVE_GEAR_RATIO * (Constants.DRIVE_WHEEL_RADIUS * 2 * PI) // why did divide by 9.9 on 2020?
        positionConversionFactor = (Constants.DRIVE_WHEEL_RADIUS * 2 * PI)
    }

    init {
        // make the teleop drive command run by default
        defaultCommand = DefaultDrive()
    }

    fun drive(speeds: DifferentialDriveWheelSpeeds) {
        // convert m/s to ft/s
        val lms = speeds.leftMetersPerSecond
        val rms = speeds.rightMetersPerSecond

        // compute final outputs and apply ff
        val lv = leftPID.calculate(leftEncoder.velocity, lms) + leftFeedforward.calculate(lms, leftAccelCalculator.calculate(lms))
        val rv = rightPID.calculate(rightEncoder.velocity, rms) + rightFeedforward.calculate(rms, rightAccelCalculator.calculate(rms))

        SmartDashboard.putNumber("lerror", leftPID.positionError)
        SmartDashboard.putNumber("rerror", rightPID.positionError)

        // write outputs to motors
        leftDrive.setVoltage(lv)
        rightDrive.setVoltage(rv)
    }

    override fun periodic() {
        // update the odometry
//        odometry.update(
//            Rotation2d.fromDegrees(RobotContainer.pigeon.fusedHeading),
//            leftEnc.position,
//            rightEnc.position
//        )
    }

}